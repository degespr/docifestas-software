package com.software.docifestas.dto.usuario;

public class UsuarioRequestDTO {
    // Atributos By Dege
    private String nome;
    private String email;
    private String senha;

    // Getter
    public String getNome() {return nome;}
    public String getEmail() {return email;}
    public String getSenha() {return senha;}

    // -----------------------------

    // Setter
    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(String email) {this.email = email;}
    public void setSenha(String senha) {this.senha = senha;}
}
