package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.World1;
import com.mygdx.game.entities.World2;

import java.util.HashMap;

public class Intro implements Screen {


    private MyGdxGame game;

    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    TextureAtlas textureAtlas;
    TextureAtlas starsTextureAtlas;
    OrthographicCamera camera;
    ExtendViewport viewport;
    private float elapsed;
    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("STAR WARS THEME - shitty flute version.mp3"));
    Music evolveSound = Gdx.audio.newMusic(Gdx.files.internal("evolve.mp3"));
    BitmapFont font = new BitmapFont();
    Sprite backgroundSprite = new Sprite();
    float lineTime = 12f;
    int y = 30;
    String story;

    private static final int FRAME_COLS = 4, FRAME_ROWS = 5;
    Animation<TextureRegion> evolveAnimation;
    Texture evolveSheet;
    float evolveTime = 0f;



    public Intro(MyGdxGame game){

        this.game = game;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(1080, 720, camera);
        textureAtlas = new TextureAtlas("longago.txt");
        starsTextureAtlas = new TextureAtlas("stars.txt");
        story = "             Episode I & I/II\n" +
                "\n" +
                "        THE RISE OF JIMMY\n" +
                "\n" +
                "\n" +
                " Jimmy was just an average emu.\n" +
                "Until one day Jimmy stumbles upon\n" +
                " a pond of radioactive waste as\n" +
                "he is making his morning commute.\n" +
                "Lacking any ability to comprehend\n" +
                "the dangers that lie ahead, Jimmy\n" +
                " mindlessly falls into the pond.\n" +
                "\n" +
                " Escaping the depths of the pond,\n" +
                " Jimmy discovers newfound powers.\n" +
                " Jimmy has been bestowed with an\n" +
                "intellect comparable to that of a\n" +
                "  human, but more interestingly,\n" +
                "Jimmy now has the ability to absorb\n" +
                "  the life energy of other emus.\n" +
                "\n" +
                " A clear path for Jimmy is revealed\n" +
                " to him, as he now understands that\n" +
                " he must defeat all other emus that\n" +
                " stand in his way in order to become\n" +
                " the most powerful emu in existence\n" +
                "and to bring balance to the emu-force...";


        setLineHeight(font, 50);
        font.setColor(Color.YELLOW);

        evolveSheet = new Texture(Gdx.files.internal("radioactive evolution.png"));
        TextureRegion[][] tmp = TextureRegion.split(evolveSheet, evolveSheet.getWidth() / FRAME_COLS, evolveSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] evolveFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                evolveFrames[index++] = tmp[i][j];
            }
        }

        evolveAnimation = new Animation<>(0.15f, evolveFrames);

        addSprites();

    }


    @Override
    public void render (float delta) {


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        TextureRegion evolveFrame = evolveAnimation.getKeyFrame(evolveTime, false);


        if (elapsed > 0 && elapsed <= 4)
        drawSprite("longago", 0, 0);

        if (elapsed > 3.0) {
            mainSong.play();}
        if (elapsed > 5.0 && elapsed <= 12){
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            drawSprite("emuwars", 40, 25);
        }
        if (elapsed > 12) {
            renderBackground();
            font.draw(game.batch, story, 250, y);
            //y = y + 10;
            //lineTime = (float) (lineTime + 0.3);
        }

        if (elapsed > 12.4) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 40);
            y = y + 10;
        }
        if (elapsed > 12.8) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 50);

        }
        if (elapsed > 13.2) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 60);

        }
        if (elapsed > 13.6) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 70);

        }
        if (elapsed > 14.0) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 80);
            //font.dispose();

        }
        if (elapsed > 14.4) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 90);
        }
        if (elapsed > 14.8) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 100);
        }
        if (elapsed > 15.2) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 100);
        }
        if (elapsed > 15.6) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 110);
        }
        if (elapsed > 16.0) {
//            font.dispose();
            renderBackground();
            font.draw(game.batch, story, 250, 120);
        }
        if (elapsed > 16.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 130);
        }
        if (elapsed > 16.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 140);
        }
        if (elapsed > 17.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 150);
        }
        if (elapsed > 17.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 160);
        }
        if (elapsed > 18.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 170);
        }
        if (elapsed > 18.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 180);
        }
        if (elapsed > 18.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 190);
        }
        if (elapsed > 19.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 200);
        }
        if (elapsed > 19.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 210);
        }
        if (elapsed > 20.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 220);
        }
        if (elapsed > 20.4){
            renderBackground();
            font.draw(game.batch, story, 250, 230);
        }
        if (elapsed > 20.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 240);
        }
        if (elapsed > 21.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 250);
        }
        if (elapsed > 21.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 260);
        }
        if (elapsed > 22.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 270);
        }
        if (elapsed > 22.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 280);
        }
        if (elapsed > 22.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 290);
        }
        if (elapsed > 23.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 300);
        }
        if (elapsed > 23.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 310);
        }
        if (elapsed > 24.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 320);
        }
        if (elapsed > 24.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 330);
        }
        if (elapsed > 24.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 340);
        }
        if (elapsed > 25.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 350);
        }
        if (elapsed > 25.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 360);
        }
        if (elapsed > 26.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 370);
        }
        if (elapsed > 26.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 380);
        }
        if (elapsed > 26.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 390);
        }
        if (elapsed > 27.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 400);
        }
        if (elapsed > 27.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 410);
        }
        if (elapsed > 28.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 420);
        }
        if (elapsed > 28.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 430);
        }
        if (elapsed > 28.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 440);
        }
        if (elapsed > 29.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 450);
        }
        if (elapsed > 29.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 460);
        }
        if (elapsed > 30.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 470);
        }
        if (elapsed > 30.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 480);
        }
        if (elapsed > 30.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 490);
        }
        if (elapsed > 31.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 500);
        }
        if (elapsed > 31.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 510);
        }
        if (elapsed > 32.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 520);
        }
        if (elapsed > 32.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 530);
        }
        if (elapsed > 32.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 540);
        }
        if (elapsed > 33.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 550);
        }
        if (elapsed > 33.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 560);
        }
        if (elapsed > 34.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 570);
        }
        if (elapsed > 34.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 580);
        }
        if (elapsed > 34.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 590);
        }
        if (elapsed > 35.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 600);
        }
        if (elapsed > 35.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 610);
        }
        if (elapsed > 36.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 620);
        }
        if (elapsed > 36.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 630);
        }
        if (elapsed > 36.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 640);
        }
        if (elapsed > 37.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 650);
        }
        if (elapsed > 37.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 660);
        }
        if (elapsed > 38.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 670);
        }
        if (elapsed > 38.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 680);
        }
        if (elapsed > 38.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 690);
        }
        if (elapsed > 39.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 700);
        }
        if (elapsed > 39.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 710);
        }
        if (elapsed > 40.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 720);
        }
        if (elapsed > 40.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 730);
        }
        if (elapsed > 40.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 740);
        }
        if (elapsed > 41.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 750);
        }
        if (elapsed > 41.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 760);
        }
        if (elapsed > 42.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 770);
        }
        if (elapsed > 42.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 780);
        }
        if (elapsed > 42.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 790);
        }
        if (elapsed > 43.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 800);
        }
        if (elapsed > 43.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 810);
        }
        if (elapsed > 44.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 820);
        }
        if (elapsed > 44.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 830);
        }
        if (elapsed > 44.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 840);
        }
        if (elapsed > 45.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 850);
        }
        if (elapsed > 45.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 860);
        }
        if (elapsed > 46.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 870);
        }
        if (elapsed > 46.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 880);
        }
        if (elapsed > 46.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 890);
        }
        if (elapsed > 47.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 900);
        }
        if (elapsed > 47.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 910);
        }
        if (elapsed > 48.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 920);
        }
        if (elapsed > 48.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 930);
        }
        if (elapsed > 48.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 940);
        }
        if (elapsed > 49.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 950);
        }
        if (elapsed > 49.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 960);
        }
        if (elapsed > 50.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 970);
        }
        if (elapsed > 50.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 980);
        }
        if (elapsed > 50.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 990);
        }
        if (elapsed > 51.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1000);
        }
        if (elapsed > 51.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1010);
        }
        if (elapsed > 52.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1020);
        }
        if (elapsed > 52.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1030);
        }
        if (elapsed > 52.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1040);
        }
        if (elapsed > 53.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1050);
        }
        if (elapsed > 53.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1060);
        }
        if (elapsed > 54.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1070);
        }
        if (elapsed > 54.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1080);
        }
        if (elapsed > 54.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1090);
        }
        if (elapsed > 55.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1100);
        }
        if (elapsed > 55.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1110);
        }
        if (elapsed > 56.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1120);
        }
        if (elapsed > 56.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1130);
        }
        if (elapsed > 56.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1140);
        }
        if (elapsed > 57.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1150);
        }
        if (elapsed > 57.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1160);
        }
        if (elapsed > 58.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1170);
        }
        if (elapsed > 58.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1180);
        }
        if (elapsed > 58.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1190);
        }
        if (elapsed > 59.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1200);
        }
        if (elapsed > 59.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1210);
        }
        if (elapsed > 60.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1220);
        }
        if (elapsed > 60.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1230);
        }
        if (elapsed > 60.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1240);
        }
        if (elapsed > 61.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1250);
        }
        if (elapsed > 61.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1260);
        }
        if (elapsed > 62.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1270);
        }
        if (elapsed > 62.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1280);
        }
        if (elapsed > 62.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1290);
        }
        if (elapsed > 63.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1300);
        }
        if (elapsed > 63.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1310);
        }
        if (elapsed > 64.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1320);
        }
        if (elapsed > 64.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1330);
        }
        if (elapsed > 64.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1340);
        }
        if (elapsed > 65.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1350);
        }
        if (elapsed > 65.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1360);
        }
        if (elapsed > 66.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1370);
        }
        if (elapsed > 66.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1380);
        }
        if (elapsed > 66.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1390);
        }
        if (elapsed > 67.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1400);
        }
        if (elapsed > 67.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1410);
        }
        if (elapsed > 68.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1420);
        }
        if (elapsed > 68.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1430);
        }
        if (elapsed > 68.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1440);
        }
        if (elapsed > 69.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1450);
        }
        if (elapsed > 69.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1460);
        }
        if (elapsed > 70.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1470);
        }
        if (elapsed > 70.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1480);
        }
        if (elapsed > 70.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1490);
        }
        if (elapsed > 71.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1500);
        }
        if (elapsed > 71.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1510);
        }
        if (elapsed > 72.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1520);
        }
        if (elapsed > 72.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1530);
        }
        if (elapsed > 72.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1540);
        }
        if (elapsed > 73.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1550);
        }
        if (elapsed > 73.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1560);
        }
        if (elapsed > 74.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1570);
        }
        if (elapsed > 74.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1580);
        }
        if (elapsed > 74.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1590);
        }
        if (elapsed > 75.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1600);
        }
        if (elapsed > 75.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1610);
        }
        if (elapsed > 76.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1620);
        }
        if (elapsed > 76.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1630);
        }
        if (elapsed > 76.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1640);
        }
        if (elapsed > 77.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1650);
        }
        if (elapsed > 77.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1660);
        }
        if (elapsed > 78.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1670);
        }
        if (elapsed > 78.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1680);
        }
        if (elapsed > 78.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1690);
        }
        if (elapsed > 79.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1700);
        }
        if (elapsed > 79.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1710);
        }
        if (elapsed > 80.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1720);
        }
        if (elapsed > 80.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1730);
        }
        if (elapsed > 80.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1740);
        }
        if (elapsed > 81.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1750);
        }
        if (elapsed > 81.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1760);
        }
        if (elapsed > 82.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1770);
        }
        if (elapsed > 82.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1780);
        }
        if (elapsed > 82.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1790);
        }
        if (elapsed > 83.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1800);
        }
        if (elapsed > 83.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1810);
        }
        if (elapsed > 84.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1820);
        }
        if (elapsed > 84.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1830);
        }
        if (elapsed > 84.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1840);
        }
        if (elapsed > 85.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1850);
        }
        if (elapsed > 85.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1860);
        }
        if (elapsed > 86.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1870);
        }
        if (elapsed > 86.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1880);
        }
        if (elapsed > 86.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1890);
        }
        if (elapsed > 87.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1900);
        }
        if (elapsed > 87.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1910);
        }
        if (elapsed > 88.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1920);
        }
        if (elapsed > 88.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1930);
        }
        if (elapsed > 88.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1940);
        }
        if (elapsed > 89.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 1950);
        }
        if (elapsed > 89.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 1960);
        }
        if (elapsed > 90.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 1970);
        }
        if (elapsed > 90.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 1980);
        }
        if (elapsed > 90.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 1990);
        }
        if (elapsed > 91.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 2000);
        }
        if (elapsed > 91.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 2010);
        }
        if (elapsed > 92.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 2020);
        }
        if (elapsed > 92.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 2030);
        }
        if (elapsed > 92.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 2040);
        }
        if (elapsed > 93.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 2050);
        }
        if (elapsed > 93.6) {
            renderBackground();
            font.draw(game.batch, story, 250, 2060);
        }
        if (elapsed > 94.0) {
            renderBackground();
            font.draw(game.batch, story, 250, 2070);
        }
        if (elapsed > 94.4) {
            renderBackground();
            font.draw(game.batch, story, 250, 2080);
        }
        if (elapsed > 94.8) {
            renderBackground();
            font.draw(game.batch, story, 250, 2090);
        }
        if (elapsed > 95.2) {
            renderBackground();
            font.draw(game.batch, story, 250, 2100);
        }
        if (elapsed > 0.6) {
            mainSong.stop();
            resize(8000, 4100);
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            game.batch.draw(evolveFrame, 30, 0);
            evolveSound.play();
            evolveTime += Gdx.graphics.getDeltaTime();
            if(evolveTime >= 3.2){
                evolveSound.stop();
                game.setScreen(new MapOverviewScreen(game, new World2()));
                this.dispose();
            }
        }


        elapsed += Gdx.graphics.getDeltaTime();

        game.batch.end();

    }

    public static void setLineHeight(BitmapFont font, float height) {
        font.getData().setScale(height * font.getScaleY() / font.getLineHeight());
    }


    @Override
    public void dispose () {
        textureAtlas.dispose();
        sprites.clear();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
        game.batch.setProjectionMatrix(camera.combined);
    }

    private void drawSprite(String name, float x, float y){
        Sprite sprite = sprites.get(name);
        sprite.setPosition(x, y);
        sprite.draw(game.batch);
    }

    private void addSprites(){
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for(TextureAtlas.AtlasRegion region : regions){
            Sprite sprite = textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }

        Array<TextureAtlas.AtlasRegion> regions2 = starsTextureAtlas.getRegions();

        for(TextureAtlas.AtlasRegion region : regions2){
            Sprite sprite = starsTextureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }

    public void renderBackground() {
        backgroundSprite = sprites.get("stars");
        backgroundSprite.setSize(1080,720);
        backgroundSprite.draw(game.batch);
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }


    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }
}
