package com.system.models;

import com.system.enums.ChannelType;

import java.util.EnumMap;
import java.util.Map;

public class NotificationPreference {

    private final Map<ChannelType, Boolean> channelPreferences;

    public NotificationPreference() {
        this.channelPreferences = new EnumMap<>(ChannelType.class);
    }

    public void enableChannel(ChannelType channelType) {
        channelPreferences.put(channelType, true);
    }

    public void disableChannel(ChannelType channelType) {
        channelPreferences.put(channelType, false);
    }

    public boolean isChannelEnabled(ChannelType channelType) {
        return channelPreferences.getOrDefault(channelType, false);
    }

    @Override
    public String toString() {
        return "NotificationPreference{" +
                "channelPreferences=" + channelPreferences +
                '}';
    }
}