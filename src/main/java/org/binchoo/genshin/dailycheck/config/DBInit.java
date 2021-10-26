package org.binchoo.genshin.dailycheck.config;

import org.binchoo.genshin.dailycheck.entities.LoginToken;
import org.binchoo.genshin.dailycheck.entities.LoginTokenOwner;
import org.binchoo.genshin.dailycheck.repos.LoginTokenOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DBInit {

    @Autowired
    LoginTokenOwnerRepository repository;

    @PostConstruct
    public void initDatabase() {
        LoginTokenOwner user = new LoginTokenOwner();
        user.setName("binchoo");
        user.setLoginToken(new LoginToken("rN0anFtemzPu8vfYLW0hkLkysJeidRk3vN6YGtjA", "77407897"));
        repository.save(user);
    }
}
