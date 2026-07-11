package com.system.models;

import java.util.UUID;

public class NotificationTemplate {

    private final String templateId;
    private final String templateName;
    private final String content;

    public NotificationTemplate(String templateName, String content) {
        this.templateId = UUID.randomUUID().toString();
        this.templateName = templateName;
        this.content = content;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "NotificationTemplate{" +
                "templateId='" + templateId + '\'' +
                ", templateName='" + templateName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}