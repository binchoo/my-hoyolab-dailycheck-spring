package org.binchoo.genshin.dailycheck.services;

import org.binchoo.genshin.dailycheck.entities.LoginToken;
import org.binchoo.genshin.dailycheck.entities.LoginTokenOwner;
import org.binchoo.genshin.dailycheck.repos.LoginTokenOwnerRepository;
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
public class LoginTokenServiceTest {

    @Autowired
    LoginTokenOwnerRepository repository;

    @Autowired
    LoginTokenService service;

    @Test
    public void initialDataNotEmpty() {
        List<LoginTokenOwner> data = repository.findAll();
        assertThat(data.size() > 0);
    }

    @Test
    public void initialDataNameIsValid() {
        LoginTokenOwner owner = repository.findByName("binchoo").get();
        assertThat(owner).isNotNull();
        assertThat(owner.getId()).isEqualTo(1L);
    }

    @Test
    public void uniqueContraintIsValid() {
        LoginToken duplicatingToken = new LoginToken("123", "456");
        LoginTokenOwner owner1 = new LoginTokenOwner();
        owner1.setName("abc");
        owner1.setLoginToken(duplicatingToken);

        LoginTokenOwner owner2 = new LoginTokenOwner();
        owner2.setName("ABC");
        owner2.setLoginToken(duplicatingToken);

        repository.save(owner1);
        assertThatThrownBy(()->{repository.save(owner2);});
    }
}
