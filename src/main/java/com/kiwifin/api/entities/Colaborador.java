package com.kiwifin.api.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "ID_COLABORADOR")
@Table(name="COLABORADOR", schema = "KIWIFIN")
public class Colaborador implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_COLABORADOR")
    protected Long idColaborador;
    @Column(name="NOME", nullable = false)
    protected String nome;
    @Column(name="EMAIL", nullable = false)
    protected String email;
    @Column(name="CPF", unique = true, nullable = false, length = 11)
    protected String cpf;
    @Column(name="SENHA", nullable = false)
    protected String senha;
    @OneToOne
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")
    protected Departamento departamento;
    @Column(name="ACCOUNT_NON_EXPIRED")
    private Boolean accountNonExpired;
    @Column(name="ACCOUNT_NON_LOCKED")
    private Boolean accountNonLocked;
    @Column(name="CREDENTIALS_NON_EXPIRED")
    private Boolean credentialsNonExpired;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "KIWIFIN", name = "COLABORADOR_PERFIL", joinColumns = {@JoinColumn (name = "ID_COLABORADOR_PERFIL")},
        inverseJoinColumns = {@JoinColumn (name = "ID_PERFIL")}
    )
    protected List<Perfil> listaPerfis;

//    public List<String> getRoles() {
//        List<String> roles = new ArrayList<>();
//        for(Perfil perfil : listaPerfis) {
//            roles.add(perfil.getDescricao());
//
//        }
//        return roles;
//    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority("CLIENTE"));
        return this.listaPerfis;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
