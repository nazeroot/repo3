package com.itheima.execption;

public class CheckItemExecption extends RuntimeException {
    public CheckItemExecption() {
        super();
    }

    public CheckItemExecption(String message) {
        super(message);
    }

    public CheckItemExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckItemExecption(Throwable cause) {
        super(cause);
    }

    protected CheckItemExecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
