package com.software.docifestas.dto.usuario;

import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioRequestDTO {
    // Atributos By Dege
    @Schema(description = "Nome do usuário", example = "Diego Oliveira")
    private String nome;

    @Schema(description = "Email do usuário", example = "dfernandesoliveira@outlook.com.br")
    private String email;

    @Schema(description = "Senha do usuário", example = "admin123")
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
