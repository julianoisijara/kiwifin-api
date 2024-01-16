package com.kiwifin.api.controllers;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ApiController {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String HEADER_VALUE = "application/json; charset=UTF-8";
    private static final String MESSAGE = "MESSAGE";
    private static final String STATUS_CODE = "STATUS_CODE";

    protected  ResponseEntity<Object> respondOk(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    protected  ResponseEntity<Object> respondCreated(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    protected  ResponseEntity<Object> respondAccepted(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.ACCEPTED);
    }

    ResponseEntity<Object> respondNoContent(String mensagem) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, HEADER_VALUE);

        return new ResponseEntity<>(new JSONObject().put("noContent",
                                                                new JSONObject().put(MESSAGE, mensagem)
                                                                                .put(STATUS_CODE, 204)).toString(),
                headers ,HttpStatus.NO_CONTENT);
    }

    protected  ResponseEntity<Object> respondFound(Object entity) {
        return new ResponseEntity<>(entity, HttpStatus.FOUND);
    }

    ResponseEntity<Object> respondBadRequest(String mensagem) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, HEADER_VALUE);

        return new ResponseEntity<>(new JSONObject().put("erro",
                new JSONObject().put(MESSAGE, mensagem)
                        .put(STATUS_CODE, 400)).toString(),
                headers ,HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> respondForbidden(String mensagem) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, HEADER_VALUE);

        return new ResponseEntity<>(new JSONObject().put("erro",
                                                                new JSONObject().put(MESSAGE, mensagem)
                                                                                .put(STATUS_CODE, 403)).toString(),
                headers ,HttpStatus.FORBIDDEN);
    }

    protected ResponseEntity<Object> respondError(String mensagem) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(CONTENT_TYPE, HEADER_VALUE);

        return new ResponseEntity<>(mensagem,
                headers ,HttpStatus.INTERNAL_SERVER_ERROR);
    }




}