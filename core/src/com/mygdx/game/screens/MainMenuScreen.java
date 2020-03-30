package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.LevelStatus;
import com.mygdx.game.entities.World1_Status;


import java.util.HashMap;


public class MainMenuScreen implements Screen {

    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("River Sounds.mp3"));
    OrthographicCamera camera;
    ExtendViewport viewport;
    TextureAtlas textureAtlas;
    TextureAtlas jimmyTextureAtlas;
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    Animation<TextureRegion> jimmyAnimation;
    float elapsedTime = 0;
    Sprite backgroundSprite = new Sprite();


    MyGdxGame game;


    public MainMenuScreen(MyGdxGame game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(900, 560, camera);
        textureAtlas = new TextureAtlas("mainmenu.txt");
        jimmyTextureAtlas = new TextureAtlas("BouncingJimmy.txt");
        jimmyAnimation = new Animation<TextureRegion>(0.1f, jimmyTextureAtlas.getRegions());
        addSprites();
    }


    private void drawSprite(String name, float x, float y) {

        Sprite sprite = sprites.get(name);
        sprite.setPosition(x,y);
        sprite.draw(game.batch);
    }


    private void addSprites() {

        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();
        for (TextureAtlas.AtlasRegion region : regions) {
            Sprite sprite = textureAtlas.createSprite(region.name);
            sprites.put(region.name, sprite);
        }
    }


    private void drawAnimation(TextureRegion textureRegion, float x, float y ){

        game.batch.draw(textureRegion,x,y);
    }


    public void renderBackground() {
        backgroundSprite = sprites.get("mainbackground");
        backgroundSprite.setSize(900,600);
        backgroundSprite.draw(game.batch);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        renderBackground();

        mainSong.play();

        drawSprite("Title",250,450);

        TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);

        drawAnimation(currentFrame, 530, 300);


//start
        if (Gdx.input.getX() < 250 + (sprites.get("active settings").getWidth()-26)*2  && Gdx.input.getX() > 300 && game.HEIGHT - Gdx.input.getY() < 325 + sprites.get("active start").getHeight() && game.HEIGHT - Gdx.input.getY() > sprites.get("active start").getHeight()*3 ) {
        drawSprite("active start",250,275);
        if (Gdx.input.isTouched()){
            mainSong.stop();
            LevelStatus.setStatus(World1_Status.level1);
            //game.setScreen(new MapOverviewScreen(game));
            game.setScreen(new World1_BossFightScreen(game));
            this.dispose();
            }
        }
        else {
            drawSprite("start",250,275);
        }

//emupedia
        if (Gdx.input.getX() < 240 + (sprites.get("active settings").getWidth()-26)*2  && Gdx.input.getX() > 300 && game.HEIGHT - Gdx.input.getY() < 230 + sprites.get("active emupedia").getHeight() && game.HEIGHT - Gdx.input.getY() > sprites.get("active emupedia").getHeight()*2+40 ) {
            drawSprite("active emupedia",250,200);
            if (Gdx.input.isTouched()){
                mainSong.stop();
                game.setScreen(new EmupediaScreen(game));
                this.dispose();
            }
        }
        else {
            drawSprite("emupedia",250,200);
        }

//settings
        if (Gdx.input.getX() < 250 + (sprites.get("active settings").getWidth()-26)*2 && Gdx.input.getX() > 300 && game.HEIGHT - Gdx.input.getY() < 140 + sprites.get("active settings").getHeight() && game.HEIGHT - Gdx.input.getY() > sprites.get("active settings").getHeight() + 70) {
            drawSprite("active settings",250,125);
            if (Gdx.input.isTouched()){
                mainSong.stop();
                game.setScreen(new SettingsScreen(game));
                this.dispose();
            }
        }
        else {
            drawSprite("settings",250,125);
        }

//exit
        if (Gdx.input.getX() < 250 + (sprites.get("active exit").getWidth()-26)*2  && Gdx.input.getX() > 300 && game.HEIGHT - Gdx.input.getY() < 50 + sprites.get("active exit").getHeight() && game.HEIGHT - Gdx.input.getY() > sprites.get("active exit").getHeight()/2+40) {
            drawSprite("active exit",250,50);
            if (Gdx.input.isTouched()){
                mainSong.stop();
                Gdx.app.exit(); //this is for an exit or a quit game button
            }
        }
        else {
            drawSprite("exit",250,50);
        }

        elapsedTime += Gdx.graphics.getDeltaTime();
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

        game.batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    @Override
    public void dispose() {

    }
}
