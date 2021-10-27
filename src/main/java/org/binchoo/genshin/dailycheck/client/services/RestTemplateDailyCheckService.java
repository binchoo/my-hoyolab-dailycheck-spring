package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.user.daos.JpaLoginUserDao;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.binchoo.genshin.dailycheck.client.vos.MonthlyUserChecksResponse;
import org.binchoo.genshin.dailycheck.client.vos.UserCheckedInResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class RestTemplateDailyCheckService implements DailyCheckService {

    final Logger logger = LoggerFactory.getLogger(RestTemplateDailyCheckService.class);

    RestTemplate restTemplate;

    JpaLoginUserDao loginUserDao;

    TaskExecutor asyncTaskExecutor;

    @Value("${hoyolab.api.get-user-checks-on-this-month.url}")
    String GET_USER_CHEKCS_ON_THIS_MONTH;

    @Value("${hoyolab.api.post-user-checked-in.url}")
    String POST_USER_CHECKED_IN;

    public RestTemplateDailyCheckService(RestTemplate restTemplate,
                                         JpaLoginUserDao loginUserDao, TaskExecutor asyncTaskExecutor) {
        this.restTemplate = restTemplate;
        this.loginUserDao = loginUserDao;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Override
    public Optional<MonthlyUserChecksResponse> getMonthlyDailyCheckStatus(LoginUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", user.getLoginCookieString());

        HttpEntity requestEntity = new HttpEntity(null, headers);

        ResponseEntity<MonthlyUserChecksResponse> response = restTemplate.exchange(
                GET_USER_CHEKCS_ON_THIS_MONTH, HttpMethod.GET, requestEntity, MonthlyUserChecksResponse.class);

        return HttpStatus.OK == response.getStatusCode() ?
                Optional.of(response.getBody()) : Optional.empty();
    }

    @Override
    public Optional<UserCheckedInResponse> postUserCheckedInToday(LoginUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", user.getLoginCookieString());

        HttpEntity requestEntity = new HttpEntity(null, headers);

        ResponseEntity<UserCheckedInResponse> response = restTemplate.exchange(
                POST_USER_CHECKED_IN, HttpMethod.POST, requestEntity, UserCheckedInResponse.class);

        return HttpStatus.OK == response.getStatusCode() ?
                Optional.of(response.getBody()) : Optional.empty();
    }

    @Scheduled(cron = "0 0 8 * * *") // every 8am:00:00
    @Override
    public void batchScheduledDailyCheckInToday() {
        List<LoginUser> users = loginUserDao.findAll();

        for (LoginUser user : users) {
            logger.info("Executing scheduled daily-check for " + user);
            asyncTaskExecutor.execute(()-> postUserCheckedInToday(user));
        }
    }
}
