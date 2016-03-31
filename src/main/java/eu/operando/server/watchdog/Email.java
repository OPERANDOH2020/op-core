/*
 * Copyright (c) 2016 Oxford Computer Consultants Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    Matthew Gallagher (Oxford Computer Consultants) - Creation.
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */
package eu.operando.server.watchdog;

import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The email object which is used in the Email Services API.
 */
public class Email
{
	private int sender = -1;
	private Vector<Integer> to = new Vector<Integer>();  
	private Vector<Integer> cc = new Vector<Integer>();
	private Vector<Integer> bcc = new Vector<Integer>();
	private String content = "";
	private Vector<Attachment> attachments = new Vector<Attachment>();
	
	/**
	 * Zero-argument constructor for JAXB.
	 */
	public Email(){}
	
	public Email(Integer sender, Vector<Integer> to, Vector<Integer> cc, Vector<Integer> bcc, String content, Vector<Attachment> attachments)
	{
		this.sender = sender;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.content = content;
		this.attachments = attachments;
	}

	public int getSender()
	{
		return sender;
	}
	public void setSender(int sender)
	{
		this.sender = sender;
	}
	public Vector<Integer> getTo()
	{
		return to;
	}
	public void setTo(Vector<Integer> to)
	{
		this.to = to;
	}
	public Vector<Integer> getCc()
	{
		return cc;
	}
	public void setCc(Vector<Integer> cc)
	{
		this.cc = cc;
	}
	public Vector<Integer> getBcc()
	{
		return bcc;
	}
	public void setBcc(Vector<Integer> bcc)
	{
		this.bcc = bcc;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Vector<Attachment> getAttachments()
	{
		return attachments;
	}
	public void setAttachments(Vector<Attachment> attachments)
	{
		this.attachments = attachments;
	}	
}
