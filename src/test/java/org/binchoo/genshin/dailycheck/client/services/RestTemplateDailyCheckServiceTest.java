package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.client.vos.MonthlyUserChecksResponse;
import org.binchoo.genshin.dailycheck.client.vos.DailyUserCheckResponse;
import org.binchoo.genshin.dailycheck.user.entities.LoginToken;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateDailyCheckServiceTest {

    @Autowired
    DailyCheckService service;

    private LoginUser getValidLoginUser() {
        LoginUser user = new LoginUser();
        user.setName("binchoo");
        user.setLoginToken(new LoginToken("ThQLL1uvhAMwVFVGBYdoHJLtYDtsCW9ID19JEcjL", "123725082"));
        return user;
    }

    private LoginUser getInvalidTestUser() {
        LoginUser user = new LoginUser();
        user.setName("binchoo");
        user.setLoginToken(new LoginToken("EcFQLL1uvsCW9IDVGBYdoHThhAMwVJJLtYDt19jL", "132451211"));
        return user;
    }

    @Test
    public void getMonthlyDailyCheckStatus_response_OK() {
        LoginUser user = getValidLoginUser();

        MonthlyUserChecksResponse response = service.getMonthlyUserChecks(user).orElseThrow(()-> new RuntimeException());
        assertThat(response.getRetcode()).isEqualTo(0);
        assertThat(response.getMessage()).isEqualTo("OK");

        MonthlyUserChecksResponse.MonthlyUserChecksResponseData data = response.getData();
        assertThat(data.getTotalSignDay()).isGreaterThan(-1);
    }

    @Test
    public void postUserCheckedInToday_response_OK() {
        LoginUser user = getValidLoginUser();

        DailyUserCheckResponse response = service.postDailyUserCheck(user).orElseThrow(()-> new RuntimeException());
        assertThat(response.getRetcode()).isLessThan(0);
        assertThat(response.getMessage()).contains("already");
        assertThat(response.getData()).isNull();
    }

    @Test
    public void getMonthlyDailyCheckStatus_response_OK_invalid_user() {
        LoginUser user = getInvalidTestUser();

        MonthlyUserChecksResponse response = service.getMonthlyUserChecks(user).orElseThrow(()-> new RuntimeException());
        assertThat(response.getRetcode()).isEqualTo(-100);
        assertThat(response.getMessage()).contains("Not logged in");
        assertThat(response.getData()).isNull();
    }

    @Test
    public void postUserCheckedInToday_response_OK_invalid_user() {
        LoginUser user = getInvalidTestUser();

        DailyUserCheckResponse response = service.postDailyUserCheck(user).orElseThrow(()-> new RuntimeException());
        assertThat(response.getRetcode()).isEqualTo(-100);
        assertThat(response.getMessage()).contains("Not logged in");
        assertThat(response.getData()).isNull();
    }
}