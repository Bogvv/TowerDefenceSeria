package com.example.towerDefenceS;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract  class Personaggio extends Pane {
    protected double HP,danno,speed, range;
    protected boolean alleato;
    protected boolean fight = false;

    private ImageView imgWalk;
    private ImageView imgAttacco;

    public Personaggio(double HP,double danno, double range,double speed,String fileWalk, String fileAttacco, boolean alleato){
        this.HP = HP;
        this.danno = danno;
        this.speed = speed;
        this.range = range;
        this.alleato = alleato;

        this.imgWalk = new ImageView(new Image("file:assets/" + fileWalk));
        this.imgAttacco = new ImageView(new Image("file:assets/" + fileAttacco));

        this.imgWalk.setFitWidth(100); this.imgWalk.setFitHeight(100);
        this.imgAttacco.setFitWidth(100); this.imgAttacco.setFitHeight(100);

        this.getChildren().addAll(imgWalk,imgAttacco);

        aggiornaAnimazione();

    }

    public void aggiornaAnimazione(){
        imgWalk.setVisible(!fight);
        imgAttacco.setVisible(fight);
    }

    public void move(){
        if(!fight){
            if (alleato) setTranslateX(getTranslateX() + speed);
            else setTranslateX(getTranslateX() - speed);
        }
    }

    public boolean seeNemico(Personaggio t){
        double dist = Math.abs(this.getTranslateX() - t.getTranslateX());
        return dist <= this.range;
    }

    public void subisciDanno(double d){ this.HP -= d;}
    public boolean isDead(){ return HP <= 0;}

    public void setFight(boolean fight) {
        this.fight = fight;
    }

    public double getDanno() {
        return danno;
    }
}
