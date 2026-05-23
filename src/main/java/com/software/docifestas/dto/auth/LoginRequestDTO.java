package com.software.docifestas.dto.auth;

public class LoginRequestDTO {
    // Atributos By Dege
    private String email;
    private String senha;

    // Getter
    public String getEmail() {return email;}
    public String getSenha() {return senha;}

    // -----------------------------

    // Setter
    public void setEmail(String email) {this.email = email;}
    public void setSenha(String senha) {this.senha = senha;}
}
