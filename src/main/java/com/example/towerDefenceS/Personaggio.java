package com.example.towerDefenceS;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract  class Personaggio extends Pane {
    protected double HP,danno,speed, rangeAttacco;
    protected boolean alleato;
    protected boolean fight = false;

    public Personaggio(double HP,double danno, double rangeAttacco,double speed,String[] fileNames, boolean alleato){
        this.HP = HP;
        this.danno = danno;
        this.speed = speed;
        this.rangeAttacco = rangeAttacco;
        this.alleato = alleato;

        for(String nome : fileNames){
            Image img = new Image("file:assets/" + nome);
            ImageView view = new ImageView(img);
            view.setFitHeight(100);
            view.setFitHeight(100);
            this.getChildren().add(view);
        }
    }

    public void move(){
        if(!fight){
            if (alleato) setTranslateX(getTranslateX() + speed);
            else setTranslateX(getTranslateX() - speed);
        }
    }

    public boolean seeNemico(Personaggio t){
        double distanza = Math.abs(this.getTranslateX() - t.getTranslateX());
        return distanza <= this.rangeAttacco;
    }

    public void subisciDanno(double d){ this.HP -= d;}
    public boolean isDead(){ return HP <= 0;}
    public double getDanno(){ return danno;}
    public void setFight(boolean b){this.fight = b;}
}
