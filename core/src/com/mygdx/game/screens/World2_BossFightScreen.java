package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Level;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.AiEmu;
import com.mygdx.game.entities.LevelStatus;
import com.mygdx.game.entities.MapLevelStatus;
import com.mygdx.game.entities.World1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class World2_BossFightScreen implements Screen {

    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Country Road.mp3"));
    public static final float SPEED = 350; // in pixels per second
    public static final float EMUSPEED = 150; // in pixels per second

    public static final int DEFAULT_BACKGROUND_SPEED = 150;
    public static final int BACKGROUND_ACCELERATION = 65;
    public static final int BACKGROUND_GOAL_REACH_ACCELERATION = 1300;

    Texture background;
    float x1,x2;
    int backgroundSpeed;
    int goalSpeed;
    float backgroundScale;
    boolean speedFixed;


    BitmapFont font = new BitmapFont();//(Gdx.files.internal("Calibri.fnt"),Gdx.files.internal("Calibri.png"),false);
    OrthographicCamera camera;
    ExtendViewport viewport;
    TextureAtlas textureAtlas;
    TextureAtlas jimmyTextureAtlas;
    TextureAtlas aiEmuTextureAtlas;
    TextureAtlas jimmyAttackTextureAtlas;
    TextureAtlas jimmyAbsorbTextureAtlas;
    TextureAtlas mainGameTextureAtlas;

    Animation<TextureRegion> jimmyAbsorbAnimation;
    Animation<TextureRegion> jimmyAttackAnimation;
    Animation<TextureRegion> jimmyAnimation;
    Animation<TextureRegion> aiEmuAnimation;
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

    float jimmyY = 100;

    float bananaX = 1000;
    float bananaY = 0;

    float finishLineX = 1000;


    float elapsedTime = 0;
    float prevTime = 0;
    float livePrevTime = 0;
    float finishTime;
    float fireTime = 0;

    boolean jimmyMovingX = false;
    boolean jimmyMovingY = false;
    private Rectangle jimmyRectangle;
    private Rectangle bananaRectangle;
    private Rectangle finishRectangle;

    boolean banana = false;


    private int lives = 3;

    Random rand = new Random();
    Sprite backgroundSprite;
    AiEmu boss = new AiEmu();


    //MyGdxGame constructor
    MyGdxGame game;
    Level level;



    public World2_BossFightScreen(MyGdxGame game){//}, Level level){
        this.game = game;
        this.level = level;
        LevelStatus.setStatus(MapLevelStatus.level1);

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(500, 350, camera);

        jimmyTextureAtlas = new TextureAtlas("radioactive jimmy walking.txt");
        //aiEmuTextureAtlas = new TextureAtlas("Normal Jimmy.txt");
        textureAtlas = new TextureAtlas("BossFight2.txt");
        jimmyAttackTextureAtlas = new TextureAtlas("jimmy attack.txt");
        jimmyAbsorbTextureAtlas = new TextureAtlas("radioactive jimmy absorb.txt");

        jimmyAnimation = new Animation<TextureRegion>(0.1f, jimmyTextureAtlas.getRegions());
        //aiEmuAnimation = new Animation<TextureRegion>(0.1f, aiEmuTextureAtlas.getRegions());
        jimmyAttackAnimation = new Animation<TextureRegion>(0.1f, jimmyAttackTextureAtlas.getRegions());
        jimmyAbsorbAnimation = new Animation<TextureRegion>(0.1f, jimmyAbsorbTextureAtlas.getRegions());
        addSprites();

        background = new Texture("roadScrollingBackground.jpg");

        x1 = 0;
        x2 = background.getWidth();
        backgroundSpeed = 0;
        goalSpeed = BACKGROUND_GOAL_REACH_ACCELERATION;
        backgroundScale = 0;

        speedFixed = false;

        sprites.get("2018-Nissan-Rogue").flip(true,false);
        sprites.get("2018-Nissan-Rogue").setSize(128,64);
        sprites.get("bananaPeel").setSize(50,50);
        sprites.get("zeroLives").setSize(75,75);
        sprites.get("oneLives").setSize(75,75);
        sprites.get("twoLives").setSize(75,75);
        sprites.get("threeLives").setSize(75,75);

        sprites.get("finishLine").rotate(90);
        sprites.get("finishLine").setSize(1500, 100);
        finishTime = Gdx.graphics.getDeltaTime();

        sprites.get("retryInactive").setSize(100,100);
        sprites.get("retryActive").setSize(100,100);
        sprites.get("fire1").setSize(50,57);
        sprites.get("fire2").setSize(50,57);

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
        backgroundSprite = sprites.get("boss fight1 background");
        backgroundSprite.setSize(540,360);
        backgroundSprite.draw(game.batch);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0.1f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainSong.play();

        boolean animated = false;
        boolean aiAnimated;

        jimmyMovingX = false;
        jimmyMovingY = false;


        if (!speedFixed){
            backgroundSpeed += BACKGROUND_ACCELERATION *Gdx.graphics.getDeltaTime();
            if (backgroundSpeed > goalSpeed){
                backgroundSpeed = goalSpeed;
            }
        }

        x1 -= backgroundSpeed*Gdx.graphics.getDeltaTime();
        x2 -= backgroundSpeed*Gdx.graphics.getDeltaTime();

        if (x1 + background.getWidth()* backgroundScale <= 0){
            x1 = x2 + background.getWidth() * backgroundScale;
        }
        if (x2 + background.getWidth()* backgroundScale <= 0) {
            x2 = x1 + background.getWidth() * backgroundScale;
        }


        game.batch.begin();

        //renderBackground();
        game.batch.draw(background, x1, 0, background.getWidth() * backgroundScale, Gdx.graphics.getHeight()/2);
        game.batch.draw(background, x2, 0, background.getWidth() * backgroundScale, Gdx.graphics.getHeight()/2);

        elapsedTime += Gdx.graphics.getDeltaTime();
        if (lives != 0) {

            elapsedTime += Gdx.graphics.getDeltaTime();
            jimmyRectangle = new Rectangle(32, jimmyY + 50, (int) sprites.get("2018-Nissan-Rogue").getWidth() - 20, (int) sprites.get("2018-Nissan-Rogue").getHeight() - 35);
            drawSprite("2018-Nissan-Rogue", 0, jimmyY);
//banana spawn
            if (elapsedTime - prevTime > 0.2 & banana == false) {
                banana = true;
                bananaY = 30 + rand.nextInt(260);
            }
            if (banana == true) {
                bananaX -= backgroundSpeed * Gdx.graphics.getDeltaTime();
                drawSprite("bananaPeel", bananaX, bananaY);
                bananaRectangle = new Rectangle(bananaX + 30, bananaY + 50, (int) sprites.get("bananaPeel").getWidth() - 20, (int) sprites.get("bananaPeel").getHeight() - 30);
                if (bananaRectangle.overlaps(jimmyRectangle) & elapsedTime - livePrevTime > 2) {
                    livePrevTime = elapsedTime;
                    lives--;
                }
                if (bananaX < 0) {
                    bananaX = 1000;
                    banana = false;
                    prevTime = elapsedTime;
                }
            }
            //finish Line
            if (elapsedTime - finishTime > 10) {
                finishLineX -= backgroundSpeed * Gdx.graphics.getDeltaTime();
                finishRectangle = new Rectangle(finishLineX + 120, 50, (int) sprites.get("finishLine").getWidth() - 20, (int) 300);
                drawSprite("finishLine", finishLineX, 0);
                if (finishRectangle.overlaps(jimmyRectangle)) {
                    mainSong.stop();
                    game.setScreen(new MapOverviewScreen(game, new World1()));
                }
            }


//nissan control
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (jimmyY > 30) {
                    jimmyY += -SPEED * Gdx.graphics.getDeltaTime();
                    jimmyMovingY = true;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (jimmyY < 290 && jimmyMovingY == false) {
                    jimmyY += SPEED * Gdx.graphics.getDeltaTime();
                }
            }




        }
        else {
            drawSprite("2018-Nissan-Rogue", 0, jimmyY);

            if (elapsedTime - fireTime< 0.2f)
                drawSprite("fire1", 30, jimmyY + 20);
            else
                drawSprite("fire2", 30, jimmyY + 20);

            if (elapsedTime - fireTime > 0.4f)
                fireTime = elapsedTime;

            if (Gdx.input.getX() < 600  && Gdx.input.getX() > 440 && Gdx.input.getY() < 250 && Gdx.input.getY() > 150 ){
                drawSprite("retryActive",210,200);
                if (Gdx.input.isTouched()){
                    mainSong.stop();
                    LevelStatus.setStatus(MapLevelStatus.level1);
                    game.setScreen(new World2_BossFightScreen(game));
                    this.dispose();
                }
            }
            else {
                drawSprite("retryInactive",210,200);
            }
        }
        if (lives == 3)
            drawSprite("threeLives", 0, -25);
        else if (lives == 2)
            drawSprite("twoLives", 0, -25);
        else if (lives == 1)
            drawSprite("oneLives", 0, -25);
        else {
            drawSprite("zeroLives", 0, -25);
            goalSpeed = 0;
        }

        game.batch.end();
    }

    public void setSpeed(int goalSpeed){
        this.goalSpeed = goalSpeed;
    }

    public void setSpeedFixed(boolean speedFixed){
        this.speedFixed = speedFixed;

    }
    @Override
    public void resize(int width, int height){
        backgroundScale = width / background.getWidth();
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
