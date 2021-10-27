package org.binchoo.genshin.dailycheck.client.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonthlyUserChecksResponse {

    int retcode;

    MonthlyUserChecksResponseData data;

    String message;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public MonthlyUserChecksResponseData getData() {
        return data;
    }

    public void setData(MonthlyUserChecksResponseData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MonthlyUserChecksResponseData {

        @JsonProperty("total_sign_day")
        int totalSignDay;

        DateTime today;

        @JsonProperty("is_sign")
        boolean isSign;

        @JsonProperty("first_bind")
        boolean firstBind;

        @JsonProperty("is_sub")
        boolean isSub;

        String region;

        public int getTotalSignDay() {
            return totalSignDay;
        }

        public void setTotalSignDay(int totalSignDay) {
            this.totalSignDay = totalSignDay;
        }

        public DateTime getToday() {
            return today;
        }

        public void setToday(DateTime today) {
            this.today = today;
        }

        public boolean isSign() {
            return isSign;
        }

        public void setSign(boolean sign) {
            isSign = sign;
        }

        public boolean isFirstBind() {
            return firstBind;
        }

        public void setFirstBind(boolean firstBind) {
            this.firstBind = firstBind;
        }

        public boolean isSub() {
            return isSub;
        }

        public void setSub(boolean sub) {
            isSub = sub;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }
    }
}