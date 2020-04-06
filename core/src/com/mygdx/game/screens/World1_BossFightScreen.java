package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

import java.util.HashMap;
import java.util.Random;

public class World1_BossFightScreen implements Screen{

    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Country Road.mp3"));
    public static final float SPEED = 250; // in pixels per second
    public static final float EMUSPEED = 150; // in pixels per second
    public static final float LASERSPEED = 600;

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
    float jimmyX = 100;
    float jimmyY = 100;
    float elapsedTime = 0;
    float laserTime = 0;
    float laserDirectionTime = 0;
    float livePrevTime = 0;
    boolean jimmyMovingX = false;
    boolean jimmyMovingY = false;
    boolean left = false;
    private Rectangle jimmyRectangle;
    private Sprite bossSprite;
    private Sprite laserSprite;
    private int bossHit = 0;
    private int lives = 3;

    Random rand = new Random();
    Sprite backgroundSprite;
    AiEmu boss = new AiEmu();

    AiEmu laser1 = new AiEmu();
    AiEmu laser2 = new AiEmu();
    AiEmu laser3 = new AiEmu();
    AiEmu laser4 = new AiEmu();
    AiEmu laser5 = new AiEmu();
    AiEmu laser6 = new AiEmu();
    AiEmu laser7 = new AiEmu();
    AiEmu laser8 = new AiEmu();
    AiEmu laser9 = new AiEmu();
    AiEmu lasers[] = {laser1, laser2, laser3, laser4, laser5, laser6, laser7, laser8, laser9};

    boolean laser_1 = false;
    boolean laser_2 = false;
    boolean laser_3 = false;

    float laser1X, laser2X, laser3X, laser1Y, laser2Y, laser3Y;

    Rectangle BossRec;

    Rectangle laserRec1;
    Rectangle laserRec2;
    Rectangle laserRec3;
    Rectangle laserRec4;
    Rectangle laserRec5;
    Rectangle laserRec6;
    Rectangle laserRec7;
    Rectangle laserRec8;
    Rectangle laserRec9;
    Rectangle laserRectangles[] = {laserRec1, laserRec2, laserRec3, laserRec4, laserRec5, laserRec6, laserRec7, laserRec8, laserRec9};

    Rectangle testRec = new Rectangle(0,0,100, 100);
    Rectangle testRec2 = new Rectangle(0,250,100, 100);
    Rectangle testRec3 = new Rectangle(400,0,100, 100);
    Rectangle testRec4 = new Rectangle(400, 250, 100, 100);

    int i =0;


    float emuTime = 0;

    //MyGdxGame constructor
    MyGdxGame game;
    Level level;


