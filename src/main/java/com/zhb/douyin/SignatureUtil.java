package com.zhb.douyin;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/3/30 17:18
 * @Version 1.0
 */
@Component
public class SignatureUtil {

    private final static String userInfoUrl = "https://www.iesdouyin.com/share/user/";

    private final static String getDataUrl = "https://www.iesdouyin.com/web/api/v2/aweme/post/";

    private final static String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";

    private final static String js = "return generateSignature(arguments[0],arguments[1])";

    @Autowired
    InitDriver initDriver;

    //获取tac和dytk
    private Douyin getTacDytk(String userId) {
        String html = HttpUtil.createGet(userInfoUrl + userId)
                .header("User-Agent", userAgent)
                .execute().body();
        Document document = Jsoup.parse(html);
        Elements scripts = document.getElementsByTag("script");

        String tacScript = scripts.get(1).toString();
        String tac = tacScript.replace("<script>tac='", "")
                .replace("'</script>", "")
                .split("\\|")[0];
        String dytkScript = scripts.get(scripts.size() - 1).toString();
        String dytk = ReUtil.getGroup0("dytk.*'", dytkScript)
                .replace("dytk: '", "")
                .replace("'", "");

        return new Douyin(userId, tac, dytk);
    }

    //webDriver获取signature
    public Douyin getSignature(String userId) {
        Douyin douyin = getTacDytk(userId);
        ChromeDriver driver = initDriver.getChromeDriver();
        String signature = (String) ((JavascriptExecutor) driver).executeScript(js, douyin.getTac(), douyin.getUser_id());
        douyin.set_signature(signature);
        return douyin;
    }

//    public static String getData(Douyin douyin) {
//        Map<String, Object> params = BeanUtil.beanToMap(douyin);
//        return HttpUtil.createGet(getDataUrl)
//                .header("User-Agent", userAgent)
//                .form(params)
//                .execute().body();
//    }

}
