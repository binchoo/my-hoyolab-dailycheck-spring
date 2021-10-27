package org.binchoo.genshin.dailycheck.client.entities;

import org.joda.time.DateTime;

public class MonthlyUserChecksResponseData {

    int totalSignDay;

    DateTime today;

    boolean isSign;
    boolean firstBind;
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
