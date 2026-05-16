package com.example.towerDefenceS;

public class Nemico extends Personaggio{
    public Nemico(int tipo, double y){
        super(60, 1, 2, 60, "temp.png", "temp.png", false);
        setTranslateX(1100); setTranslateY(y);
        switch (tipo){
            case 1:
                configura("walk_troll.png", "slash_troll.png", 50, 1, 3, 60);
                break;
            case 2:
                configura("walk_witch.png", "thrust_witch.png", 80, 2, 1.5, 250);
                break;
            case 3:
                configura("walk_troll.png", "slash_troll.png", 300, 5, 0.6, 80);
                break;
        }
    }

    private void configura(String w, String a, double h, double d, double v, double r){
        this.getChildren().clear();
        javafx.scene.image.ImageView vW = new javafx.scene.image.ImageView(new javafx.scene.image.Image("file:assets/" + w));
        javafx.scene.image.ImageView vA = new javafx.scene.image.ImageView(new javafx.scene.image.Image("file:assets/" + a));
        vW.setFitWidth(100); vW.setFitHeight(100);
        vA.setFitWidth(100); vA.setFitHeight(100);
        this.getChildren().addAll(vW, vA);
        this.HP = h; this.danno = d; this.speed = v; this.range = r;
    }
}
