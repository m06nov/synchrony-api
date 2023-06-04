package com.synchrony.app.response.pojo; 
public class UploadImageResponseRoot{
    public UploadImageResponseData data;
    public boolean success;
    public int status;
	public UploadImageResponseData getData() {
		return data;
	}
	public void setData(UploadImageResponseData data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UploadImageResponseRoot [data=" + data + ", success=" + success + ", status=" + status + "]";
	}
    
    
}
