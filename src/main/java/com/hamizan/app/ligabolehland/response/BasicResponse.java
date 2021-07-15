/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.response;

/**
 *
 * @author hamizan
 */
public class BasicResponse {
    
    private String message;
    private Object body;

    public BasicResponse() {
    }
    
    public BasicResponse(String message) {
        this.message = message;
    }

    public BasicResponse(String message, Object body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
  
}
