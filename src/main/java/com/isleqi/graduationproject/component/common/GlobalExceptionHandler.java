package com.isleqi.graduationproject.component.common;

import com.isleqi.graduationproject.component.common.domain.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * API统一异常处理
     **/
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response jsonApiErrorHandler(HttpServletRequest request, Exception e) {
Response response=new Response();
        try {
            System.out.println("统一异常处理...");
            e.printStackTrace();

            Throwable innerEx = e.getCause();
            while (innerEx != null) {
                //innerEx.printStackTrace();
                if (innerEx.getCause() == null) {
                    break;
                }
                innerEx = innerEx.getCause();
            }

            if (innerEx == null) {
                response.setMessage(e.getMessage());

            } else {
                response.setMessage(innerEx.getMessage());
            }

          response.setCode("500");


        } catch (Exception ex) {
            ex.printStackTrace();

            response.setMessage(ex.getMessage());
            response.setCode("500");
        }

        return response;
    }

}