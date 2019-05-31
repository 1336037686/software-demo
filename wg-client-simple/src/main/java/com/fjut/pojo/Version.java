package com.fjut.pojo;

/**
 * 更新版本信息实体类
 * @author LGX
 *
 */
public class Version {
	private double version;
	private String filePath;
	private long fileSize;
	
	public Version() {
		
	}
	public Version(double version, String filePath, long fileSize) {
		this.version = version;
		this.filePath = filePath;
		this.fileSize = fileSize;
	}
	public double getVersion() {
		return version;
	}
	public void setVersion(double version) {
		this.version = version;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
}
