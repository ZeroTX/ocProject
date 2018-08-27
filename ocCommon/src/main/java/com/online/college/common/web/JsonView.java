package com.online.college.common.web;

import net.sf.json.JSONObject;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by tx on 2018/7/6.
 */
public class JsonView {

    private Integer code = 0;//响应码
    private String message;//消息
    private Object data;//数据

    /**
     * 失败 1
     */
    public static final Integer ERROR_CODE = 1;
    /**
     * 成功 0
     */
    public static final Integer SUCCESS_CODE = 0;



    public static String render(Object data){
        JsonView view = new JsonView(SUCCESS_CODE,"",data);
        return JSONObject.fromObject(view).toString();
    }
    public static String render(Integer code){
        JsonView view = new JsonView(code);
        return JSONObject.fromObject(view).toString();
    }
    public static String render(Integer errcode, String message){
        JsonView tmp = new JsonView(errcode, message);
        return JSONObject.fromObject(tmp).toString();
    }

    public static String render(Integer errcode, String message, Object data){
        JsonView tmp = new JsonView(errcode, message, data);
        return JSONObject.fromObject(tmp).toString();
    }

    public JsonView() {
    }

    public JsonView(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public JsonView(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonView(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString(){
        return JSONObject.fromObject(this).toString();
    }
}
