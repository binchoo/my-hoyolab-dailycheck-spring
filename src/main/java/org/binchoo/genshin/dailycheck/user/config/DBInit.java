package org.binchoo.genshin.dailycheck.user.config;

import org.binchoo.genshin.dailycheck.user.entities.LoginToken;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.binchoo.genshin.dailycheck.user.repos.LoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DBInit {

    @Autowired
    LoginUserRepository repository;

    @PostConstruct
    public void initDatabase() {
        LoginUser user = new LoginUser();
        user.setName("binchoo");
        user.setLoginToken(new LoginToken("eidRzPu8vfYLWkrN0anF3vtem0hkLkGtysJN6YjA", "123456789"));
        repository.save(user);
    }
}
