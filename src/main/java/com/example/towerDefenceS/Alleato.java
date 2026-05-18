package com.example.towerDefenceS;

public class Alleato extends Personaggio{
    public Alleato(int tipo, double y){
        super(100, 1, 60, 2, true);
        setTranslateX(50);setTranslateY(y);
        switch (tipo){
            case 1:
                this.HP = 120; this.danno = 2; this.speed = 1.6; this.range = 70;
                impostaCamminata("walk_contadine.png", 576,256,9,3);
                impostaAttacco("slash_contadine.png", 512,256,8,3);
                break;
            case 2:
                this.HP = 70; this.danno = 1.5; this.speed = 1.8; this.range = 250;
                impostaCamminata("walk_arciere.png", 1152,512,9,3);
                impostaAttacco("shoot_arciere.png", 832,256,13,3);
                setTranslateY(y - 95);
                break;
            case 3:
                this.HP = 50; this.danno = 4; this.speed = 1.3; this.range = 200;
                impostaCamminata("walk_knight.png", 576,256,9,3);
                impostaAttacco("slash_knight.png", 1152,768,6,3);
                break;
        }
        aggiornaAnimazione();
    }
}
