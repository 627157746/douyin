package com.zhb.douyin;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhb
 * @Description TODO
 * @Date 2020/3/30 17:29
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Douyin {

    private String user_id;

    private String sec_uid = "";

    private String count = "20";

    private String max_cursor = "0";

    private String aid = "1128";

    private String tac;

    private String _signature;

    private String dytk;

    private String user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36";

    public Douyin(String user_id, String tac, String dytk) {
        this.user_id = user_id;
        this.tac = tac;
        this.dytk = dytk;
    }
}
