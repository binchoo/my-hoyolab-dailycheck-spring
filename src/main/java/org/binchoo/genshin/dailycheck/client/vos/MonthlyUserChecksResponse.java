package org.binchoo.genshin.dailycheck.client.vos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonthlyUserChecksResponse {

    int retcode;
    String message;

    MonthlyUserChecksResponseData data;

    public int getRetcode() {
        return retcode;
    }

    public MonthlyUserChecksResponseData getData() {
        return data;
    }

    public String getMessage() {
        return message;
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

        public DateTime getToday() {
            return today;
        }

        public boolean isSign() {
            return isSign;
        }

        public boolean isFirstBind() {
            return firstBind;
        }

        public boolean isSub() {
            return isSub;
        }

        public String getRegion() {
            return region;
        }
    }
}
