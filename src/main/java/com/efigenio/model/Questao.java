package com.efigenio.model;

import java.util.List;

public class Questao {
    private String enunciado;
    private String resposta;
    private List<String> alternativas;
    private Boolean correto;

    public Questao(String enunciado, List<String> alternativas, String resposta) {
        this.enunciado = enunciado;
        this.resposta = resposta;
        this.alternativas = alternativas;
        correto = false;

    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getQuestao(int pos) {
        return this.alternativas.get(pos);
    }

    public List<String> getAlternativas() {
        return alternativas;
    }

    public void setAlternativas(List<String> alternativas) {
        this.alternativas = alternativas;
    }

    public void setAlternativa(String alternativa) {
        this.alternativas.add(alternativa);
    }

    public Boolean getCorreto() {
        return correto;
    }

    public String getResposta() {
        return resposta;
    }

    public void setCorreto(Boolean correto) {
        this.correto = correto;
    }
}
