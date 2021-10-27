package org.binchoo.genshin.dailycheck.user.daos;

import org.binchoo.genshin.dailycheck.user.entities.LoginToken;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.binchoo.genshin.dailycheck.user.repos.LoginUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Transactional
@Service
public class JpaLoginUserDao implements LoginUserDao {

    LoginUserRepository repository = null;

    public JpaLoginUserDao(LoginUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(@NotNull LoginUser owner) {
        repository.save(owner);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoginUser> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public LoginUser findByName(String name) {
        return repository.findByName(name).orElseThrow(()-> new IllegalArgumentException());
    }

    @Override
    public void update(LoginUser user, String name, String ltoken, String ltuid) {
        LoginUser tmp = repository.findByName(name).orElseThrow(()-> new IllegalArgumentException());

        if (tmp.getId() != user.getId())
            throw new IllegalStateException("Entity id mismathes.");

        tmp.setName(name);
        tmp.setLoginToken(new LoginToken(ltoken, ltuid));

        repository.save(tmp);
    }

    @Override
    public void delete(LoginUser user) {
        LoginUser tmp = repository.findById(user.getId()).orElseThrow(()->new IllegalArgumentException());
        repository.delete(tmp);
    }
}
