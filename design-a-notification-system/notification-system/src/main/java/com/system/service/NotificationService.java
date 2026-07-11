package com.system.service;

import com.system.builder.NotificationBuilder;
import com.system.enums.ChannelType;
import com.system.enums.NotificationStatus;
import com.system.factory.NotificationChannelFactory;
import com.system.models.DeliveryAttempt;
import com.system.models.Notification;
import com.system.models.NotificationTemplate;
import com.system.observer.Event;
import com.system.retry.RetryChain;
import com.system.retry.RetryHandler;
import com.system.strategy.NotificationChannel;

public class NotificationService {

    // Singleton — volatile for thread safety
    private static volatile NotificationService instance;

    // Retry chain — Chain of Responsibility
    private final RetryHandler retryHandler;

    // Private constructor
    private NotificationService() {
        retryHandler = RetryChain.createChain();
    }

    // Double checked locking
    public static NotificationService getInstance() {
        if (instance == null) {
            synchronized (NotificationService.class) {
                if (instance == null) {
                    instance = new NotificationService();
                }
            }
        }
        return instance;
    }

    // MAIN SEND METHOD — uses State Pattern
    public void sendNotification(Notification notification) {

        // Step 1 — Check user preference
        boolean channelEnabled = notification
                .getUser()
                .getNotificationPreference()
                .isChannelEnabled(
                        notification.getChannelType());

        if (!channelEnabled) {
            System.out.println(
                    "[SKIPPED] Channel " +
                            notification.getChannelType() +
                            " disabled by user: " +
                            notification.getUser().getName());
            notification.cancel(); // State Pattern
            return;
        }

        // Step 2 — Get channel via Factory
        NotificationChannel channel =
                NotificationChannelFactory
                        .getChannel(notification.getChannelType());

        // Step 3 — State Pattern: move to SENDING
        notification.send(); // PENDING → SENDING

        // Step 4 — Track delivery attempt
        DeliveryAttempt attempt = new DeliveryAttempt(
                notification.getNotificationId(), 1);

        try {
            // Step 5 — Actually send
            channel.send(notification);

            // Step 6 — Mark success
            attempt.markSuccess();

            // Step 7 — State Pattern: SENDING → DELIVERED
            notification.markDelivered();

            System.out.println(
                    "[DELIVERED] Notification: " +
                            notification.getNotificationId());

        } catch (Exception e) {

            // Step 6 — Mark failure with reason
            attempt.markFailure(e.getMessage());

            // Step 7 — State Pattern: SENDING → FAILED
            notification.markFailed();

            System.out.println(
                    "[FAILED] Notification: " +
                            notification.getNotificationId() +
                            " Reason: " + e.getMessage());

            // Step 8 — Trigger retry chain
            retryHandler.retry(notification, 1);
        }
    }

    // Called by Observer when event fires
    public void processEvent(Event event) {
        System.out.println(
                "[EVENT] Received: " +
                        event.getEventType());

        // Lookup template based on event type
        NotificationTemplate template =
                getTemplateForEvent(event);

        if (template == null) {
            System.out.println(
                    "[SKIPPED] No template for: " +
                            event.getEventType());
            return;
        }

        // Extract user from event data
        // In production → event.getData()
        // would be cast to proper type
        System.out.println(
                "[EVENT] Template found: " +
                        template.getTemplateName() +
                        ". Notification would be built and sent.");
    }

    // Template lookup by event type
    private NotificationTemplate getTemplateForEvent(
            Event event) {
        switch (event.getEventType()) {
            case ORDER_PLACED:
                return new NotificationTemplate(
                        "ORDER_PLACED",
                        "Your order has been placed!");

            case PAYMENT_SUCCESS:
                return new NotificationTemplate(
                        "PAYMENT_SUCCESS",
                        "Payment received. Thank you!");

            case PAYMENT_FAILED:
                return new NotificationTemplate(
                        "PAYMENT_FAILED",
                        "Payment failed. Please retry.");

            case LOGIN:
                return new NotificationTemplate(
                        "LOGIN_ALERT",
                        "New login detected on your account.");

            case PROMOTION:
                return new NotificationTemplate(
                        "PROMO",
                        "Special offer just for you!");

            default:
                return null;
        }
    }
}