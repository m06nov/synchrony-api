package com.synchrony.app.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.synchrony.app.configs.ImpurConfiguration;
import com.synchrony.app.dto.ImageInfoDTO;
import com.synchrony.app.repositories.ImageReposotory;
import com.synchrony.app.response.pojo.UploadImageResponseRoot;

@RestController
@RequestMapping(value = "/image")
public class ImageController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ImageReposotory imageReposotory;
	
	@Autowired
	private ImpurConfiguration configuration;
	
	private String uploadURL = "https://api.imgur.com/3/upload";

	@PostMapping("/upload/{userId}")
	public Long upload(@PathVariable("userId") Long userId, @RequestPart("file") MultipartFile multipartFile){
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		if(!StringUtils.isEmpty(configuration.getAccessToken())) {
			headers.add("Token", configuration.getAccessToken());
		}
		else {
			headers.add("Authorization", "Client-ID "+configuration.getClientid());
		}
		
		
		
		MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("image", multipartFile.getResource());
		
		
		HttpEntity<MultiValueMap<String, Object>> requestEntity
		 = new HttpEntity<>(requestBody, headers);
		ResponseEntity<UploadImageResponseRoot> response = restTemplate
				  .postForEntity(uploadURL, requestEntity, UploadImageResponseRoot.class);
		
		UploadImageResponseRoot body = response.getBody();
		
		ImageInfoDTO imageInfoDTO = new ImageInfoDTO();
		imageInfoDTO.setDeletehash(body.getData().getDeletehash());
		imageInfoDTO.setImageId(body.getData().getId());
		imageInfoDTO.setLink(body.getData().getLink());
		imageInfoDTO.setUserId(userId);
		
		imageInfoDTO = imageReposotory.save(imageInfoDTO);
		return imageInfoDTO.getId();
	}
	
	
	@GetMapping("/view/{userId}")
	public List<Map<String,Object>> view(@PathVariable("userId") Long userId){
		
		List<ImageInfoDTO> findByUserId = imageReposotory.findByUserId(userId);
		
		return convert(findByUserId);
	}
	
	
	@GetMapping(value = "/view/{userId}/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] view(@PathVariable("userId") Long userId, @PathVariable("imageId") Long imageId){
		
		ImageInfoDTO imageInfoDTO = imageReposotory.findByUserIdAndId(userId, imageId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		
		if(!StringUtils.isEmpty(configuration.getAccessToken())) {
			headers.add("Token", configuration.getAccessToken());
		}
		else {
			headers.add("Authorization", "Client-ID "+configuration.getClientid());
		}
		HttpEntity<MultiValueMap<String, Object>> requestEntity
		 = new HttpEntity<>(headers);
		byte[] forObject = restTemplate.getForObject(imageInfoDTO.getLink(), byte[].class);
		return forObject;
	}
	
	private List<Map<String, Object>> convert(List<ImageInfoDTO> findByUserId) {
		if(findByUserId != null) {
			List<Map<String, Object>> objs = new ArrayList<Map<String,Object>>();
			findByUserId.forEach(value->{
				Map<String, Object> map = new HashMap<>();
				map.put("id", value.getId());
				map.put("link", value.getLink());
				objs.add(map);
			});
			return objs;
		}
		return Collections.EMPTY_LIST;
	}


	@DeleteMapping("/delete/{userId}/{imageId}")
	public String delete(@PathVariable("userId") Long userId,
			@PathVariable("imageId") Long imageId){
		Optional<ImageInfoDTO> findById = imageReposotory.findById(imageId);
		if(findById.isPresent()) {
			imageReposotory.deleteById(imageId);
			
			String deleteUrl = "https://api.imgur.com/3/image/"+findById.get().getDeletehash();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			
			if(!StringUtils.isEmpty(configuration.getAccessToken())) {
				headers.add("Token", configuration.getAccessToken());
			}
			else {
				headers.add("Authorization", "Client-ID "+configuration.getClientid());
			}
			HttpEntity<MultiValueMap<String, Object>> requestEntity
			 = new HttpEntity<>(headers);
			ResponseEntity<Object> exchange = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, requestEntity, Object.class);
			if(exchange.getStatusCodeValue() != 200) {
				return "Error in deleted";
			}
		}
		
		return "deleted";
	}
}
