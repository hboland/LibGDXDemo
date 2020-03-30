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
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.mygdx.game.Level;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.AiEmu;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.LevelStatus;
import com.mygdx.game.entities.World1_Status;

import java.util.HashMap;
import java.util.Random;


import static com.badlogic.gdx.Gdx.files;
//blah blah blah

public class MainGameScreen implements Screen{

    Music mainSong;
    public static final float SPEED = 250; // in pixels per second
    public static final float EMUSPEED = 40; // in pixels per second

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
    int currentDeathToll = 0;
    boolean jimmyMovingX = false;
    boolean jimmyMovingY = false;
    private Rectangle jimmyRectangle;
    private Sprite jimmySprite;
    String jimmyName;
    String background;
    private int respawned = 0;

    Random rand = new Random();
    Sprite backgroundSprite;
    World1_Status levelStatus;

   //AiEmu AiEmus[6];

    AiEmu emu0 = new AiEmu();
    AiEmu emu1 = new AiEmu();
    AiEmu emu2 = new AiEmu();
    AiEmu emu3 = new AiEmu();
    AiEmu emu4 = new AiEmu();
    AiEmu emu5 = new AiEmu();
    AiEmu emu6 = new AiEmu();
    AiEmu aiEmus[] = {emu0, emu1, emu2, emu3, emu4, emu5,emu6};


    Rectangle rec0; //= new Rectangle();
    Rectangle rec1;// = new Rectangle();
    Rectangle rec2;// = new Rectangle();
    Rectangle rec3;// = new Rectangle();
    Rectangle rec4;//= new Rectangle();
    Rectangle rec5;// = new Rectangle();
    Rectangle rec6;// = new Rectangle();
    Rectangle aiRectangle[] = {rec0, rec1, rec2, rec3, rec4, rec5, rec6};

    float emuTime = 0;

    //MyGdxGame constructor
    MyGdxGame game;
    Level level;


    private static final int FRAME_COLS = 4, FRAME_ROWS = 5;
    Animation<TextureRegion> evolveAnimation;
    Texture evolveSheet;
    float stateTime;




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
        jimmyAttackTextureAtlas = level.attackTextureAtlas();
        jimmyAbsorbTextureAtlas = level.absorbTextureAtlas();

        jimmyName = level.getJimmyName();
        background = level.getBackground();

        jimmyAnimation = level.walkingAnimation();
        aiEmuAnimation = new Animation<TextureRegion>(0.f, aiEmuTextureAtlas.getRegions());
        jimmyAttackAnimation = level.attackAnimation();
        jimmyAbsorbAnimation = level.absorbAnimation();
        //addSprites();


        evolveSheet = new Texture(Gdx.files.internal("jimmy hits the slopes evolution.png"));


