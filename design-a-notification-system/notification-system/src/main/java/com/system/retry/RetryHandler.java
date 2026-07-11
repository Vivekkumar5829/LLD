package com.system.retry;

import com.system.models.Notification;

public abstract class RetryHandler {

    protected RetryHandler nextHandler;

    public void setNextHandler(RetryHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void retry(Notification notification, int attempt);
}