package com.nsa.team10.asgproject.services.dtos;

import java.util.List;
import java.util.Map;

public class Mail
{
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String content;
    private String contentType;
    private List<Object> attachments;
    private Map<String, Object> model;

    public Mail()
    {
        contentType = "text/plain";
        from = "asgsystem@example.com";
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom(String from)
    {
        this.from = from;
    }

    public String getTo()
    {
        return to;
    }

    public void setTo(String to)
    {
        this.to = to;
    }

    public String getCc()
    {
        return cc;
    }

    public void setCc(String cc)
    {
        this.cc = cc;
    }

    public String getBcc()
    {
        return bcc;
    }

    public void setBcc(String bcc)
    {
        this.bcc = bcc;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public List<Object> getAttachments()
    {
        return attachments;
    }

    public void setAttachments(List<Object> attachments)
    {
        this.attachments = attachments;
    }

    public Map<String, Object> getModel()
    {
        return model;
    }

    public void setModel(Map<String, Object> model)
    {
        this.model = model;
    }
}
