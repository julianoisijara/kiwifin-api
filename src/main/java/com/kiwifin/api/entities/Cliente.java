package com.kiwifin.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name="CLIENTE", schema = "KIWIFIN")
public class Cliente implements Serializable {

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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    private Perfil perfil;

    public Cliente() {

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
