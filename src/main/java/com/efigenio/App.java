package com.efigenio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.efigenio.controller.GameController;
import com.efigenio.model.Questao;

import javafx.application.Application;
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
    private Button butoesAlt1;
    private Button butoesAlt2;
    private Button butoesAlt3;
    private Button butoesAlt4;

    @Override
    public void start(Stage stage) {

        root = new VBox();
        quantidadeQuestoesText = new Text("Pergunta 0/0");
        resultadoText = new Text("");
        butoesAlt1 = new Button();
        butoesAlt2 = new Button();
        butoesAlt3 = new Button();
        butoesAlt4 = new Button();

        butoesAlt1.setText("Alternativa 1");
        butoesAlt2.setText("Alternativa 2");
        butoesAlt3.setText("Alternativa 3");
        butoesAlt4.setText("Alternativa 4");

        root.getChildren().add(quantidadeQuestoesText);
        root.getChildren().add(butoesAlt1);
        root.getChildren().add(butoesAlt2);
        root.getChildren().add(butoesAlt3);
        root.getChildren().add(butoesAlt4);
        root.getChildren().add(resultadoText);
        root.setAlignment(Pos.CENTER);

        Scene cena = new Scene(root, 800, 600);
        stage.setScene(cena);
        stage.setTitle("Quiz Game");
        stage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();

        List<Questao> questoes = new ArrayList<>();

        questoes.add(new Questao("Quantos Anos a Prof/Professora tem?",
                new ArrayList<>(Arrays.asList("20", "30", "26", "28"))));

        questoes.add(new Questao("Quando ocorre a aula de POO",
                new ArrayList<>(Arrays.asList("Segunda", "Terca", "Quarta", "Quinta"))));

        gameController = new GameController(questoes, 0, 0, questoes.get(0));
    }

    public static void main(String[] args) {
        launch(args);
    }

}