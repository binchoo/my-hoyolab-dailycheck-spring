package org.binchoo.genshin.dailycheck.services;

import org.binchoo.genshin.dailycheck.entities.LoginTokenOwner;

import java.util.List;

public interface LoginTokenService {
    List<LoginTokenOwner> findAll();
    LoginTokenOwner findByName(String name);
    void add(LoginTokenOwner owner);
    void update(LoginTokenOwner user, String name, String ltoken, String ltuid);
    void delete(LoginTokenOwner user);
}
