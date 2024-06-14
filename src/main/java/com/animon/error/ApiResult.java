package com.animon.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {

    private String path;
    private String message;
    private String error;
    private int status;
    private Date createdDate = new Date(System.currentTimeMillis());
    private Map<String,String> validationErrors;

    public ApiResult() {
    }


    public ApiResult(String path, String message,  int status) {
        this.path = path;
        this.message = message;
        this.status = status;
    }

    public ApiResult(String path, String message, String error, int status) {
        this.path = path;
        this.message = message;
        this.error = error;
        this.status = status;
    }
}
