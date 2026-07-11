package com.system.observer;

public class NotificationObserver
        implements NotificationEventListener {


    private final NotificationService notificationService;


    public NotificationObserver( NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @Override
    public void onEvent(Event event) {

        System.out.println(
                "Notification observer received event: "
                        + event.getEventType()
        );

        notificationService.processEvent(event);
    }
}
