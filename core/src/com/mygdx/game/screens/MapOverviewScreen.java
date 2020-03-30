package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.mygdx.game.Level;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.AiEmu;
import com.mygdx.game.entities.LevelStatus;
import com.mygdx.game.entities.World1_Level1;
import com.mygdx.game.entities.World1_Level2;
import com.mygdx.game.entities.World1_Level3;
import com.mygdx.game.entities.World1_Level4;
import com.mygdx.game.entities.World1_Level5;
import com.mygdx.game.entities.World1_Level6;
import com.mygdx.game.entities.World1_Level7;
import com.mygdx.game.entities.World1_Level8;
import com.mygdx.game.entities.World1_Status;


import java.util.HashMap;

import static com.badlogic.gdx.Gdx.files;
//blah blah blah

public class MapOverviewScreen implements Screen{

    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("You Reposted in the Wrong Dimmadome.mp3"));
    public static final float SPEED = 300; // in pixels per second
    public static final float EMUSPEED = 40; // in pixels per second


    OrthographicCamera camera;
    ExtendViewport viewport;
    TextureAtlas textureAtlas;
    TextureAtlas jimmyTextureAtlas;

    Animation<TextureRegion> jimmyAnimation;
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    float jimmyX = 100;
    float jimmyY = 100;
    float elapsedTime = 0;
    boolean jimmyMovingX = false;
    boolean jimmyMovingY = false;

    Sprite backgroundSprite = new Sprite();

    //MyGdxGame constructor
    MyGdxGame game;

    public MapOverviewScreen(MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(540, 360, camera);
        jimmyTextureAtlas = new TextureAtlas("mapjimmy.txt");
        textureAtlas = new TextureAtlas("mapoverview.txt");

        jimmyAnimation = new Animation<TextureRegion>(0.1f, jimmyTextureAtlas.getRegions());
        addSprites();


    }

    private void drawSprite(String name, float x, float y) {


        Sprite sprite = sprites.get(name);
        sprite.setPosition(x,y);
        sprite.draw(game.batch);

    }
    private void drawAnimation(TextureRegion textureRegion, float x, float y ){

        game.batch.draw(textureRegion,x,y);

    }

    private void addSprites() {

        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);

        }
    }


    public void renderBackground() {
        backgroundSprite = sprites.get("linkbackground");
        backgroundSprite.setSize(540,360);
        backgroundSprite.draw(game.batch);
    }


    @Override
    public void render(float delta){

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainSong.play();

        boolean animated = false;
        jimmyMovingX = false;
        jimmyMovingY = false;


        game.batch.begin();

        renderBackground();

        if(LevelStatus.getStatus() == World1_Status.level1 || LevelStatus.getStatus() == World1_Status.level2 || LevelStatus.getStatus() == World1_Status.level3 || LevelStatus.getStatus() == World1_Status.level4 ||
        LevelStatus.getStatus() == World1_Status.level5 || LevelStatus.getStatus() == World1_Status.level6 || LevelStatus.getStatus() == World1_Status.level7
        || LevelStatus.getStatus() == World1_Status.level8 || LevelStatus.getStatus() == World1_Status.level9)
            if (jimmyX + 64 < 100 + sprites.get("active world token").getWidth()*2  && jimmyX + 64 > 116 && jimmyY + 64 < sprites.get("active world token").getWidth()*2 + 30 && jimmyY + 64 > 40){//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 100, 20);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, new World1_Level1())));
                    this.dispose();
                }
            }
            else{
                drawSprite("world token",100,20);}

        if(LevelStatus.getStatus() == World1_Status.level2 || LevelStatus.getStatus() == World1_Status.level3 || LevelStatus.getStatus() == World1_Status.level4 ||
                LevelStatus.getStatus() == World1_Status.level5 || LevelStatus.getStatus() == World1_Status.level6 || LevelStatus.getStatus() == World1_Status.level7
                || LevelStatus.getStatus() == World1_Status.level8 || LevelStatus.getStatus() == World1_Status.level9) {
            if (jimmyX + 64 < 280 + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > 296 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + 110 && jimmyY + 64 > 120) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 280, 100);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, new World1_Level2())));
                    this.dispose();
                }
            } else {
                drawSprite("world token", 280, 100);
            }
        }
        else{
            drawSprite("inactive world token", 280, 100);
        }


        if(LevelStatus.getStatus() == World1_Status.level3 || LevelStatus.getStatus() == World1_Status.level4 || LevelStatus.getStatus() == World1_Status.level5
                || LevelStatus.getStatus() == World1_Status.level6 || LevelStatus.getStatus() == World1_Status.level7
                || LevelStatus.getStatus() == World1_Status.level8 || LevelStatus.getStatus() == World1_Status.level9) {
            if (jimmyX + 64 < 390 + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > 406 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + 55 && jimmyY + 64 > 65) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 390, 45);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, new World1_Level3())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",390,45);
            }
        }
        else{
            drawSprite("inactive world token", 390, 45);
        }

        if(LevelStatus.getStatus() == World1_Status.level4 || LevelStatus.getStatus() == World1_Status.level5 || LevelStatus.getStatus() == World1_Status.level6
                || LevelStatus.getStatus() == World1_Status.level7 || LevelStatus.getStatus() == World1_Status.level8
                || LevelStatus.getStatus() == World1_Status.level9) {
            if (jimmyX + 64 < 95 + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > 111 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + 160 && jimmyY + 64 > 170) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 95, 150);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, new World1_Level4())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",95,150);
            }
        }
        else{
            drawSprite("inactive world token", 95, 150);
        }

        if(LevelStatus.getStatus() == World1_Status.level5 || LevelStatus.getStatus() == World1_Status.level6 || LevelStatus.getStatus() == World1_Status.level7
                || LevelStatus.getStatus() == World1_Status.level8 || LevelStatus.getStatus() == World1_Status.level9) {
            if (jimmyX + 64 < 255 + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > 271 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + 175 && jimmyY + 64 > 185) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 255, 165);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, new World1_Level5())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",255,165);
            }
        }
        else{
            drawSprite("inactive world token", 255, 165);
        }

        if(LevelStatus.getStatus() == World1_Status.level6 || LevelStatus.getStatus() == World1_Status.level7
                || LevelStatus.getStatus() == World1_Status.level8 || LevelStatus.getStatus() == World1_Status.level9) {
            if (jimmyX + 64 < 420 + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > 436 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + 180 && jimmyY + 64 > 190) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 420, 170);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, new World1_Level6())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",420,170);
            }
        }
        else{
            drawSprite("inactive world token", 420, 170);
        }

        if(LevelStatus.getStatus() == World1_Status.level7 || LevelStatus.getStatus() == World1_Status.level8 || LevelStatus.getStatus() == World1_Status.level9) {
            if (jimmyX + 64 < 70 + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > 86 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + 310 && jimmyY + 64 > 320) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 70, 300);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, new World1_Level7())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",70,300);
            }
        }
        else{
            drawSprite("inactive world token", 70, 300);
        }

        if(LevelStatus.getStatus() == World1_Status.level8 || LevelStatus.getStatus() == World1_Status.level9) {
            if (jimmyX + 64 < 250 + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > 266 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + 330 && jimmyY + 64 > 340) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 250, 320);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, new World1_Level8())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",250,320);
            }
        }
        else{
            drawSprite("inactive world token", 250, 320);
        }

        if(LevelStatus.getStatus() == World1_Status.level9) {
            if (jimmyX + 64 < 465 + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > 481 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + 330 && jimmyY + 64 > 340) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", 465, 320);
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen(new World1_BossFightScreen(game));
                    this.dispose();
                }
            } else {
                drawSprite("world token",465,320);
            }
        }
        else{
            drawSprite("inactive world token", 465, 320);
        }

        TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);


//checking for key press

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

            if (jimmyX > -25) {
                jimmyX += -SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
                //jimmyMovingX = true;
                //Gdx.input.
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (jimmyX < 485 && jimmyMovingX == false) {
                jimmyX += SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (jimmyY > 0) {
                jimmyY += -SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
                jimmyMovingY = true;

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W )) {
            if (jimmyY < 283 && jimmyMovingY == false) {
                jimmyY += SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
            }
        }

        elapsedTime += Gdx.graphics.getDeltaTime();

        if (animated == false)
            drawSprite("Jimmy 1", jimmyX, jimmyY);

        game.batch.end();

    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show(){

    }

    @Override
    public void pause(){

    }
    @Override
    public void resume(){

    }
    @Override
    public void hide(){

    }
    @Override
    public void dispose(){

    }

}
