package com.fjut.pojo;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回信息封装
 * @author LGX_TvT
 * @date 2019-01-20 0:10
 */
@Getter@Setter
public class ResultInfo {
    private int code;
    private String msg;
    private String info;

    public ResultInfo() {
    }

    public ResultInfo(int code, String msg, String info) {
        this.code = code;
        this.msg = msg;
        this.info = info;
    }
}