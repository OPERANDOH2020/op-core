package eu.operando.server.watchdog;
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


import java.util.Vector;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The email object which is used in the Email Services API.
 */
@XmlRootElement
public class EmailNotification
{
	private Vector<String> to = new Vector<String>();  
	private Vector<String> cc = new Vector<String>();
	private Vector<String> bcc = new Vector<String>();
	private String content = "";
	private String subject = "";
	private Vector<Attachment> attachments = new Vector<Attachment>();
	
	/**
	 * Zero-argument constructor for JAXB.
	 */
	public EmailNotification(){}
	
	public EmailNotification(Vector<String> to, Vector<String> cc, Vector<String> bcc, String content, String subject, Vector<Attachment> attachments)
	{
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.content = content;
		this.subject  = subject;
		this.attachments = attachments;
	}
	
	
	public Vector<String> getTo()
	{
		return to;
	}
	public void setTo(Vector<String> to)
	{
		this.to = to;
	}
	public Vector<String> getCc()
	{
		return cc;
	}
	public void setCc(Vector<String> cc)
	{
		this.cc = cc;
	}
	public Vector<String> getBcc()
	{
		return bcc;
	}
	public void setBcc(Vector<String> bcc)
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
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
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
