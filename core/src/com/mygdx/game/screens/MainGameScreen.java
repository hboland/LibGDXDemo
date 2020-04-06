package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Screen;
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

import com.mygdx.game.Level;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.World;
import com.mygdx.game.entities.AiEmu;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.LevelStatus;
import com.mygdx.game.entities.MapLevelStatus;
import com.mygdx.game.entities.World1;
import com.mygdx.game.entities.World2;

import java.util.HashMap;
import java.util.Random;

public class MainGameScreen implements Screen{

    Music mainSong;
    Music evolveSound = Gdx.audio.newMusic(Gdx.files.internal("evolve.mp3"));

    public static float SPEED = 250; // in pixels per second
    public static float EMUSPEED = 40; // in pixels per second

    BitmapFont font = new BitmapFont();
    OrthographicCamera camera;
    ExtendViewport viewport;
    TextureAtlas textureAtlas;
    TextureAtlas jimmyTextureAtlas;
    TextureAtlas aiEmuTextureAtlas;
    TextureAtlas mainGameTextureAtlas;

    Animation<TextureRegion> jimmyAnimation;
    Animation<TextureRegion> aiEmuAnimation;
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    float jimmyX = 100;
    float jimmyY = 100;
    float elapsedTime = 0;float
    animatedTime = 0;
    boolean attacking = false;
    boolean absorbing = false;
    int currentDeathToll = 0;
    private int respawned = 0;
    boolean jimmyMovingX = false;
    boolean jimmyMovingY = false;
    boolean isOverlapping = false;
    private Rectangle jimmyRectangle;
    private Sprite jimmySprite;
    String jimmyName;
    String background;

    Random rand = new Random();
    Sprite backgroundSprite;
    MapLevelStatus levelStatus;


    AiEmu emu0 = new AiEmu();
    AiEmu emu1 = new AiEmu();
    AiEmu emu2 = new AiEmu();
    AiEmu emu3 = new AiEmu();
    AiEmu emu4 = new AiEmu();
    AiEmu emu5 = new AiEmu();
    AiEmu emu6 = new AiEmu();
    AiEmu aiEmus[] = {emu0, emu1, emu2, emu3, emu4, emu5,emu6};


    Rectangle rec0;
    Rectangle rec1;
    Rectangle rec2;
    Rectangle rec3;
    Rectangle rec4;
    Rectangle rec5;
    Rectangle rec6;
    Rectangle aiRectangle[] = {rec0, rec1, rec2, rec3, rec4, rec5, rec6};

    //MyGdxGame constructor
    MyGdxGame game;
    Level level;

    private static final int FRAME_COLS = 4, FRAME_ROWS = 5;

    Animation<TextureRegion> evolveAnimation;
    Texture evolveSheet;
    float evolveTime = 0f;


