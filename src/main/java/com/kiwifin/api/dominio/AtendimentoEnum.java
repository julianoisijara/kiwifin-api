package com.kiwifin.api.dominio;

public enum AtendimentoEnum {

    ABERTO("A"),
    EM_ATENDIMENTO("EA"),
    PENDENTE("P"),
    FECHADO("F"),
    REABERTO("R"),
    ENCERRADO("E");

    private final String sigla;

    AtendimentoEnum(String sigla) {
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public static String getSigla(String sigla) {
        for(AtendimentoEnum a : AtendimentoEnum.values()) {
            if (a.sigla.equals(sigla)) {
                return a.name();
            }
        }
        return null;
    }

}
