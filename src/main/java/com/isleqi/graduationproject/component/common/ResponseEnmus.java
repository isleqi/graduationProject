package com.isleqi.graduationproject.component.common;

public enum ResponseEnmus {


    SUCCESS("200", "操作成功"),
    FAIL("500", "操作失败"),
    DELETE_SUCCESS("200", "删除成功"),
    DELETE_FAIL("400", "删除失败");





    ResponseEnmus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;
    private String message;

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


}
