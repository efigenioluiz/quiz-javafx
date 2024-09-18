package com.efigenio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.efigenio.controller.GameController;
import com.efigenio.model.Questao;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {
    private GameController gameController;
    private VBox root;
    private Text quantidadeQuestoesText;
    private Text resultadoText;
    private Text enunciadoTxt;
    private Button alternativa1;
    private Button alternativa2;
    private Button alternativa3;
    private Button alternativa4;
    private Button proximo;

    @Override
    public void start(Stage stage) {

        iniciaComponente();
        atualizaComponente();

        Scene cena = new Scene(root, 800, 600);
        stage.setScene(cena);
        stage.setTitle("Quiz Game");
        stage.show();
    }

    @SuppressWarnings("unchecked")
    public void atualizaComponente() {
        List<Questao> questoes = gameController.getAllQuestoes();
        Questao questaoAtual = gameController.getQuestaoAtual();

        quantidadeQuestoesText
                .setText("Perguntas " + questoes.indexOf(questaoAtual) + "/" + gameController.getQuantidadeQuestoes());
        enunciadoTxt.setText(questaoAtual.getEnunciado());

        alternativa1.setText(questaoAtual.getAlternativas().get(0));
        alternativa2.setText(questaoAtual.getAlternativas().get(1));
        alternativa3.setText(questaoAtual.getAlternativas().get(2));
        alternativa4.setText(questaoAtual.getAlternativas().get(3));

        alternativa1.setOnAction(respondeQuestao());
        alternativa2.setOnAction(respondeQuestao());
        alternativa3.setOnAction(respondeQuestao());
        alternativa4.setOnAction(respondeQuestao());

    }

    private EventHandler respondeQuestao() {
        return new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Button bt = new Button();
                bt = (Button) event.getSource();

                System.out.println(bt.getText());

                resultadoText.setVisible(true);
                proximo.setVisible(true);

                if (gameController.verificaJogada(bt.getText())) {

                    resultadoText.setText("Acertou");
                } else {

                    resultadoText.setText("Errou");
                }

                proximo.setOnAction(proximaQuestao());
            }
        };
    }

    private EventHandler proximaQuestao() {
        return new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                gameController.proximaQuestao();
                resultadoText.setVisible(false);
                atualizaComponente();
            }
        };
    }

    public void iniciaComponente() {
        root = new VBox(10);
        quantidadeQuestoesText = new Text("Pergunta 0/0");
        enunciadoTxt = new Text("Enunciado:");
        resultadoText = new Text("Resultado");
        alternativa1 = new Button();
        alternativa2 = new Button();
        alternativa3 = new Button();
        alternativa4 = new Button();
        proximo = new Button();

        alternativa1.setText("Alternativa 1");
        alternativa2.setText("Alternativa 2");
        alternativa3.setText("Alternativa 3");
        alternativa4.setText("Alternativa 4");
        proximo.setText("Proximo");

        root.getChildren().add(quantidadeQuestoesText);
        root.getChildren().add(enunciadoTxt);
        root.getChildren().add(alternativa1);
        root.getChildren().add(alternativa2);
        root.getChildren().add(alternativa3);
        root.getChildren().add(alternativa4);

        root.getChildren().add(resultadoText);
        proximo.setVisible(false);
        resultadoText.setVisible(false);

        root.getChildren().add(proximo);
        root.setAlignment(Pos.CENTER);

    }

    @Override
    public void init() throws Exception {
        super.init();

        List<Questao> questoes = new ArrayList<>();

        questoes.add(new Questao("Quantos Anos a Prof/Professora tem?",
                new ArrayList<>(Arrays.asList("20", "30", "26", "28")), "26"));

        questoes.add(new Questao("Quando ocorre a aula de POO",
                new ArrayList<>(Arrays.asList("Segunda", "Terca", "Quarta", "Quinta")), "Quarta"));
        questoes.add(new Questao("Quando ocorre a aula de POO",
                new ArrayList<>(Arrays.asList("Segunda", "Terca", "Quarta", "Quinta")), "Quarta"));

        gameController = new GameController(questoes, 0, 0, questoes.get(0));
    }

    public static void main(String[] args) {
        launch(args);
    }

}