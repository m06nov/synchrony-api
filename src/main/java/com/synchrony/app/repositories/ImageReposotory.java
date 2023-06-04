package com.synchrony.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synchrony.app.dto.ImageInfoDTO;

public interface ImageReposotory extends JpaRepository<ImageInfoDTO, Long> {
	List<ImageInfoDTO> findByUserId(Long userId);
	ImageInfoDTO findByUserIdAndId(Long userId, Long imageId);
}
