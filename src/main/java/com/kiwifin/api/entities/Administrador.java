package com.kiwifin.api.entities;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="ADMINISTRADOR", schema = "KIWIFIN")
public class Administrador {

    private Long idAdministrador;
    private String nome;
    private String email;
    private String cpf;
    private String senha;

    public Administrador() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ADMINISTRADOR")
    public Long getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    @Column(name="NOME", nullable = false)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name="EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="CPF", nullable = false, length = 11)
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name="SENHA", nullable = false)
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrador that = (Administrador) o;
        return idAdministrador.equals(that.idAdministrador);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAdministrador);
    }
}
