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
import com.mygdx.game.entities.World1_Status;

import java.util.HashMap;
import java.util.Random;

public class World1_BossFightScreen implements Screen{

    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Country Road.mp3"));
    public static final float SPEED = 250; // in pixels per second
    public static final float EMUSPEED = 150; // in pixels per second
    public static final float LASERSPEED = 100;

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
    boolean jimmyMovingX = false;
    boolean jimmyMovingY = false;
    private Rectangle jimmyRectangle;
    private Sprite bossSprite;
    private Sprite laserSprite;
    private int bossHit = 0;


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

        jimmyTextureAtlas = new TextureAtlas("radioactive jimmy walking.txt");
        //aiEmuTextureAtlas = new TextureAtlas("Normal Jimmy.txt");
        textureAtlas = new TextureAtlas("world1_bossfight.txt");
        jimmyAttackTextureAtlas = new TextureAtlas("jimmy attack.txt");
        jimmyAbsorbTextureAtlas = new TextureAtlas("radioactive jimmy absorb.txt");

        jimmyAnimation = new Animation<TextureRegion>(0.1f, jimmyTextureAtlas.getRegions());
        //aiEmuAnimation = new Animation<TextureRegion>(0.1f, aiEmuTextureAtlas.getRegions());
        jimmyAttackAnimation = new Animation<TextureRegion>(0.1f, jimmyAttackTextureAtlas.getRegions());
        jimmyAbsorbAnimation = new Animation<TextureRegion>(0.1f, jimmyAbsorbTextureAtlas.getRegions());
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
        /*boolean isOverlapping;
        boolean isOverlapping2;
        boolean isOverlapping3;
        boolean isOverlapping4;*/

        jimmyMovingX = false;
        jimmyMovingY = false;


        // jimmySprite = new Sprite(new Texture(files.internal("Normal Jimmy.png")));

        game.batch.begin();

        renderBackground();


