package com.bym.bankingsystem.models.status;

public class Response {
    private int status_code;
    private String message;
    private Iterable<?> data;
    private long id;
    private Object obj;
    public Response(int status_code, String message, Iterable<?> data) {
        this.status_code = status_code;
        this.message = message;
        this.data = data;
    }

    public int getStatus_code() {
        return status_code;
    }
    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Iterable<?> getData() {
        return data;
    }
    public void setData(Iterable<?> data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}