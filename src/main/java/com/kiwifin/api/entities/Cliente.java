package com.kiwifin.api.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="CLIENTE", schema = "KIWIFIN")
public class Cliente implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_CLIENTE")
    private Long idCliente;
    @Column(name="NOME", nullable = false)
    private String nome;
    @Column(name="EMAIL", nullable = false)
    private String email;
    @Column(name="CPF", unique = true, nullable = false, length = 11)
    private String cpf;
    @Column(name="SENHA", nullable = false)
    private String senha;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_NASCIMENTO", length = 7, nullable = false)
    private Date dataNascimento;
    @Column(name="CELULAR", nullable = false)
    private String celular;
    @Column(name="CEP", nullable = false)
    private String cep;
    @Column(name="CIDADE", nullable = false)
    private String cidade;
    @Column(name="ENDERECO", nullable = false)
    private String endereco;
    @Column(name="UF", nullable = false,  length = 2)
    private String uf;
    @Column(name="COMPLEMENTO", nullable = false)
    private String complemento;
    @Column(name="ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired;
    @Column(name="ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked;
    @Column(name="CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERFIL", referencedColumnName = "ID_PERFIL")
    private Perfil perfil;

    public Cliente() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return idCliente.equals(cliente.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }
}
