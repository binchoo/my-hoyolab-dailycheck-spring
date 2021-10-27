package org.binchoo.genshin.dailycheck.user.daos;

import org.binchoo.genshin.dailycheck.user.entities.LoginUser;

import java.util.List;

public interface LoginUserDao {
    List<LoginUser> findAll();
    LoginUser findByName(String name);
    void add(LoginUser owner);
    void update(LoginUser user, String name, String ltoken, String ltuid);
    void delete(LoginUser user);
}
