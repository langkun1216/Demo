package com.demo.common.utils;

import java.io.Serializable;

/**
 * 统一返回格式
 * @auther: WindyHu
 * @date: 2018/5/10 9:59
 */
public class CuscResult implements Serializable{


    private static final long serialVersionUID = 7925607901844005925L;


    private String code;

    private String message;

    private Object result;

    private Integer total;

//    public CuscResult (String code,String message,Object result,Integer total) {
//        this.code=code;
//        this.message = message;
//        this.result = result;
//        this.total = total;
//    }

    public CuscResult(){};

    public CuscResult (String code,String message,String result,Integer total) {
        this.code=code;
        this.message = message;
        this.result = result;
        this.total = total;
    }




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
