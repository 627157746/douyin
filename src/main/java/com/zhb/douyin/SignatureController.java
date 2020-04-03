package com.zhb.douyin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/3/30 15:48
 * @Version 1.0
 */
@Controller
public class SignatureController {


    @Autowired
    SignatureUtil signatureUtil;

    @ResponseBody
    @GetMapping("/sign/{userId}")
    public Result getSignature(@PathVariable String userId) {
        try {
            return Result.success(signatureUtil.getSignature(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error();
        }
    }

    @GetMapping
    public String index(){
        return "index";
    }
}
