/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hamizan.app.ligabolehland.util;

import com.hamizan.app.ligabolehland.response.BasicResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;

/**
 *
 * @author hamizan
 */
public class ResponseHandler {
    
    private static BodyBuilder response200 = ResponseEntity.status(HttpStatus.OK);
    private static BodyBuilder response400 = ResponseEntity.status(HttpStatus.BAD_REQUEST);
    private static BodyBuilder response500 = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    
    public static class OK {
        
        public static ResponseEntity build (String message, Object body){
            return response200.body(new BasicResponse(message, body));
        }
    }
    public static class BAD_REQ {
    
        public static ResponseEntity build (String message, Object body){
            return response400.body(new BasicResponse(message, body));
        }
    }
    public static class SERVER_ERROR {
    
        public static ResponseEntity build (String message, Object body){
            return response500.body(new BasicResponse(message, body));
        }
    }
    
}
