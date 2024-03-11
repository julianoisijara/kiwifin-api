package com.kiwifin.api.security.config.DTO;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
public class TokenVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cpf;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenVO() {
    }

    public TokenVO(String cpf, Boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken) {
        this.cpf = cpf;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenVO tokenVO = (TokenVO) o;
        return cpf.equals(tokenVO.cpf) && authenticated.equals(tokenVO.authenticated) && created.equals(tokenVO.created) && expiration.equals(tokenVO.expiration) && accessToken.equals(tokenVO.accessToken) && refreshToken.equals(tokenVO.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, authenticated, created, expiration, accessToken, refreshToken);
    }
}
