package org.binchoo.genshin.dailycheck.client.dto;

import org.springframework.lang.Nullable;

public class Hk4eResponse {

    int retcode;

    @Nullable
    Object data;

    String message;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    @Nullable
    public Object getData() {
        return data;
    }

    public void setData(@Nullable Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
