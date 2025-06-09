package com.vasworks.npc.struts.agent;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.vasworks.npc.model.ImageFile;
import com.vasworks.npc.struts.AgentAction;

public class ImageDisplayAction extends AgentAction {
	public static final Log LOG = LogFactory.getLog(ImageDisplayAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 4138071251332140102L;	
	
	private String contentDisposition;
	
	private InputStream inputStream;
	
	private String contentType;
	
	private Long fileId;
	
	private String fileExtension;

	@Override
	public String execute() {
		LOG.debug("execute()");
		
		StringBuilder b = new StringBuilder("inline;filename=");		
		b.append("\"");
		b.append(System.nanoTime());
		b.append(".");
		b.append(fileExtension != null ? fileExtension : "jpg");
		b.append("\"");
		
		contentDisposition = b.toString();
		
		byte[] rawFileData = getFilePhoto();
		
		if(rawFileData != null) {
			inputStream = new ByteArrayInputStream(rawFileData);
		}
		
		return SUCCESS;
	}

	public byte[] getFilePhoto() {
		LOG.debug("getFilePhoto()");
		byte[] photoData = null;
		if(fileId != null) {
			ImageFile data = (ImageFile) agentService.find(fileId, ImageFile.class);
			
			contentType = contentType == null ? "image/jpeg" : contentType;
			
			fileExtension = fileExtension == null ? "jpg" : fileExtension;
			
			photoData = data.getImageData();
		}
		
		if(photoData != null) {
			LOG.debug("photoData.length: " + photoData.length);
		}
		
		return photoData;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}
	
	public String getContentType() {
		return contentType;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long memberId) {
		this.fileId = memberId;
	}
}