        if (Gdx.input.getX() < 20 + (sprites.get("active back arrow").getWidth() * 2) && Gdx.input.getX() > 20 && game.HEIGHT - Gdx.input.getY() < 670 + sprites.get("active back arrow").getHeight() && game.HEIGHT - Gdx.input.getY() > 670 - sprites.get("active back arrow").getHeight() / 2) {
            drawSprite("active back arrow", 10, 315);
            if (Gdx.input.isTouched()) {
                mainSong.stop();
                game.setScreen(new MapOverviewScreen(game));
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
                        drawSprite("hydra", boss.getX(), boss.getY());

                        aiAnimated = true;

                        BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                    }

                } else if (boss.movementDirection == "right") {
                    if (boss.getX() < 400) {
                        float x = boss.getX() + EMUSPEED * Gdx.graphics.getDeltaTime();
                        boss.changePosition(x, boss.getY());
                        //drawAnimation(aiEmuFrame, boss.getX(), boss.getY());
                        drawSprite("hydra", boss.getX(), boss.getY());

                        aiAnimated = true;

                        BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                    }

                } else if (boss.movementDirection == "up") {
                    if (boss.getY() < 200) {
                        float y = boss.getY() + EMUSPEED * Gdx.graphics.getDeltaTime();
                        boss.changePosition(boss.getX(), y);
                        //drawAnimation(aiEmuFrame, boss.getX(), boss.getY());
                        drawSprite("hydra", boss.getX(), boss.getY());
                        aiAnimated = true;

                        BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                    }

                } else if (boss.movementDirection == "down") {
                    if (boss.getY() > 0 & boss.getX() < 400) {
                        float y = boss.getY() - EMUSPEED * Gdx.graphics.getDeltaTime();
                        boss.changePosition(boss.getX(), y);
                        //drawAnimation(aiEmuFrame, boss.getX(), boss.getY());
                        drawSprite("hydra", boss.getX(), boss.getY());

                        aiAnimated = true;

                        BossRec = new Rectangle(boss.getX(), boss.getY(), 150, 150);
                    }
                }
            }
            if (aiAnimated == false) {
                //drawSprite("Jimmy 1", boss.getX(), boss.getY());
                drawSprite("hydra", boss.getX(), boss.getY());


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
                laserTime();


                //jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (jimmyX < 435 && jimmyMovingX == false) {
                jimmyX += SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
                laserTime();


                //jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (jimmyY > 0) {
                jimmyY += -SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
                jimmyMovingY = true;
                laserTime();


                // jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W )) {
            if (jimmyY < 220 && jimmyMovingY == false) {
                jimmyY += SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
                laserTime();


                //jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

            }
        }



        elapsedTime += Gdx.graphics.getDeltaTime();

        if (animated == false) {
            drawSprite("Jimmy 1", jimmyX, jimmyY);
            laserTime();
            //jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

            //for (int i = 0; i < 2; i++) {
                /*if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && laser1.getState() == "alive") {
                    laser1.changePosition(jimmyX + 65, jimmyY + 73);
                    drawSprite("laser", laser1.getX(), laser1.getY());
                    laser1.changeState("despawned");

                    laserRec1 = new Rectangle(laser1.getX(), laser1.getY() + 8, 32, 12);

                }

                if (laser1.getState() == "despawned") {
                    float x = laser1.getX() + LASERSPEED * Gdx.graphics.getDeltaTime();
                    laser1.changePosition(x, laser1.getY());
                    drawSprite("laser", laser1.getX(), laser1.getY());

                    aiAnimated = true;

                    laserRec1 = new Rectangle(laser1.getX(), laser1.getY()+8, 32, 12);

                    isOverlapping = laserRec1.overlaps(BossRec);
                    if (isOverlapping) {
                        drawSprite("hydra hit", boss.getX(), boss.getY());
                        laser1.changeState("alive");
                        bossHit++;
                    }
                }
                if (laser1.getX() >= 540) {
                    laser1.changeState("alive");
                }*/
                if(bossHit == 10){
                    mainSong.stop();
                    LevelStatus.setStatus(World1_Status.level1);
                    game.setScreen(new MainMenuScreen(game));
                }
            //}
                    //isOverlapping = BossRec.overlaps(laserRectangles[i]);
            /*if (isOverlapping) {

                    bossHit++;
                    //boss.killed();
                    //drawSprite("Radioactive Jimmy attack", jimmyX, jimmyY);
                    drawAnimation(jimmyattack, jimmyX, jimmyY);
                }*/
            }

        game.batch.end();

    }


    public void laserTime() {

        boolean isOverlapping1 = false;
        boolean isOverlapping2 = false;
        boolean isOverlapping3 = false;
        boolean isOverlapping4 = false;
        boolean isOverlapping5 = false;
        boolean isOverlapping6 = false;
        boolean isOverlapping7 = false;
        boolean isOverlapping8 = false;
        boolean isOverlapping9 = false;
        boolean isOverlapping[] = {isOverlapping1, isOverlapping2, isOverlapping3, isOverlapping4, isOverlapping5, isOverlapping6, isOverlapping7, isOverlapping8, isOverlapping9};


        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && lasers[i].getState() == "alive") {
            lasers[i].changePosition(jimmyX + 65, jimmyY + 73);
            drawSprite("laser", laser1.getX(), laser1.getY());
            lasers[i].changeState("despawned");

            laserRec1 = new Rectangle(lasers[i].getX(), lasers[i].getY() + 8, 32, 12);

        }

        if (lasers[i].getState() == "despawned") {
            float x = lasers[i].getX() + LASERSPEED * Gdx.graphics.getDeltaTime();
            lasers[i].changePosition(x, laser1.getY());
            drawSprite("laser", lasers[i].getX(), lasers[i].getY());


            laserRectangles[i] = new Rectangle(lasers[i].getX(), lasers[i].getY() + 8, 32, 12);

            isOverlapping[i] = laserRectangles[i].overlaps(BossRec);
            if (isOverlapping[i]) {
                drawSprite("hydra hit", boss.getX(), boss.getY());
                lasers[i].changeState("alive");
                bossHit++;
            }
        }
        if (lasers[i].getX() >= 540) {
            lasers[i].changeState("alive");
        }


        /*if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && lasers[i++].getState() == "alive") {
            i++;
            laserTime();
        }*/
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
