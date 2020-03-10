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

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.entities.AiEmu;


import java.util.HashMap;
import java.util.Random;

import static com.badlogic.gdx.Gdx.files;
//blah blah blah

public class MainGameScreen implements Screen{

    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Banger Alert Times a Million Plus Aliens.mp3"));
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

    Random rand = new Random();
   Sprite backgroundSprite = new Sprite();





   //AiEmu AiEmus[6];

    AiEmu emu0 = new AiEmu();
    AiEmu emu1 = new AiEmu();
    AiEmu emu2 = new AiEmu();
    AiEmu emu3 = new AiEmu();
    AiEmu emu4 = new AiEmu();
    AiEmu emu5 = new AiEmu();
    AiEmu emu6 = new AiEmu();
    AiEmu aiEmus[] = {emu0, emu1, emu2, emu3, emu4, emu5,emu6};
    float emuTime = 0;



    //MyGdxGame constructor
    MyGdxGame game;

    public MainGameScreen(MyGdxGame game){
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(500, 350, camera);
        jimmyTextureAtlas = new TextureAtlas("Normal Jimmy.txt");
        textureAtlas = new TextureAtlas("sprites.txt");

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
    @Override
    public void show(){

    }

    public void renderBackground() {
        backgroundSprite = sprites.get("badWallpaper");
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

        drawSprite("crate", 0, 0); // this will act as the background for now(not working though:/)

        TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);

        //AI emus here:
        // ideally should go to the top of the class (right below the render background)

        for (int i = 0; i <7; i++){

            aiAnimated = false;

            if (aiEmus[i].getState() == "despawned") {
                if (true) {                                      // use this for time of respawn in the future(aiEmus[i].timeUntilRespawn >= 0) {
                    //spawn emu
                    aiEmus[i].changeState("alive");
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
                            drawAnimation(currentFrame, aiEmus[i].getX(), aiEmus[i].getY());
                            aiAnimated = true;
                        }
                    } else if (aiEmus[i].movementDirection == "right") {
                        if (aiEmus[i].getX() < 400) {
                            float x = aiEmus[i].getX() + EMUSPEED * Gdx.graphics.getDeltaTime();
                            aiEmus[i].changePosition(x, aiEmus[i].getY());
                            drawAnimation(currentFrame, aiEmus[i].getX(), aiEmus[i].getY());
                            aiAnimated = true;
                        }
                    } else if (aiEmus[i].movementDirection == "up") {
                        if (aiEmus[i].getY() < 200) {
                            float y = aiEmus[i].getY() + EMUSPEED * Gdx.graphics.getDeltaTime();
                            aiEmus[i].changePosition(aiEmus[i].getX(), y);
                            drawAnimation(currentFrame, aiEmus[i].getX(), aiEmus[i].getY());
                            aiAnimated = true;
                        }
                    } else if (aiEmus[i].movementDirection == "down") {
                        if (aiEmus[i].getY() > 0 & aiEmus[i].getX() < 400) {
                            float y = aiEmus[i].getY() - EMUSPEED * Gdx.graphics.getDeltaTime();
                            aiEmus[i].changePosition(aiEmus[i].getX(), y);
                            drawAnimation(currentFrame, aiEmus[i].getX(), aiEmus[i].getY());
                            aiAnimated = true;
                        }
                    }
                }
                if (aiAnimated == false) {
                    drawSprite("Jimmy 1", aiEmus[i].getX(), aiEmus[i].getY());
                }
                if (elapsedTime > aiEmus[i].emuTime){ //moving for three seconds after it started moving
                    aiEmus[i].isMoving = false;
                }
            }
            if (aiEmus[i].getState() == ""){

            }
        }

//checking for key press

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                if (jimmyX > 0) {
                    jimmyX += -SPEED * Gdx.graphics.getDeltaTime();
                    drawAnimation(currentFrame, jimmyX, jimmyY);
                    animated = true;
                    //jimmyMovingX = true;
                    //Gdx.input.

                }
            }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (jimmyX < 400 && jimmyMovingX == false) {
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
            if (jimmyY < 200 && jimmyMovingY == false) {
                jimmyY += SPEED * Gdx.graphics.getDeltaTime();
                drawAnimation(currentFrame, jimmyX, jimmyY);
                animated = true;
            }
        }


        elapsedTime += Gdx.graphics.getDeltaTime();

        if (animated == false)
            drawSprite("Jimmy 1", jimmyX, jimmyY);



            //aiEmus[i].timeUntilRespawn
    game.batch.end();

    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);

        game.batch.setProjectionMatrix(camera.combined);
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
