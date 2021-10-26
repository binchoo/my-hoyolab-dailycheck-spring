package org.binchoo.genshin.dailycheck.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
public class LoginTokenOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Length(min=1, max=20)
    String name;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    LoginToken loginToken;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LoginToken getLoginToken() {
        return loginToken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLoginToken(LoginToken loginToken) {
        this.loginToken = loginToken;
    }
}
