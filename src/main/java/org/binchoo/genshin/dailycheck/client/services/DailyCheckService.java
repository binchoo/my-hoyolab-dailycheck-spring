package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.client.vos.MonthlyUserChecksResponse;
import org.binchoo.genshin.dailycheck.client.vos.UserCheckedInResponse;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;

import java.util.Optional;

public interface DailyCheckService {
    Optional<MonthlyUserChecksResponse> getMonthlyDailyCheckStatus(LoginUser user);
    Optional<UserCheckedInResponse> postUserCheckedInToday(LoginUser user);
    void batchScheduledDailyCheckInToday();
}
