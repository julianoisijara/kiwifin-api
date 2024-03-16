package com.kiwifin.api.DTO.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kiwifin.api.DTO.base.BaseDTO;

import java.io.Serializable;
import java.util.Date;

public class ClienteViewDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idCliente;
    private String nome;
    private String email;
    private String cpf;
    @JsonIgnore
    private String senha;
    private Date dataNascimento;
    private String celular;
    private String cep;
    private String cidade;
    private String endereco;
    private String uf;
    private String complemento;
    private PerfilViewDTO perfil;
    private Boolean autenticado;

    public ClienteViewDTO() {
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public PerfilViewDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilViewDTO perfil) {
        this.perfil = perfil;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }
}
