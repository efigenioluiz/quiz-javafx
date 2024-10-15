package com.efigenio.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.efigenio.models.Questao;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.fxml.FXML;

public class JanelaPrincipalController {
    @FXML
    private Button alternativa1;

    @FXML
    private Button alternativa2;

    @FXML
    private Button alternativa3;

    @FXML
    private Button alternativa4;

    @FXML
    private Text enunciadoText;

    @FXML
    private Text perguntasQtdText;

    @FXML
    private Button proximoBtn;

    @FXML
    private Text resultadoText;

    GameQuizController gameQuizController;

    public void initialize() {

        resultadoText.setVisible(false);
        proximoBtn.setVisible(false);

        List<Questao> questoes = new ArrayList();

        questoes.add(new Questao("Quantos Anos a Prof/Professora tem?",
                new ArrayList<>(Arrays.asList("20", "30", "26", "28")), "26"));

        questoes.add(new Questao("Quando ocorre a aula de POO",
                new ArrayList<>(Arrays.asList("Segunda", "Terca", "Quarta", "Quinta")),
                "Quarta"));
        questoes.add(new Questao("Qual é Capital do Paraná",
                new ArrayList<>(Arrays.asList("Pato Branco", "Curitiba", "Paranaguá",
                        "Cascavel")),
                "Curitiba"));

        Collections.shuffle(questoes);

        gameQuizController = new GameQuizController(questoes, 0, 0, questoes.get(0));

        List<String> alternativas = gameQuizController.getQuestaoAtual().getAlternativas();
        atualizaTela(alternativas, gameQuizController.getQuestaoAtual());
    }

    @FXML
    void proximo(ActionEvent event) {
        resultadoText.setVisible(false);
        resultadoText.setVisible(false);

        proximoBtn.setVisible(false);

        int perguntaAtualPosicao = gameQuizController.getAllQuestoes().indexOf(gameQuizController.getQuestaoAtual());
        gameQuizController.setQuestaoAtual(gameQuizController.getAllQuestoes().get(perguntaAtualPosicao));

        disableTodosBotoes(false);
        atualizaTela(gameQuizController.getQuestaoAtual().getAlternativas(), gameQuizController.getQuestaoAtual());
    }

    public void setQuantidadeQuestoes() {
        Questao questaoAtual = gameQuizController.getQuestaoAtual();
        int indexAtual = gameQuizController.getAllQuestoes().indexOf(questaoAtual) + 1;

        perguntasQtdText
                .setText("Perguntas " + indexAtual + "/"
                        + gameQuizController.getQuantidadeQuestoes());
    }

    public void atualizaTela(List<String> alternativas, Questao questao) {
        setQuantidadeQuestoes();
        enunciadoText.setText(questao.getEnunciado());
        alternativa1.setText(alternativas.get(0));
        alternativa2.setText(alternativas.get(1));
        alternativa3.setText(alternativas.get(2));
        alternativa4.setText(alternativas.get(3));
    }

    public void disableTodosBotoes(Boolean opcao) {
        alternativa1.setDisable(opcao);
        alternativa2.setDisable(opcao);
        alternativa3.setDisable(opcao);
        alternativa4.setDisable(opcao);
    }

    public boolean acertouQuestao(String palpite) {
        return gameQuizController.verificaJogada(palpite);
    }

    @FXML
    public Boolean verificaJogada(ActionEvent event) {

        String palpite = ((Button) event.getSource()).getText().toString();
        disableTodosBotoes(true);
        resultadoText.setVisible(true);
        proximoBtn.setVisible(true);

        if (!gameQuizController.temProxima()) {
            proximoBtn.setVisible(false);
        }

        if (acertouQuestao(palpite)) {
            resultadoText.setText("Acertou");
            return true;
        }

        resultadoText.setText("Errado");
        return false;
    }

}
