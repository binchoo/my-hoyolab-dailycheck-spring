package org.binchoo.genshin.dailycheck.client.vos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyUserCheckResponse {

    int retcode;

    String message;

    UserCheckedInResponseData data;

    public int getRetcode() {
        return retcode;
    }

    public UserCheckedInResponseData getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class UserCheckedInResponseData {

        String code;

        public String getCode() {
            return code;
        }
    }

}
