package com.isleqi.graduationproject.component.common.domain;

import com.isleqi.graduationproject.component.common.ResponseEnmus;
import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String code = "200";
    private String message = "success";
    private Object data;
    private List dataList;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public static Response errorResponse(String message) {
        Response response = new Response();
        response.setCode(ResponseEnmus.FAIL.getCode());
        response.setMessage(message);
        return response;
    }

    public static Response errorResponseWithData(String message,Object data) {
        Response response = new Response();
        response.setCode(ResponseEnmus.FAIL.getCode());
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static Response successResponse() {
        Response response = new Response();
        response.setCode(ResponseEnmus.SUCCESS.getCode());
        response.setMessage(ResponseEnmus.SUCCESS.getMessage());
        return response;
    }

    public static Response successResponseWithData(Object data) {
        Response response = new Response();
        response.setCode(ResponseEnmus.SUCCESS.getCode());
        response.setMessage(ResponseEnmus.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    public static Response build(String code, String message) {
        return new Response(code, message, null,null);
    }
    public static Response build(String code, String message, Object data) {
        return new Response(code, message, data,null);
    }
    public Response(ResponseEnmus responseResultEnum, Object data, List dataList) {
        this(responseResultEnum.getCode(),responseResultEnum.getMessage(),data,dataList);
    }
    public Response() {

    }
    public Response(String code, String message, Object data,List dataList) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.dataList=dataList;
    }

}
