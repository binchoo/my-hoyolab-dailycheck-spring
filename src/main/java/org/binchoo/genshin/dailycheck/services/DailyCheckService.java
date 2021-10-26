package org.binchoo.genshin.dailycheck.services;

import org.binchoo.genshin.dailycheck.dto.DailyCheckResult;
import org.binchoo.genshin.dailycheck.entities.LoginTokenOwner;

import java.util.List;

public interface DailyCheckService {
    DailyCheckResult dailyCheck(LoginTokenOwner user);
    List<DailyCheckResult> batchScheduledDailyCheck();
}
