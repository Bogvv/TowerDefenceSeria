package com.example.towerDefenceS;

public class Nemico extends Personaggio{
    public Nemico(int tipo, double y){
        super(60, 1, 2, 60, false);
        setTranslateX(1100); setTranslateY(y);
        switch (tipo){
            case 1:
                this.HP = 50; this.danno = 1; this.speed = 1; this.range = 60;
                impostaCamminata("walk_troll.png", 576,256,9,1);
                impostaAttacco("slash_troll.png", 1152,768,6,1);
                break;
            case 2:
                this.HP = 80; this.danno = 2; this.speed = 1.2; this.range = 250;
                impostaCamminata("walk_witch.png", 576,256,9,1);
                impostaAttacco("thrust_witch.png", 1536,768,8,1);
                break;
            case 3:
                this.HP = 300; this.danno = 5; this.speed = 0.6; this.range = 80;
                impostaCamminata("walk_orc.png", 576,256,9,1);
                impostaAttacco("slash_orc.png", 768,512,6,1);
                break;
        }
        aggiornaAnimazione();
    }


}
