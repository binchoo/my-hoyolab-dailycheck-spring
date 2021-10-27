package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.user.daos.JpaLoginUserDao;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.binchoo.genshin.dailycheck.client.entities.MonthlyUserChecksResponseData;
import org.binchoo.genshin.dailycheck.client.entities.UserCheckedInResponseData;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RestTemplateDailyCheckService implements DailyCheckService {

    RestTemplate restTemplate;

    JpaLoginUserDao loginUserDao;

    TaskExecutor asyncTaskExecutor;

    public RestTemplateDailyCheckService(RestTemplate restTemplate,
                                         JpaLoginUserDao loginUserDao, TaskExecutor asyncTaskExecutor) {
        this.restTemplate = restTemplate;
        this.loginUserDao = loginUserDao;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Override
    public Optional<MonthlyUserChecksResponseData> getMonthlyDailyCheckStatus(LoginUser user) {
        return Optional.empty();
    }

    @Override
    public Optional<UserCheckedInResponseData> postUserCheckedInToday(LoginUser user) {
        return Optional.empty();
    }

    @Scheduled
    @Override
    public List<UserCheckedInResponseData> batchScheduledDailyCheckInToday() {
        return null;
    }
}
