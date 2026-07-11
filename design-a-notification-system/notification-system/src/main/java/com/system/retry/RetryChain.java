package com.system.retry;

public class RetryChain {


    public static RetryHandler createChain(){

        RetryHandler immediate =
                new ImmediateRetryHandler();

        RetryHandler delayed =
                new DelayedRetryHandler();


        immediate.setNextHandler(delayed);

        return immediate;
    }
}