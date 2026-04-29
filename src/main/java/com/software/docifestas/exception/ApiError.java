package com.software.docifestas.exception;

public class ApiError {

    // Atributos
    private String message;
    private int status;

    // Getter
    public String getMessage() {return message;}
    public int getStatus() {return status;}

    // Setter
    public void setMessage(String message) {this.message = message;}
    public void setStatus(int status) {this.status = status;}
}