    public MainGameScreen(MyGdxGame game, Level level){
        this.game = game;
        this.level = level;

        mainSong = level.getSong();

        camera = new OrthographicCamera();
        viewport = new ExtendViewport(500, 350, camera);
        levelStatus = level.completed();

        jimmyTextureAtlas = level.walkingTextureAtlas();
        aiEmuTextureAtlas = new TextureAtlas("Normal Jimmy.txt");
        mainGameTextureAtlas = new TextureAtlas("maingame.txt");
        textureAtlas = level.mainTextureAtlas();
        evolveSheet = level.evolveTexture();
        TextureRegion[][] tmp = TextureRegion.split(evolveSheet, evolveSheet.getWidth() / FRAME_COLS, evolveSheet.getHeight() / FRAME_ROWS);


        TextureRegion[] evolveFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                evolveFrames[index++] = tmp[i][j];
            }
        }

        jimmyName = level.getJimmyName();
        background = level.getBackground();

        jimmyAnimation = level.walkingAnimation();
        aiEmuAnimation = new Animation<TextureRegion>(0.1f, aiEmuTextureAtlas.getRegions());
        evolveAnimation = new Animation<>(1.2f, evolveFrames);

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
        Array<TextureAtlas.AtlasRegion> regions2 = mainGameTextureAtlas.getRegions();
        for (TextureAtlas.AtlasRegion region : regions2) {
            Sprite sprite = mainGameTextureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }


    public void renderBackground() {
        backgroundSprite = sprites.get(background);
        backgroundSprite.setSize(540,360);
        backgroundSprite.draw(game.batch);
    }


    @Override
    public void render(float delta){

        Gdx.gl.glClearColor(0, 0.1f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainSong.play();

        boolean animated = false;
        boolean aiAnimated;
        jimmyMovingX = false;
        jimmyMovingY = false;

        game.batch.begin();

        renderBackground();


        if (Gdx.input.getX() < 20 + (sprites.get("active back arrow").getWidth() * 2) && Gdx.input.getX() > 20 && Gdx.input.getY() < game.HEIGHT - 10 &&  Gdx.input.getY() > game.HEIGHT - sprites.get("active back arrow").getHeight()*2.5 ) {
            drawSprite("active back arrow", 10, 10);
            if (Gdx.input.isTouched()) {
                mainSong.stop();
                game.setScreen(new EmupediaScreen(game, currentDeathToll, false, level)); 
            }
        }
        else {
            drawSprite("back arrow", 10, 10);
        }


        TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);
        jimmySprite = new Sprite(currentFrame);
        TextureRegion aiEmuFrame = aiEmuAnimation.getKeyFrame(elapsedTime, true);
        TextureRegion evolveFrame = evolveAnimation.getKeyFrame(evolveTime, false);



        //AI emus here:
        // ideally should go to the top of the class (right below the render background)

        for (int i = 0; i <7; i++){

            aiAnimated = false;

            if (aiEmus[i].getState() == "despawned") {

                //when death toll is hit, play evolution animation and return to map
                if (currentDeathToll == level.deathToll()) {
                    LevelStatus.setStatus(levelStatus);
                    mainSong.stop();
                    resize(4000, 2000);
                    Gdx.gl.glClearColor(0, 0, 0, 1);
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                    game.batch.draw(evolveFrame, 30, 0);
                    evolveSound.play();
                    evolveTime += Gdx.graphics.getDeltaTime();
                    if (evolveTime >= 24) {
                        evolveSound.stop();
                        game.setScreen(new MapOverviewScreen(game, level.worldname()));
                        this.dispose();
                    }
                }

                //respawn enough emus to complete levels
                if (respawned < level.deathToll() - aiEmus.length) {

                    //create delay so emus don't respawn instantly
                    if (aiEmus[i].timeSinceDespawn == aiEmus[i].timeUntilRespawn) {                                      // use this for time of respawn in the future(aiEmus[i].timeUntilRespawn >= 0) {
                        aiEmus[i].respawn();
                        respawned++;
                    }
                    else{
                        aiEmus[i].timeSinceDespawn++;}
                }
            }


            if (aiEmus[i].getState() == "alive") {
                //draw emu

                if (aiEmus[i].isMoving == false) {
                    if (elapsedTime - aiEmus[i].emuTime > 1.5) {
                        aiEmus[i].emuTime = elapsedTime + 1 + rand.nextInt(3);

                        //try to move
                        int randomInt = rand.nextInt(3);
                        if (randomInt == 0 || randomInt == 1 ) {
                            aiEmus[i].newRandomDirection();
                            aiEmus[i].isMoving = true;
                        }
                    }
                }
                if ((aiEmus[i].isMoving == true)) { //sprite is moving
                    if (aiEmus[i].movementDirection == "left") {
                        if (aiEmus[i].getX() > 0) {
                            float x = aiEmus[i].getX() - EMUSPEED * Gdx.graphics.getDeltaTime();
                            aiEmus[i].changePosition(x, aiEmus[i].getY());
                            drawAnimation(aiEmuFrame, aiEmus[i].getX(), aiEmus[i].getY());
                            aiAnimated = true;

                            aiRectangle[i] = new Rectangle(aiEmus[i].getX()+32, aiEmus[i].getY()+64, (int)jimmySprite.getWidth()-96, (int)jimmySprite.getHeight()-64);

                        }

                    }
                    else if (aiEmus[i].movementDirection == "right") {
                        if (aiEmus[i].getX() < 400) {
                            float x = aiEmus[i].getX() + EMUSPEED * Gdx.graphics.getDeltaTime();
                            aiEmus[i].changePosition(x, aiEmus[i].getY());
                            drawAnimation(aiEmuFrame, aiEmus[i].getX(), aiEmus[i].getY());
                            aiAnimated = true;

                            aiRectangle[i] = new Rectangle(aiEmus[i].getX()+32, aiEmus[i].getY()+64, (int)jimmySprite.getWidth()-96, (int)jimmySprite.getHeight()-64);

                        }

                    }
                    else if (aiEmus[i].movementDirection == "up") {
                        if (aiEmus[i].getY() < 200) {
                            float y = aiEmus[i].getY() + EMUSPEED * Gdx.graphics.getDeltaTime();
                            aiEmus[i].changePosition(aiEmus[i].getX(), y);
                            drawAnimation(aiEmuFrame, aiEmus[i].getX(), aiEmus[i].getY());
                            aiAnimated = true;

                            aiRectangle[i] = new Rectangle(aiEmus[i].getX()+32, aiEmus[i].getY()+64, (int)jimmySprite.getWidth()-96, (int)jimmySprite.getHeight()-64);

                        }

                    }
                    else if (aiEmus[i].movementDirection == "down") {
                        if (aiEmus[i].getY() > 0 & aiEmus[i].getX() < 400) {
                            float y = aiEmus[i].getY() - EMUSPEED * Gdx.graphics.getDeltaTime();
                            aiEmus[i].changePosition(aiEmus[i].getX(), y);
                            drawAnimation(aiEmuFrame, aiEmus[i].getX(), aiEmus[i].getY());
                            aiAnimated = true;

                            aiRectangle[i] = new Rectangle(aiEmus[i].getX()+32, aiEmus[i].getY()+64, (int)jimmySprite.getWidth()-96, (int)jimmySprite.getHeight()-64);

                        }
                    }
                }
                if (aiAnimated == false) {
                    drawSprite("Jimmy 1", aiEmus[i].getX(), aiEmus[i].getY());

                    aiRectangle[i] = new Rectangle(aiEmus[i].getX()+32, aiEmus[i].getY()+64, (int)jimmySprite.getWidth()-96, (int)jimmySprite.getHeight()-64);

                }
                if (elapsedTime > aiEmus[i].emuTime){ //moving for three seconds after it started moving
                    aiEmus[i].isMoving = false;
                }
            }

            if (aiEmus[i].getState() == "dead"){
                drawSprite("dead emu", aiEmus[i].getX(), aiEmus[i].getY());
            }
        }

//checking for key press
        if (!attacking & !absorbing) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                if (jimmyX > -40) {
                    jimmyX += -SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;
                    jimmyMovingX = true;

                    jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) jimmySprite.getWidth() - 96, (int) jimmySprite.getHeight() - 64);

                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {

                if (jimmyX < 435 && jimmyMovingX == false) {
                    jimmyX += SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;

                    jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) jimmySprite.getWidth() - 96, (int) jimmySprite.getHeight() - 64);

                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S)) {
                if (jimmyY > 0) {
                    jimmyY += -SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;
                    jimmyMovingY = true;

                    jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) jimmySprite.getWidth() - 96, (int) jimmySprite.getHeight() - 64);

                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                if (jimmyY < 220 && jimmyMovingY == false) {
                    jimmyY += SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;

                    jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) jimmySprite.getWidth() - 96, (int) jimmySprite.getHeight() - 64);

                }
            }
        }


        elapsedTime += Gdx.graphics.getDeltaTime();

        if (animated == false) {
            if (elapsedTime - animatedTime > 0.3f){
                drawSprite(jimmyName, jimmyX, jimmyY);
                attacking = false;
                absorbing = false;
                jimmyRectangle = new Rectangle(jimmyX + 32, jimmyY + 64, (int) jimmySprite.getWidth() - 96, (int) jimmySprite.getHeight() - 64);


                for (int i = 0; i < 7; i++) {
                    if (aiEmus[i].getState() == "alive") {
                        isOverlapping = aiRectangle[i].overlaps(jimmyRectangle);
                        if (isOverlapping) {
                            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                                aiEmus[i].killed();
                                animatedTime = elapsedTime;
                                attacking = true;
                            }
                        }
                    } else if (aiEmus[i].getState() == "dead") {
                        isOverlapping = aiRectangle[i].overlaps(jimmyRectangle);
                        if (isOverlapping) {
                            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && aiEmus[i].getState() == "dead") {
                                aiEmus[i].despawn();
                                currentDeathToll++;
                                font.draw(game.batch, currentDeathToll + "/" + level.deathToll(), 470, 330);
                                animatedTime = elapsedTime;
                                absorbing = true;
                            }
                        }
                    }
                }
            }
            else if (attacking == true)
                drawSprite(level.getJimmyAttackName(), jimmyX, jimmyY);
            else if (absorbing == true)
                drawSprite(level.getJimmyAbsorbName(), jimmyX, jimmyY);

        }

        font.draw(game.batch, "Emus till next Evolution: "+currentDeathToll + "/" + level.deathToll(), 330, 330);

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

