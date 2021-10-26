package org.binchoo.genshin.dailycheck.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class LoginToken {

    @EmbeddedId
    private LoginTokenId loginTokenId;

    public LoginToken() {

    }

    public LoginToken(String ltoken, String ltuid) {
        loginTokenId = new LoginTokenId(ltoken, ltuid);
    }

    public String getLtoken() {
        return loginTokenId.getLtoken();
    }

    public String getLtuid() {
        return loginTokenId.getLtuid();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LoginToken) {
            LoginToken other = (LoginToken) obj;
            return other.getLtoken() == this.getLtoken() && other.getLtuid() == this.getLtuid();
        }
        return false;
    }
}
