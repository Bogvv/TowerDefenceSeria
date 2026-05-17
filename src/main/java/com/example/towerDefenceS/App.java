package com.example.towerDefenceS;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class App extends Application {

    private Pane gamePane = new Pane();
    private ArrayList<Alleato> miei = new ArrayList<>();
    private ArrayList<Nemico> nemici = new ArrayList<>();
    private int uccisi = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Image sfondo = new Image(App.class.getResourceAsStream("/assets/campo_gioco.png"));
        gamePane.setBackground(new Background(new BackgroundImage(sfondo, null, null, null, new BackgroundSize(1200, 700, false, false, false, false))));
        HBox menu = new HBox(10);
        for (int i = 1; i <= 3; i++) {
            final int tipo = i;
            Button b = new Button("Eroe " + i);
            b.setOnAction(e ->{
                Alleato a = new Alleato(tipo, 400);
                miei.add(a);
                gamePane.getChildren().add(a);
            });
            menu.getChildren().add(b);
        }
        menu.setLayoutY(640);menu.setLayoutX(500);
        gamePane.getChildren().add(menu);

        new AnimationTimer(){
            long ultimoSpawn = 0;

            @Override
            public void handle(long ora) {
                if (uccisi < 20){
                    update();
                    if (ora - ultimoSpawn > 2_000_000_000L){
                        spawnNemico();
                        ultimoSpawn = ora;
                    }
                }
            }
        }.start();
        stage.setScene(new Scene(gamePane, 1200,700));
        stage.show();
    }

    private void update(){
        for (Alleato a : miei){
            a.setFight(false);
            for(Nemico n : nemici){
                if (a.seeNemico(n)){
                    a.setFight(true);
                    n.subisciDanno(a.getDanno());
                }
                if (n.seeNemico(a)){
                    n.setFight(true);
                    a.subisciDanno(n.getDanno());
                }
            }
            a.move();
            a.aggiornaAnimazione();
        }
        for (Nemico n : nemici){
            n.move();
            n.aggiornaAnimazione();
        }

        miei.removeIf(p ->{
            if (p.isDead()) {
                gamePane.getChildren().remove(p);
                return true;
            }
            return false;
        });

        nemici.removeIf(p ->{
            if (p.isDead()){
                gamePane.getChildren().remove(p);
                return true;
            }
            return false;
        });
    }

    private void spawnNemico(){
        Nemico n = new Nemico(new Random().nextInt(3) + 1, 400);
        nemici.add(n);
        gamePane.getChildren().add(n);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
