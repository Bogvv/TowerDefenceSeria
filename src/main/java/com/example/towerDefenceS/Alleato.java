package com.example.towerDefenceS;

public class Alleato extends Personaggio{
    public Alleato(int tipo, double y){
        super(100,1,2,60, "temp.png", "temp.png", true);
        setTranslateX(50);setTranslateY(y);
        switch (tipo){
            case 1:
                configura("walk_contadine.png", "slash_contadine.png", 120, 2, 2, 70);
                break;
            case 2:
                configura("walk_arciere.png", "shoot_arciere.png", 70, 1.5, 2.5, 250);
                break;
            case 3:
                configura("walk_knight.png", "slash_knight.png", 50, 4, 1.8, 180);
                break;
        }
        aggiornaAnimazione();
    }

    private void configura(String w, String a, double h, double d, double v, double r){
        this.getChildren().clear();
        javafx.scene.image.ImageView vW = new javafx.scene.image.ImageView(new javafx.scene.image.Image(Alleato.class.getResourceAsStream("/assets/" + w)));
        javafx.scene.image.ImageView vA = new javafx.scene.image.ImageView(new javafx.scene.image.Image(Alleato.class.getResourceAsStream("/assets/" + a)));
        vW.setFitWidth(100); vW.setFitHeight(100);
        vA.setFitWidth(100); vA.setFitHeight(100);
        this.getChildren().addAll(vW, vA);
        setImgWalk(vW);
        setImgAttacco(vA);
        this.HP = h; this.danno = d; this.speed = v; this.range = r;
        aggiornaAnimazione();
    }
}
