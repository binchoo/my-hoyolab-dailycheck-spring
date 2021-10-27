package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.binchoo.genshin.dailycheck.client.vos.MonthlyUserChecksResponse;
import org.binchoo.genshin.dailycheck.client.vos.DailyUserCheckResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RestTemplateDailyCheckService implements DailyCheckService {

    RestTemplate restTemplate;

    @Value("${hoyolab.api.get-monthly-user-checks.url}")
    String GET_MONTHLY_USER_CHECKS;

    @Value("${hoyolab.api.post-daily-user-check.url}")
    String POST_DAILY_USER_CHECK;

    public RestTemplateDailyCheckService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<MonthlyUserChecksResponse> getMonthlyUserChecks(LoginUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", user.getLoginCookieString());

        HttpEntity requestEntity = new HttpEntity(null, headers);

        ResponseEntity<MonthlyUserChecksResponse> response = restTemplate.exchange(
                GET_MONTHLY_USER_CHECKS, HttpMethod.GET, requestEntity, MonthlyUserChecksResponse.class);

        return HttpStatus.OK == response.getStatusCode() ?
                Optional.of(response.getBody()) : Optional.empty();
    }

    @Override
    public Optional<DailyUserCheckResponse> postDailyUserCheck(LoginUser user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cookie", user.getLoginCookieString());

        HttpEntity requestEntity = new HttpEntity(null, headers);

        ResponseEntity<DailyUserCheckResponse> response = restTemplate.exchange(
                POST_DAILY_USER_CHECK, HttpMethod.POST, requestEntity, DailyUserCheckResponse.class);

        return HttpStatus.OK == response.getStatusCode() ?
                Optional.of(response.getBody()) : Optional.empty();
    }
}
