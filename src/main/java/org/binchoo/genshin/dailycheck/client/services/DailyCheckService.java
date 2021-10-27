package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.client.vos.MonthlyUserChecksResponse;
import org.binchoo.genshin.dailycheck.client.vos.DailyUserCheckResponse;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;

import java.util.Optional;

public interface DailyCheckService {
    Optional<MonthlyUserChecksResponse> getMonthlyUserChecks(LoginUser user);
    Optional<DailyUserCheckResponse> postDailyUserCheck(LoginUser user);
}
