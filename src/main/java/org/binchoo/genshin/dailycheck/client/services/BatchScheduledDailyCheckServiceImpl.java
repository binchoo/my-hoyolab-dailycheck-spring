package org.binchoo.genshin.dailycheck.client.services;

import org.binchoo.genshin.dailycheck.user.daos.LoginUserDao;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchScheduledDailyCheckServiceImpl implements BatchScheduledDailyCheckService {

    final static Logger logger = LoggerFactory.getLogger(RestTemplateDailyCheckService.class);

    public static int EPOCHS;

    LoginUserDao loginUserDao;

    DailyCheckService dailyCheckService;

    ThreadPoolTaskExecutor asyncTaskExecutor;

    public BatchScheduledDailyCheckServiceImpl(LoginUserDao loginUserDao,
                                               RestTemplateDailyCheckService dailyCheckService,
                                               ThreadPoolTaskExecutor asyncTaskExecutor) {
        this.dailyCheckService = dailyCheckService;
        this.loginUserDao = loginUserDao;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Scheduled(cron="${scheduled.trigger.cron}")
    @Override
    public void dailyCheckForAllUser() {
        List<LoginUser> users = loginUserDao.findAll();

        for (LoginUser user: users) {
            asyncTaskExecutor.execute(()-> dailyCheckService.postDailyUserCheck(user));
        }

        EPOCHS++;
    }

    static {
        EPOCHS = 0;
    }
}
