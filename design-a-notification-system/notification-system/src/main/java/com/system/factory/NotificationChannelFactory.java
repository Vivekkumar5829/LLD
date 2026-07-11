package com.system.factory;

import com.system.strategy.*;
import com.system.enums.ChannelType;

public class NotificationChannelFactory {

    private NotificationChannelFactory() {
        // Prevent object creation
    }

    public static NotificationChannel getChannel(ChannelType channelType) {

        switch (channelType) {

            case EMAIL:
                return new EmailChannel();

            case SMS:
                return new SMSChannel();

            case PUSH:
                return new PushChannel();

            case WHATSAPP:
                return new WhatsAppChannel();

            default:
                throw new IllegalArgumentException(
                        "Unsupported channel type: " + channelType
                );
        }
    }
}