        TextureRegion[][] tmp = TextureRegion.split(evolveSheet,
                evolveSheet.getWidth() / FRAME_COLS,
                evolveSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] evolveFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                evolveFrames[index++] = tmp[i][j];
            }
        }

        evolveAnimation = new Animation<TextureRegion>(1.5f, evolveFrames);
        stateTime = 0f;

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
        boolean isOverlapping;
        jimmyMovingX = false;
        jimmyMovingY = false;


        // jimmySprite = new Sprite(new Texture(files.internal("Normal Jimmy.png")));

        game.batch.begin();

        renderBackground();


        if (Gdx.input.getX() < 20 + (sprites.get("active back arrow").getWidth()*2)  && Gdx.input.getX() > 20 && game.HEIGHT - Gdx.input.getY() < 670 + sprites.get("active back arrow").getHeight() && game.HEIGHT - Gdx.input.getY() > 670 - sprites.get("active back arrow").getHeight()/2) {
            drawSprite("active back arrow", 10, 315);
            if (Gdx.input.isTouched()) {
                mainSong.stop();
                game.setScreen(new MapOverviewScreen(game));
            }
        }
        else{
            drawSprite("back arrow", 10, 315);
        }


        TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);
        jimmySprite = new Sprite(currentFrame);
        TextureRegion aiEmuFrame = aiEmuAnimation.getKeyFrame(elapsedTime, true);
        TextureRegion jimmyattack = jimmyAttackAnimation.getKeyFrame(elapsedTime, true);
        TextureRegion jimmyabsorb = jimmyAbsorbAnimation.getKeyFrame(elapsedTime, true);



        TextureRegion evolveFrame = evolveAnimation.getKeyFrame(stateTime, false);



        //AI emus here:
        // ideally should go to the top of the class (right below the render background)

        for (int i = 0; i <7; i++){

            aiAnimated = false;

            if (aiEmus[i].getState() == "despawned") {
                if (currentDeathToll == level.deathToll()){
                    LevelStatus.setStatus(levelStatus);
                    mainSong.stop();
                    //stateTime = 0f;
                    game.setScreen(new MapOverviewScreen(game));


                    /* time = elapsedTime;
                    resize(4000, 2000);
                    Gdx.gl.glClearColor(0, 0, 0, 1);
                    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                    game.batch.draw(evolveFrame, 30, 0);
                    stateTime += Gdx.graphics.getDeltaTime();
                    if(time+5 >= elapsedTime){
                        game.setScreen(new MapOverviewScreen(game));

                    }*/
                }

                if (respawned < level.deathToll() - aiEmus.length) {

                    if (aiEmus[i].timeSinceDespawn == aiEmus[i].timeUntilRespawn) {                                      // use this for time of respawn in the future(aiEmus[i].timeUntilRespawn >= 0) {
                        aiEmus[i].respawn();
                        respawned++;
                    }
                    else{
                    /*else if(aiEmus[i].timeSinceDespawn == 0 && currentDeathToll < level.deathToll() - aiEmus.length)
                    {
                        currentDeathToll++;
                        font.draw(game.batch, currentDeathToll + "/" + level.deathToll(), 470, 330);
                    }*/
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

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                if (jimmyX > -40) {
                    jimmyX += -SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;
                    jimmyMovingX = true;

                    jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

                }
            }
        else if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (jimmyX < 435 && jimmyMovingX == false) {
                jimmyX += SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;

                jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (jimmyY > 0) {
                jimmyY += -SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
                jimmyMovingY = true;

                jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

            }
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.W )) {
            if (jimmyY < 220 && jimmyMovingY == false) {
                jimmyY += SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;

                jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);

            }
        }



        elapsedTime += Gdx.graphics.getDeltaTime();


        if (animated == false) {
            drawSprite(jimmyName, jimmyX, jimmyY);

            jimmyRectangle = new Rectangle(jimmyX+32, jimmyY+64, (int) jimmySprite.getWidth()-96, (int) jimmySprite.getHeight()-64);


            for (int i = 0; i <7; i++) {
                if (aiEmus[i].getState() == "alive") {
                    isOverlapping = aiRectangle[i].overlaps(jimmyRectangle);
                    if (isOverlapping) {
                        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                            aiEmus[i].killed();
                            //drawSprite("Radioactive Jimmy attack", jimmyX, jimmyY);
                            drawAnimation(jimmyattack, jimmyX, jimmyY);
                        }
                    }
                }
                else if (aiEmus[i].getState() == "dead") {
                    isOverlapping = aiRectangle[i].overlaps(jimmyRectangle);
                    if (isOverlapping) {
                        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && aiEmus[i].getState() == "dead") {
                            aiEmus[i].despawn();
                            //if(currentDeathToll >= level.deathToll() - aiEmus.length) {
                                currentDeathToll++;
                                font.draw(game.batch, currentDeathToll + "/" + level.deathToll(), 470, 330);
                            //}
                            drawAnimation(jimmyabsorb, jimmyX, jimmyY);
                        }
                    }
                }
            }
        }

        font.draw(game.batch, currentDeathToll + "/" + level.deathToll(), 470, 330);

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

