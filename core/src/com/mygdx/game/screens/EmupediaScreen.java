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


import java.util.HashMap;


public class EmupediaScreen implements Screen {

    OrthographicCamera camera;
    ExtendViewport viewport;
    TextureAtlas textureAtlas;
    TextureAtlas jimmyTextureAtlas;
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Banger Alert Times a Million Plus Aliens.mp3"));
    Animation<TextureRegion> jimmyAnimation;
    float elapsedTime = 0;
    Sprite backgroundSprite = new Sprite();


    MyGdxGame game;


    public EmupediaScreen(MyGdxGame game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(900, 560, camera);
        textureAtlas = new TextureAtlas("emupedia.txt");
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
        backgroundSprite = sprites.get("blank emupedia page");
        backgroundSprite.setSize(900,600);
        backgroundSprite.draw(game.batch);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        renderBackground();

        if (Gdx.input.getX() < 10 + (sprites.get("active back arrow").getWidth()+13)  && Gdx.input.getX() > 10 && game.HEIGHT - Gdx.input.getY() < 670 + sprites.get("active back arrow").getHeight() && game.HEIGHT - Gdx.input.getY() > 720 - sprites.get("active back arrow").getHeight()) {
            drawSprite("active back arrow", 10, 555);
            if (Gdx.input.isTouched()) {
                mainSong.stop();
                game.setScreen(new MainMenuScreen(game));
            }
        }
        else{
            drawSprite("back arrow", 10, 555);
        }

        //mainSong.play();

        TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);

        drawAnimation(currentFrame, 530, 300);


//start
        /*if (Gdx.input.getX() < 250 + (sprites.get("active settings").getWidth()-26)*2  && Gdx.input.getX() > 300 && game.HEIGHT - Gdx.input.getY() < 325 + sprites.get("active start").getHeight() && game.HEIGHT - Gdx.input.getY() > sprites.get("active start").getHeight()*3 ) {
            // REMEMBER!!! GETY() STARTS AT 0 FROM THE TOP LEFT CORNER AND NOT THE BOTTOM RIGHT LIKE IT TOTALLY SHOULD!!!!! (SPENT HOURS ON THIS)
            drawSprite("active start",250,275);
            if (Gdx.input.isTouched()){
                game.setScreen((new MapOverviewScreen(game)));
                this.dispose();
            }
        }
        else {
            drawSprite("start",250,275);
        }*/


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
