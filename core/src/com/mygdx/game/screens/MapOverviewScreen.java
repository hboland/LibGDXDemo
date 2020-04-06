package com.mygdx.game.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.mygdx.game.MyGdxGame;
import com.mygdx.game.World;
import com.mygdx.game.entities.LevelStatus;
import com.mygdx.game.entities.World1_Levels.World1_Level1;
import com.mygdx.game.entities.World1_Levels.World1_Level2;
import com.mygdx.game.entities.World1_Levels.World1_Level3;
import com.mygdx.game.entities.World1_Levels.World1_Level4;
import com.mygdx.game.entities.World1_Levels.World1_Level5;
import com.mygdx.game.entities.World1_Levels.World1_Level6;
import com.mygdx.game.entities.World1_Levels.World1_Level7;
import com.mygdx.game.entities.MapLevelStatus;


import java.util.HashMap;


public class MapOverviewScreen implements Screen{

    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("You Reposted in the Wrong Dimmadome.mp3"));
    public static final float SPEED = 200; // in pixels per second
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
    String jimmyName;
    String background;

    Sprite backgroundSprite;

    //MyGdxGame constructor
    MyGdxGame game;
    World world;

    public MapOverviewScreen(MyGdxGame game, World world){
        this.game = game;
        this.world = world;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(540, 360, camera);

        jimmyTextureAtlas = world.walkingTextureAtlas();
        jimmyAnimation = world.walkingAnimation();
        jimmyName = world.getJimmyName();

        textureAtlas = world.mainTextureAtlas();
        background = world.getBackground();
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
        backgroundSprite = sprites.get(background);
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

        if(LevelStatus.getStatus() == MapLevelStatus.level1 || LevelStatus.getStatus() == MapLevelStatus.level2 || LevelStatus.getStatus() == MapLevelStatus.level3 || LevelStatus.getStatus() == MapLevelStatus.level4 ||
        LevelStatus.getStatus() == MapLevelStatus.level5 || LevelStatus.getStatus() == MapLevelStatus.level6 || LevelStatus.getStatus() == MapLevelStatus.level7
        || LevelStatus.getStatus() == MapLevelStatus.level8 || LevelStatus.getStatus() == MapLevelStatus.level9)
            if (jimmyX + 64 < world.LevelToken_x1() + sprites.get("active world token").getWidth()*2  && jimmyX + 64 > world.LevelToken_x1()+16 && jimmyY + 64 < sprites.get("active world token").getWidth()*2 + world.LevelToken_y1()+10 && jimmyY + 64 > world.LevelToken_y1()+20){//+64 because that's the middle of Jimmy
                drawSprite("active world token", world.LevelToken_x1(), world.LevelToken_y1());
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, world.level1())));
                    this.dispose();
                }
            }
            else{
                drawSprite("world token",world.LevelToken_x1(),world.LevelToken_y1());
            }

        if(LevelStatus.getStatus() == MapLevelStatus.level2 || LevelStatus.getStatus() == MapLevelStatus.level3 || LevelStatus.getStatus() == MapLevelStatus.level4 ||
                LevelStatus.getStatus() == MapLevelStatus.level5 || LevelStatus.getStatus() == MapLevelStatus.level6 || LevelStatus.getStatus() == MapLevelStatus.level7
                || LevelStatus.getStatus() == MapLevelStatus.level8 || LevelStatus.getStatus() == MapLevelStatus.level9) {
            if (jimmyX + 64 < world.LevelToken_x2() + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > world.LevelToken_x2()+16 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + world.LevelToken_y2()+10 && jimmyY + 64 > world.LevelToken_y2()+20) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", world.LevelToken_x2(), world.LevelToken_y2());
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, world.level2())));
                    this.dispose();
                }
            } else {
                drawSprite("world token", world.LevelToken_x2(), world.LevelToken_y2());
            }
        }
        else{
            drawSprite("inactive world token", world.LevelToken_x2(), world.LevelToken_y2());
        }


        if(LevelStatus.getStatus() == MapLevelStatus.level3 || LevelStatus.getStatus() == MapLevelStatus.level4 || LevelStatus.getStatus() == MapLevelStatus.level5
                || LevelStatus.getStatus() == MapLevelStatus.level6 || LevelStatus.getStatus() == MapLevelStatus.level7
                || LevelStatus.getStatus() == MapLevelStatus.level8 || LevelStatus.getStatus() == MapLevelStatus.level9) {
            if (jimmyX + 64 < world.LevelToken_x3() + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > world.LevelToken_x3()+16 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + world.LevelToken_y3()+10 && jimmyY + 64 > world.LevelToken_y3()+20) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", world.LevelToken_x3(), world.LevelToken_y3());
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, world.level3())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",world.LevelToken_x3(),world.LevelToken_y3());
            }
        }
        else{
            drawSprite("inactive world token", world.LevelToken_x3(), world.LevelToken_y3());
        }

        if(LevelStatus.getStatus() == MapLevelStatus.level4 || LevelStatus.getStatus() == MapLevelStatus.level5 || LevelStatus.getStatus() == MapLevelStatus.level6
                || LevelStatus.getStatus() == MapLevelStatus.level7 || LevelStatus.getStatus() == MapLevelStatus.level8
                || LevelStatus.getStatus() == MapLevelStatus.level9) {
            if (jimmyX + 64 < world.LevelToken_x4() + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > world.LevelToken_x4()+16 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + world.LevelToken_y4()+10 && jimmyY + 64 > world.LevelToken_y4()+20) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", world.LevelToken_x4(), world.LevelToken_y4());
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, world.level4())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",world.LevelToken_x4(),world.LevelToken_y4());
            }
        }
        else{
            drawSprite("inactive world token", world.LevelToken_x4(), world.LevelToken_y4());
        }

        if(LevelStatus.getStatus() == MapLevelStatus.level5 || LevelStatus.getStatus() == MapLevelStatus.level6 || LevelStatus.getStatus() == MapLevelStatus.level7
                || LevelStatus.getStatus() == MapLevelStatus.level8 || LevelStatus.getStatus() == MapLevelStatus.level9) {
            if (jimmyX + 64 < world.LevelToken_x5() + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > world.LevelToken_x5()+16 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + world.LevelToken_y5()+10 && jimmyY + 64 > world.LevelToken_y5()+20) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", world.LevelToken_x5(), world.LevelToken_y5());
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, world.level5())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",world.LevelToken_x5(),world.LevelToken_y5());
            }
        }
        else{
            drawSprite("inactive world token", world.LevelToken_x5(), world.LevelToken_y5());
        }

        if(LevelStatus.getStatus() == MapLevelStatus.level6 || LevelStatus.getStatus() == MapLevelStatus.level7
                || LevelStatus.getStatus() == MapLevelStatus.level8 || LevelStatus.getStatus() == MapLevelStatus.level9) {
            if (jimmyX + 64 < world.LevelToken_x6() + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > world.LevelToken_x6()+16 && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + world.LevelToken_y6()+10 && jimmyY + 64 > world.LevelToken_y6()+20) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", world.LevelToken_x6(), world.LevelToken_y6());
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, world.level6())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",world.LevelToken_x6(),world.LevelToken_y6());
            }
        }
        else{
            drawSprite("inactive world token", world.LevelToken_x6(), world.LevelToken_y6());
        }

        if(LevelStatus.getStatus() == MapLevelStatus.level7 || LevelStatus.getStatus() == MapLevelStatus.level8 || LevelStatus.getStatus() == MapLevelStatus.level9) {
            if (jimmyX + 64 < world.LevelToken_x7() + sprites.get("active world token").getWidth() * 2 && jimmyX + 64 > world.LevelToken_x7() && jimmyY + 64 < sprites.get("active world token").getWidth() * 2 + world.LevelToken_y7()+10 && jimmyY + 64 > world.LevelToken_y7()+20) {//&& jimmyY + 64 > 20 + sprites.get("active world token").getHeight()*2 ) { //+64 because that's the middle of Jimmy
                drawSprite("active world token", world.LevelToken_x7(), world.LevelToken_y7());
                if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                    mainSong.stop();
                    game.setScreen((new MainGameScreen(game, world.level7())));
                    this.dispose();
                }
            } else {
                drawSprite("world token",world.LevelToken_x7(),world.LevelToken_y7());
            }
        }
        else{
            drawSprite("inactive world token", world.LevelToken_x7(), world.LevelToken_y7());
        }

        if(LevelStatus.getStatus() == MapLevelStatus.level8) {
            mainSong.stop();
            //game.setScreen((new World1_BossFightScreen(game)));}
            if(world.getname() == "World1"){
                game.setScreen(new World1_BossFightScreen(game));
                this.dispose();
            }
            else if(world.getname() == "World2"){
                game.setScreen(new World2_BossFightScreen(game));
                this.dispose();
            }
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
            drawSprite(jimmyName, jimmyX, jimmyY);

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