    public World1_BossFightScreen(MyGdxGame game){//}, Level level){
        this.game = game;
        this.level = level;

        //mainSong = level.getSong();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(500, 350, camera);

        jimmyTextureAtlas = new TextureAtlas("jimmy in space walk.txt");
        //aiEmuTextureAtlas = new TextureAtlas("Normal Jimmy.txt");
        textureAtlas = new TextureAtlas("world1_bossfight.txt");
        //jimmyAttackTextureAtlas = new TextureAtlas("Jimmy in space attack.txt");
        //jimmyAbsorbTextureAtlas = new TextureAtlas("Jimmy in space absorb.txt");

        jimmyAnimation = new Animation<TextureRegion>(0.1f, jimmyTextureAtlas.getRegions());
        //aiEmuAnimation = new Animation<TextureRegion>(0.1f, aiEmuTextureAtlas.getRegions());
        //jimmyAttackAnimation = new Animation<TextureRegion>(0.1f, jimmyAttackTextureAtlas.getRegions());
        //jimmyAbsorbAnimation = new Animation<TextureRegion>(0.1f, jimmyAbsorbTextureAtlas.getRegions());
        addSprites();
        textureAtlas = new TextureAtlas("BossFight2.txt");
        addSprites();

        sprites.get("zeroLives").setSize(75,75);
        sprites.get("oneLives").setSize(75,75);
        sprites.get("twoLives").setSize(75,75);
        sprites.get("threeLives").setSize(75,75);

        sprites.get("retryInactive").setSize(100,100);
        sprites.get("retryActive").setSize(100,100);

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
        backgroundSprite = sprites.get("space background");
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
        /*boolean isOverlapping;
        boolean isOverlapping2;
        boolean isOverlapping3;
        boolean isOverlapping4;*/

        jimmyMovingX = false;
        jimmyMovingY = false;


        // jimmySprite = new Sprite(new Texture(files.internal("Normal Jimmy.png")));

        game.batch.begin();

        renderBackground();

        if (lives != 0) {
            if (Gdx.input.getX() < 20 + (sprites.get("active back arrow").getWidth() * 2) && Gdx.input.getX() > 20 && game.HEIGHT - Gdx.input.getY() < 670 + sprites.get("active back arrow").getHeight() && game.HEIGHT - Gdx.input.getY() > 670 - sprites.get("active back arrow").getHeight() / 2) {
                drawSprite("active back arrow", 10, 315);
                if (Gdx.input.isTouched()) {
                    mainSong.stop();
                    //game.setScreen(new MapOverviewScreen(game, ));
                }
            } else {
                drawSprite("back arrow", 10, 315);
            }

            TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);

            //AI emus here:
            // ideally should go to the top of the class (right below the render background)


            aiAnimated = false;


            if (boss.getState() == "alive") {
                //draw emu

                if (boss.isMoving == false) {
                    if (elapsedTime - boss.emuTime > 1.5) {
                        boss.emuTime = elapsedTime + rand.nextInt(3);

                        //try to move
                        int randomInt = rand.nextInt(3);
                        if (randomInt == 0 || randomInt == 1) {
                            boss.newRandomDirection();
                            boss.isMoving = true;
                        }
                    }
                }
                if ((boss.isMoving == true)) { //sprite is moving
                    if (boss.movementDirection == "left") {
                        if (boss.getX() > 0) {
                            float x = boss.getX() - EMUSPEED * Gdx.graphics.getDeltaTime();
                            boss.changePosition(x, boss.getY());
                            // drawAnimation(aiEmuFrame, boss.getX(), boss.getY());
                            drawSprite("death star", boss.getX(), boss.getY());

                            aiAnimated = true;

                            BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                        }

                    } else if (boss.movementDirection == "right") {
                        if (boss.getX() < 400) {
                            float x = boss.getX() + EMUSPEED * Gdx.graphics.getDeltaTime();
                            boss.changePosition(x, boss.getY());
                            //drawAnimation(aiEmuFrame, boss.getX(), boss.getY());
                            drawSprite("death star", boss.getX(), boss.getY());

                            aiAnimated = true;

                            BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                        }

                    } else if (boss.movementDirection == "up") {
                        if (boss.getY() < 200) {
                            float y = boss.getY() + EMUSPEED * Gdx.graphics.getDeltaTime();
                            boss.changePosition(boss.getX(), y);
                            //drawAnimation(aiEmuFrame, boss.getX(), boss.getY());
                            drawSprite("death star", boss.getX(), boss.getY());
                            aiAnimated = true;

                            BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                        }

                    } else if (boss.movementDirection == "down") {
                        if (boss.getY() > 0 & boss.getX() < 400) {
                            float y = boss.getY() - EMUSPEED * Gdx.graphics.getDeltaTime();
                            boss.changePosition(boss.getX(), y);
                            //drawAnimation(aiEmuFrame, boss.getX(), boss.getY());
                            drawSprite("death star", boss.getX(), boss.getY());

                            aiAnimated = true;

                            BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                        }
                    }
                }
                if (aiAnimated == false) {

                    drawSprite("death star", boss.getX(), boss.getY());


                    BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                }
                if (elapsedTime > boss.emuTime) { //moving for three seconds after it started moving
                    boss.isMoving = false;
                }
            }


//checking for key press

            if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                if (jimmyX > -40) {
                    jimmyX += -SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;
                    jimmyMovingX = true;


                    jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) sprites.get("Jimmy in space").getWidth() - 96, (int) sprites.get("Jimmy in space").getHeight() - 64);

                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {

                if (jimmyX < 435 && jimmyMovingX == false) {
                    jimmyX += SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;


                    jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) sprites.get("Jimmy in space").getWidth() - 96, (int) sprites.get("Jimmy in space").getHeight() - 64);

                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (jimmyY > 0) {
                    jimmyY += -SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;
                    jimmyMovingY = true;


                    jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) sprites.get("Jimmy in space").getWidth() - 96, (int) sprites.get("Jimmy in space").getHeight() - 64);

                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (jimmyY < 220 && jimmyMovingY == false) {
                    jimmyY += SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;


                    jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) sprites.get("Jimmy in space").getWidth() - 96, (int) sprites.get("Jimmy in space").getHeight() - 64);

                }
            }


            elapsedTime += Gdx.graphics.getDeltaTime();

            if (animated == false) {
                drawSprite("Jimmy in space", jimmyX, jimmyY);
                jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) sprites.get("Jimmy in space").getWidth() - 96, (int) sprites.get("Jimmy in space").getHeight() - 64);
            }


            //lasers

            //direction here
            if (elapsedTime - laserDirectionTime > 1.5f) {
                if (jimmyX > boss.getX())
                    left = true;
                else
                    left = false;
                laserDirectionTime = elapsedTime;
            }

            if (elapsedTime - livePrevTime > 2) {
                if (laser_1 == false & elapsedTime - laserTime > 1.5) {
                    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                        laser_1 = true;
                        laser1X = jimmyX + 30;
                        laser1Y = jimmyY + 70;
                    }
                }
                if (laser_1 == true) {
                    if (left == false)
                        laser1X += LASERSPEED * Gdx.graphics.getDeltaTime();
                    else
                        laser1X -= LASERSPEED * Gdx.graphics.getDeltaTime();

                    drawSprite("laser", laser1X, laser1Y);
                    laserRec1 = new Rectangle(laser1X, laser1Y, 32, 12);
                    if (laserRec1.overlaps(BossRec) || laser1X > 500 || laser1X < 0) {
                        laser_1 = false;
                        laserTime = elapsedTime;
                        if (laserRec1.overlaps(BossRec)){
                            drawSprite("death star hit", boss.getX(), boss.getY());
                             bossHit++;
                        }
                    }
                }


                // laser 2
                if (laser_2 == false & elapsedTime - laserTime > 0.5f) {
                    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                        laser_2 = true;
                        laser2X = jimmyX + 30;
                        laser2Y = jimmyY + 70;
                    }
                }
                if (laser_2 == true) {
                    if (left == false)
                        laser2X += LASERSPEED * Gdx.graphics.getDeltaTime();
                    else
                        laser2X -= LASERSPEED * Gdx.graphics.getDeltaTime();

                    drawSprite("laser", laser2X, laser2Y);
                    laserRec2 = new Rectangle(laser2X, laser2Y, 32, 12);
                    if (laserRec2.overlaps(BossRec) || laser2X > 500 || laser2X < 0) {
                        laser_2 = false;
                        if (laserRec2.overlaps(BossRec)) {
                            drawSprite("death star hit", boss.getX(), boss.getY());
                            bossHit++;
                        }
                        //laserTime = elapsedTime;
                    }
                }

                // laser 3
                if (laser_3 == false & elapsedTime - laserTime > 1f) {
                    if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                        laser_3 = true;
                        laser3X = jimmyX + 30;
                        laser3Y = jimmyY + 70;
                    }
                }
                if (laser_3 == true) {
                    if (left == false)
                        laser3X += LASERSPEED * Gdx.graphics.getDeltaTime();
                    else
                        laser3X -= LASERSPEED * Gdx.graphics.getDeltaTime();

                    drawSprite("laser", laser3X, laser3Y);
                    laserRec3 = new Rectangle(laser3X, laser3Y, 32, 12);
                    if (laserRec3.overlaps(BossRec) || laser3X > 500 || laser3X < 0) {
                        if (laserRec3.overlaps(BossRec)){
                            drawSprite("death star hit", boss.getX(), boss.getY());
                            bossHit++;}

                        laser_3 = false;
                        //laserTime = elapsedTime;d
                    }
                }

                if (bossHit == 15) {
                    mainSong.stop();
                    LevelStatus.setStatus(MapLevelStatus.level1);
                    game.setScreen(new MainMenuScreen(game));
                }
            }

            //lives
            if (jimmyRectangle.overlaps(BossRec) & elapsedTime - livePrevTime > 2) {
                livePrevTime = elapsedTime;
                lives--;
            }

        }
        else{ // lives = 0
            // draw dead emu here drawSprite("dead");
            if (Gdx.input.getX() < 600  && Gdx.input.getX() > 440 && Gdx.input.getY() < 250 && Gdx.input.getY() > 150 ){
                drawSprite("retryActive",210,200);
                if (Gdx.input.isTouched()){
                    mainSong.stop();
                    game.setScreen(new World1_BossFightScreen(game));
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
        }

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
