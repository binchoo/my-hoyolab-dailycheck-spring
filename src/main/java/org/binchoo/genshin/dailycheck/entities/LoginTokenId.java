package org.binchoo.genshin.dailycheck.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class LoginTokenId implements Serializable {

    private String ltoken;
    private String ltuid;

    public LoginTokenId() {

    }

    public LoginTokenId(String ltoken, String ltuid) {
        this.ltoken = ltoken;
        this.ltuid = ltuid;
    }

    public String getLtoken() {
        return ltoken;
    }

    public String getLtuid() {
        return ltuid;
    }
}
