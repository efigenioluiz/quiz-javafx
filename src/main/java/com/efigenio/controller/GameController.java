package com.efigenio.controller;

import java.util.List;

import com.efigenio.model.Questao;

public class GameController {

    private List<Questao> questoes;
    private int acerto;
    private int erro;
    private Questao questaoAtual;

    public GameController(List<Questao> questoes, int acerto, int erro, Questao questaoAtual) {
        this.questoes = questoes;
        this.acerto = acerto;
        this.erro = erro;
        this.setQuestaoAtual(questaoAtual);
    }

    public List<Questao> getAllQuestoes() {
        return questoes;
    }

    public Boolean verificaJogada(String alternativa) {

        if (alternativa.equals(questaoAtual.getResposta())) {
            questaoAtual.setCorreto(true);
            return true;
        }
        return false;
    }

    public void reiniciar() {
        this.acerto = 0;
        this.erro = 0;
        this.questoes.clear();
    }

    public int getQuantidadeQuestoes() {
        return this.questoes.size();
    }

    public void setQuestaoAtual(Questao questaoAtual) {
        this.questaoAtual = questaoAtual;
    }

    public Questao getQuestaoAtual() {
        return questaoAtual;
    }

    public boolean temProxima() {
        int indiceAtual = questoes.indexOf(this.questaoAtual);
        return indiceAtual < questoes.size();
    }

    public boolean proximaQuestao() {
        int proximoIndice = questoes.indexOf(this.questaoAtual) + 1;
        int tamanhoMaximo = questoes.size();

        if (temProxima()) {
            setQuestaoAtual(questoes.get(proximoIndice));
        }

        if (proximoIndice <= tamanhoMaximo) {
            acerto += 1;
            return true;
        }

        erro += 1;
        return false;
    }

}
