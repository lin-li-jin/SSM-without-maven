package com.talent.forex.bean.model;

import java.io.File;

public class FileUploadModel {
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	private Integer classId;
	
	public File [] fileArray;
	
	public String [] fileArrayFileName;
	
	public String [] fileArrayContentType;
	
	public String [] fileType;
	
	

	public File[] getFileArray() {
		return fileArray;
	}

	public void setFileArray(File[] fileArray) {
		this.fileArray = fileArray;
	}

	public String[] getFileArrayContentType() {
		return fileArrayContentType;
	}

	public void setFileArrayContentType(String[] fileArrayContentType) {
		this.fileArrayContentType = fileArrayContentType;
	}

	public String[] getFileArrayFileName() {
		return fileArrayFileName;
	}

	public void setFileArrayFileName(String[] fileArrayFileName) {
		this.fileArrayFileName = fileArrayFileName;
		/*if(fileArrayFileName != null){
			for(int i=0; i<fileArrayFileName.length; i++){
				fileArrayFileName[i] = StringUtil.gbkToIso(fileArrayFileName[i]);
			}
		}*/
	}

	public String[] getFileType() {
		return fileType;
	}

	public void setFileType(String[] fileType) {
		this.fileType = fileType;
	}

	public void setImportFlag(String string) {
		// TODO Auto-generated method stub
		
	}
}
