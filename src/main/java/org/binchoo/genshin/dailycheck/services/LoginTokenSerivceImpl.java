package org.binchoo.genshin.dailycheck.services;

import org.binchoo.genshin.dailycheck.entities.LoginToken;
import org.binchoo.genshin.dailycheck.entities.LoginTokenOwner;
import org.binchoo.genshin.dailycheck.repos.LoginTokenOwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Transactional
@Service
public class LoginTokenSerivceImpl implements LoginTokenService {

    LoginTokenOwnerRepository repository = null;

    public LoginTokenSerivceImpl(LoginTokenOwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(@NotNull LoginTokenOwner owner) {
        repository.save(owner);
    }

    @Transactional(readOnly = true)
    @Override
    public List<LoginTokenOwner> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public LoginTokenOwner findByName(String name) {
        return repository.findByName(name).orElseThrow(()-> new IllegalArgumentException());
    }

    @Override
    public void update(LoginTokenOwner user, String name, String ltoken, String ltuid) {
        LoginTokenOwner tmp = repository.findByName(name).orElseThrow(()-> new IllegalArgumentException());

        if (tmp.getId() != user.getId())
            throw new IllegalStateException("Entity id mismathes.");

        tmp.setName(name);
        tmp.setLoginToken(new LoginToken(ltoken, ltuid));

        repository.save(tmp);
    }

    @Override
    public void delete(LoginTokenOwner user) {
        LoginTokenOwner tmp = repository.findById(user.getId()).orElseThrow(()->new IllegalArgumentException());
        repository.delete(tmp);
    }
}
