package com.software.docifestas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Usuario {
    // Anotações Spring
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
// -------------------------------------------
    // Atributos By Dege
        private String nome;
        private String email;
        @JsonIgnore
        private String senha;
        private boolean admin;

    // Construtores
        public Usuario() {
        }

    // G&S
        public Long getId() {return id;}
        public String getNome() { return nome; }
        public String getEmail() { return email; }
        public String getSenha() { return senha; }
        public boolean isAdmin() { return admin; }
    // -------------
        public void setId(Long id) {this.id = id;}
        public void setNome(String nome) { this.nome = nome; }
        public void setEmail(String email) { this.email = email; }
        public void setSenha(String senha) { this.senha = senha;}
        public void setAdmin(boolean admin) { this.admin = admin; }

    // MTS
        public String toString() {
            return "Usuário: " +nome+ " |  Email: " +email+ " |  Tipo de Login: " +isAdmin();
    }
}