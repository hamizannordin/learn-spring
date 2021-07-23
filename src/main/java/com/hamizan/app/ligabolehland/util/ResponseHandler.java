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
import org.springframework.stereotype.Component;

/**
 *
 * @author hamizan
 */
@Component("responseHandler")
public class ResponseHandler {
    
    private BodyBuilder response200 = ResponseEntity.status(HttpStatus.OK);
    private BodyBuilder response400 = ResponseEntity.status(HttpStatus.BAD_REQUEST);
    private BodyBuilder response404 = ResponseEntity.status(HttpStatus.NOT_FOUND);
    private BodyBuilder response500 = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
    
    public ResponseEntity ok (String message, Object body){
            return response200.body(new BasicResponse(message, body));
        }
    
    public ResponseEntity badRequest (String message, Object body){
        return response400.body(new BasicResponse(message, body));
    }
    
    public ResponseEntity notFound (String message, Object body){
        return response404.body(new BasicResponse(message, body));
    }
    
    public ResponseEntity serverError (String message, Object body){
            return response500.body(new BasicResponse(message, body));
        }
}
