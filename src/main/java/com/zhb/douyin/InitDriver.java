package com.zhb.douyin;

import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/3 9:33
 * @Version 1.0
 */
@Component
public class InitDriver implements CommandLineRunner {

    @Autowired
    ChromeDriver driver;

    @Override
    public void run(String... args) throws Exception {
        driver.get("http://localhost:8081/");
    }
}
