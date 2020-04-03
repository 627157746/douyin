package com.zhb.douyin;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DouyinApplication {

    public static void main(String[] args) {
        SpringApplication.run(DouyinApplication.class, args);
    }


    private final static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";

    private final static String windowsWebDriverPath = "D:\\develop\\chrome-driver\\chromedriver.exe";

    private final static String linuxWebDriverPath = "/usr/bin/chromedriver";

    private final static String linuxChromePath = "/opt/google/chrome/chrome";

    private final static boolean isLinux = true;


    @Bean
    public ChromeDriver chromeDriver() {
        if (isLinux) {
            return linuxChromeDriver();
        }
        return windowsChromeDriver();
    }

    //window配置
    private ChromeDriver windowsChromeDriver() {
        System.setProperty("webdriver.chrome.driver", windowsWebDriverPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-agent=" + userAgent);
        return new ChromeDriver(chromeOptions);
    }

    //linux配置
    private ChromeDriver linuxChromeDriver() {
        System.setProperty("webdriver.chrome.bin", linuxChromePath);
        System.setProperty("webdriver.chrome.driver", linuxWebDriverPath);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("user-agent=" + userAgent)
                .addArguments("--headless")
                .addArguments("--no-sandbox");
        return new ChromeDriver(chromeOptions);
    }

}
