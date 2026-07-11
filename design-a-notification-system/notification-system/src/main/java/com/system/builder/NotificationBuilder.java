package com.system.builder;

import com.system.enums.ChannelType;
import com.system.enums.Priority;
import com.system.models.Notification;
import com.system.models.NotificationTemplate;
import com.system.models.User;

public class NotificationBuilder {

    private User user;
    private NotificationTemplate template;
    private ChannelType channelType;
    private Priority priority;

    public NotificationBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public NotificationBuilder setTemplate(NotificationTemplate template) {
        this.template = template;
        return this;
    }


    public NotificationBuilder setChannelType(ChannelType channelType) {
        this.channelType = channelType;
        return this;
    }


    public NotificationBuilder setPriority(Priority priority) {
        this.priority = priority;
        return this;
    }


    public Notification build() {

        return new Notification(
                user,
                template,
                channelType,
                priority
        );
    }

}
