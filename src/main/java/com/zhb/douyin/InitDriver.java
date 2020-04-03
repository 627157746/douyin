package com.zhb.douyin;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/4/3 9:33
 * @Version 1.0
 */
@Component
@Slf4j
public class InitDriver implements CommandLineRunner {

    private final static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";

    private final static String windowsWebDriverPath = "D:\\develop\\chrome-driver\\chromedriver.exe";

    private final static String linuxWebDriverPath = "/usr/bin/chromedriver";

    private final static String linuxChromePath = "/opt/google/chrome/chrome";

    private final static boolean isLinux = true;

    private ChromeDriver driver;

    public ChromeDriver getChromeDriver() {
        System.out.println(driver);

        if (isLinux) {
            return linuxChromeDriver();
        }
        return windowsChromeDriver();
    }

    //window配置
    private ChromeDriver windowsChromeDriver() {
        if (this.driver == null) {
            System.setProperty("webdriver.chrome.driver", windowsWebDriverPath);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("user-agent=" + userAgent);
            this.driver = new ChromeDriver(chromeOptions);
            driver.get("http://localhost:8081/");
            //close();
        }
        return driver;
    }

    //linux配置
    private ChromeDriver linuxChromeDriver() {
        if (this.driver == null) {
            System.setProperty("webdriver.chrome.bin", linuxChromePath);
            System.setProperty("webdriver.chrome.driver", linuxWebDriverPath);
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("user-agent=" + userAgent)
                    .addArguments("--headless")
                    .addArguments("--no-sandbox")
                    .addArguments("--disable-gpu");
            this.driver = new ChromeDriver(chromeOptions);
            driver.get("http://localhost:8081/");
            log.info("生成新的chromedriver");
            //close();
        }
        return driver;
    }

    private void close() {
        ThreadUtil.execute(() -> {
            ThreadUtil.sleep(6000);
            if(driver!=null){
                driver.quit();
                driver = null;
                log.info("关闭chromedirver！");
            }
        });
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化chromedirver！");
        getChromeDriver();
        log.info("初始化chromedirver完成！");
    }
}
