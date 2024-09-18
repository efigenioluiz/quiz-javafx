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
        boolean jogada = alternativa.equals(questaoAtual.getResposta());

        if (jogada) {
            questaoAtual.setCorreto(true);
            this.acerto += 1;
        } else {
            this.erro += 1;
        }

        int proximoIndice = questoes.indexOf(this.questaoAtual) + 1;
        if (proximoIndice < questoes.size()) {
            setQuestaoAtual(questoes.get(proximoIndice));
        } else {
            System.out.println(getStatusGame());
        }

        return jogada;
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
        return indiceAtual >= 0 && indiceAtual < questoes.size() - 1;
    }

    public boolean proximaQuestao() {
        if (!temProxima()) {
            return false;
        }

        return true;
    }

    public String getStatusGame() {
        return String.format("Acertos: %d, Erros: %d, Questão Atual: %s",
                acerto, erro, questaoAtual.getEnunciado());
    }

}
