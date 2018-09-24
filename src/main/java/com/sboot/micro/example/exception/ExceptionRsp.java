/*
 * Copyright (c) 2018 Blustream Corporation.
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to
 * deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom
 * the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice
 * shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR
 * ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.sboot.micro.example.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

/**
 * Original Author(s): Mike Audi
 * Purpose: Uniform error message response model
 */

@ApiModel(value = "ExceptionRsp", description = "Uniform error message response model")
@JsonIgnoreProperties
public class ExceptionRsp{
    private HttpStatus httpStatus;

    @JsonProperty("status")
    @ApiModelProperty(required = true, notes = "HTTP Status Code")
    private int status;

    @JsonProperty("error")
    @ApiModelProperty(required = true, notes = "HTTP Status Error")
    private String error;

    @JsonProperty("message")
    @ApiModelProperty(required = true, notes = "Error response message")
    private String message;

    @JsonProperty("url")
    @ApiModelProperty(notes = "Request url")
    private String url;

    @JsonProperty("properties")
    @ApiModelProperty(notes = "User definable properties for error handling")
    private HashMap<String, String> properties;

    @JsonProperty("exceptionClass")
    @ApiModelProperty(notes = "Exception class that exception orginated from")
    private String exceptionClass;

    ExceptionRsp(Exception ex, String requestUrl, HttpStatus status){
        this.message = ex.getMessage();
        this.url = requestUrl;
        this.exceptionClass = ex.getClass().getCanonicalName();
        this.httpStatus = status;
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    ExceptionRsp(Exception ex, HttpStatus status){
        this.message = ex.getMessage();
        this.exceptionClass = ex.getClass().getCanonicalName();
        this.httpStatus = status;
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    ExceptionRsp(String message, HttpStatus status){
        this.message = message;
        this.httpStatus = status;
        this.status = status.value();
        this.error = status.getReasonPhrase();
    }

    ExceptionRsp(Exception ex, String requestUrl, HttpStatus status, HashMap<String,String> properties){
        this.message = ex.getMessage();
        this.url = requestUrl;
        this.exceptionClass = ex.getClass().getCanonicalName();
        this.httpStatus = status;
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.properties = properties;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public HashMap<String, String> getProperties() {
        return properties;
    }
    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }
    public String getExceptionClass() {
        return exceptionClass;
    }
    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }

    public ResponseEntity<ExceptionRsp> toResponse(){
        return new ResponseEntity<ExceptionRsp>(this,this.httpStatus);
    }
}
