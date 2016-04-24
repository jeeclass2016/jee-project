package com.vietsci.cms.frontend.exception;

/**
 * This class is defined to store message to inform client when insert, update, delete ....
 *
 */
public class Message {
	private String code;
	private String message;
	
	public Message() {
	}
	
	public Message(String code, String message) {
		this.code 		= code;
		this.message 	= message;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
