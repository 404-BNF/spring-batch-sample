package dev.sk.springbatchsamplev5upgrade.scheduler;

import dev.sk.springbatchsamplev5upgrade.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class Scheduler {
    @Autowired
    MainController mainController;

    @Scheduled(cron = "0 12 17 * * *", zone = "Asia/Calcutta")
    void schedule(){
        mainController.executeBatchJob();
    }

}
