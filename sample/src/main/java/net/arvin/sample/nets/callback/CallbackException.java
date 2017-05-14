package net.arvin.sample.nets.callback;

import java.io.IOException;

/**
 * Created by arvinljw on 17/5/15 01:14
 * Function：
 * Desc：
 */
public class CallbackException extends IOException {
    public static final int TOKEN_BAD = 1000;

    private int code;

    public CallbackException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
