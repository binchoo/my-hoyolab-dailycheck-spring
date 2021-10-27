package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.binchoo.genshin.dailycheck.client.dto.MonthlyUserChecksResponseData;
import org.binchoo.genshin.dailycheck.client.dto.UserCheckedInResponseData;

import java.util.List;
import java.util.Optional;

public interface DailyCheckService {
    Optional<MonthlyUserChecksResponseData> getMonthlyDailyCheckStatus(LoginUser user);
    Optional<UserCheckedInResponseData> postUserCheckedInToday(LoginUser user);
    List<UserCheckedInResponseData> batchScheduledDailyCheckInToday();
}
