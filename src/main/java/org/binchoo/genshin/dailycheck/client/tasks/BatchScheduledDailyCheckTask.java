package org.binchoo.genshin.dailycheck.client.tasks;

import org.binchoo.genshin.dailycheck.client.services.DailyCheckService;
import org.binchoo.genshin.dailycheck.client.services.RestTemplateDailyCheckService;
import org.binchoo.genshin.dailycheck.user.daos.LoginUserDao;
import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchScheduledDailyCheckTask {

    final static Logger logger = LoggerFactory.getLogger(RestTemplateDailyCheckService.class);

    static int EPOCHS;

    LoginUserDao loginUserDao;

    DailyCheckService dailyCheckService;

    ThreadPoolTaskExecutor asyncTaskExecutor;

    public BatchScheduledDailyCheckTask(LoginUserDao loginUserDao,
                                        RestTemplateDailyCheckService dailyCheckService,
                                        ThreadPoolTaskExecutor asyncTaskExecutor) {
        this.dailyCheckService = dailyCheckService;
        this.loginUserDao = loginUserDao;
        this.asyncTaskExecutor = asyncTaskExecutor;
    }

    @Scheduled(cron="${scheduled.trigger.cron}")
    public void dailyCheckForAllUser() {
        List<LoginUser> users = loginUserDao.findAll();

        logger.info("dailyCheckForAllUser epcohs: " + EPOCHS++);
        for (LoginUser user: users) {
            asyncTaskExecutor.execute(()-> dailyCheckService.postDailyUserCheck(user));
        }
    }

    static {
        EPOCHS = 0;
    }
}
