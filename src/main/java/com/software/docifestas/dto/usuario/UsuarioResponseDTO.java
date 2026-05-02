package com.software.docifestas.dto.usuario;

public class UsuarioResponseDTO {

    // Atributos
    private Long id;
    private String nome;
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
