package com.example.towerDefenceS;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract  class Personaggio extends Pane {
    protected double HP,danno,speed, range;
    protected boolean alleato;
    protected boolean fight = false;

    private ImageView imgWalk;
    private ImageView imgAttacco;

    private double wFrameW, wFrameH;
    private int wMaxFrame;
    private int wRigaIndice;

    private double aFrameW, aFrameH;
    private int aMaxFrame;
    private int aRigaIndice;

    private int frameCorrente = 0;
    private int tickCounter = 0;
    private final int TICK_PER_FRAME = 10;

    public Personaggio(double HP,double danno, double range,double speed, boolean alleato){
        this.HP = HP;
        this.danno = danno;
        this.speed = speed;
        this.range = range;
        this.alleato = alleato;
    }

    protected void impostaCamminata(String file, double imgW, double imgH, int maxFrame, int riga){
        this.wMaxFrame = maxFrame;
        this.wRigaIndice = riga;
        this.wFrameW = imgW/maxFrame;
        this.wFrameH = imgH/4.0;

        this.imgWalk = new ImageView(new Image(Personaggio.class.getResourceAsStream("/assets/" + file)));
        this.imgWalk.setFitWidth(wFrameW * 3);
        this.imgWalk.setFitHeight(wFrameH * 3);

        this.imgWalk.setViewport(new Rectangle2D(0, wRigaIndice*wFrameH, wFrameW,wFrameH));
        this.getChildren().add(this.imgWalk);
    }

    protected void impostaAttacco(String file, double imgW, double imgH, int maxFrame, int riga){
        this.aMaxFrame = maxFrame;
        this.aRigaIndice = riga;
        this.aFrameW = imgW/maxFrame;
        this.aFrameH = imgH/4.0;

        this.imgAttacco = new ImageView(new Image(Personaggio.class.getResourceAsStream("/assets/" + file)));
        this.imgAttacco.setFitWidth(aFrameW * 3);
        this.imgAttacco.setFitHeight(aFrameH * 3);

        this.imgAttacco.setViewport(new Rectangle2D(0, aRigaIndice*aFrameH,aFrameW,aFrameH));
        this.getChildren().add(this.imgAttacco);
    }

    public void aggiornaAnimazione(){
        if (imgWalk == null || imgAttacco == null) return;
        imgWalk.setVisible(!fight);
        imgAttacco.setVisible(fight);

        tickCounter++;
        if (tickCounter >= TICK_PER_FRAME){
            tickCounter = 0;
            frameCorrente++;
        }

        if (!fight){
            if (frameCorrente >= wMaxFrame) frameCorrente = 0;
            double xRitaglio = frameCorrente * wFrameW;
            double yRitaglio = wRigaIndice * wFrameH;

            imgWalk.setViewport(new Rectangle2D(xRitaglio, yRitaglio, wFrameW, wFrameH));
        }else{
            if (frameCorrente >= aMaxFrame) frameCorrente = 0;
            double xRitaglio = frameCorrente * aFrameW;
            double yRitaglio = aRigaIndice * aFrameH;

            imgAttacco.setViewport(new Rectangle2D(xRitaglio, yRitaglio, aFrameW, aFrameH));
        }
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
        if (this.fight != fight){
            this.frameCorrente = 0;
        }
        this.fight = fight;
    }

    public double getDanno() {
        return danno;
    }

    public void setImgWalk(ImageView imgWalk) {
        this.imgWalk = imgWalk;
    }

    public void setImgAttacco(ImageView imgAttacco) {
        this.imgAttacco = imgAttacco;
    }
}
