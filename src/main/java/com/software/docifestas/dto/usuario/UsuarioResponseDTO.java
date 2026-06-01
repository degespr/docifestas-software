package com.software.docifestas.dto.usuario;
import io.swagger.v3.oas.annotations.media.Schema;

public class UsuarioResponseDTO {

    // Atributos By Dege
    @Schema(description = "ID do usuário", example = "1")
    private Long id;

    @Schema(description = "Nome do usuário", example = "Diego Oliveira")
    private String nome;

    @Schema(description = "Email do usuário", example = "dfernandesoliveira@outlook.com.br")
    private String email;

    private boolean admin;

    // Getter
    public Long getId() {return id;}
    public String getNome() {return nome;}
    public String getEmail() {return email;}
    public boolean isAdmin() {return admin;}

    // -------------------------

    // Setter
    public void setId(Long id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setEmail(String email) {this.email = email;}
    public void setAdmin(boolean admin) {this.admin = admin;}

}
