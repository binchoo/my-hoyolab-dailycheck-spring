package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.user.daos.LoginUserDao;
import org.binchoo.genshin.dailycheck.user.entities.LoginToken;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.binchoo.genshin.dailycheck.user.repos.LoginUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginUserDaoTest {

    @Autowired
    LoginUserRepository repository;

    @Autowired
    LoginUserDao service;

    @Test
    public void initialDataNotEmpty() {
        List<LoginUser> data = repository.findAll();
        assertThat(data.size() > 0);
    }

    @Test
    public void initialDataNameIsValid() {
        LoginUser owner = repository.findByName("binchoo").get();
        assertThat(owner).isNotNull();
        assertThat(owner.getId()).isEqualTo(1L);
    }

    @Test
    public void uniqueContraintIsValid() {
        LoginToken duplicatingToken = new LoginToken("123", "456");
        LoginUser owner1 = new LoginUser();
        owner1.setName("abc");
        owner1.setLoginToken(duplicatingToken);

        LoginUser owner2 = new LoginUser();
        owner2.setName("ABC");
        owner2.setLoginToken(duplicatingToken);

        repository.save(owner1);
        assertThatThrownBy(()->{repository.save(owner2);});
    }
}
