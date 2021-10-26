package org.binchoo.genshin.dailycheck.services;

import org.binchoo.genshin.dailycheck.dto.DailyCheckResult;
import org.binchoo.genshin.dailycheck.entities.LoginTokenOwner;
import org.binchoo.genshin.dailycheck.repos.LoginTokenOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@PropertySource("classpath:gateway/hoyolab-gateway.properties")
@Service
public class DailyCheckServiceImpl implements DailyCheckService {

    @Autowired
    LoginTokenOwnerRepository repository;

    //TODO - DI for RestTemplate
    RestTemplate restTemplate;

    @Value("${hoyolab.url.dailycheck}")
    private String url;

    @Value("${hoyolab.method.dailycheck}")
    private String method;

    @Async
    @Override
    public DailyCheckResult dailyCheck(LoginTokenOwner user) {
        return null; //TODO - Implementation
    }

    @Scheduled
    @Override
    public List<DailyCheckResult> batchScheduledDailyCheck() {
        List<LoginTokenOwner> users;
        return null; //TODO - Implementation
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
