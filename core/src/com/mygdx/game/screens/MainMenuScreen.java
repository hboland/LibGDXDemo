package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;


public class MainMenuScreen implements Screen {

    OrthographicCamera camera;
    ExtendViewport viewport;
    TextureAtlas textureAtlas;
    TextureAtlas jimmyTextureAtlas;
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();


    MyGdxGame game;

    public MainMenuScreen(MyGdxGame game)
    {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(1080, 720, camera);
        textureAtlas = new TextureAtlas("Main Menu Textures.txt");
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

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();




        float x =game.WIDTH/2 - sprites.get("startButtonActive").getWidth()/2 - 26;// 26 is for the number of pixels of white space of sprite

        if (Gdx.input.getX() < x + sprites.get("startButtonActive").getWidth()  && Gdx.input.getX() > x && game.HEIGHT - Gdx.input.getY() < 100 + sprites.get("startButtonActive").getHeight() && game.HEIGHT - Gdx.input.getY() > sprites.get("startButtonActive").getHeight() ) {
        // REMEMBER!!! GETY() STARTS AT 0 FROM THE TOP LEFT CORNER AND NOT THE BOTTOM RIGHT LIKE IT TOTALLY SHOULD!!!!! (SPENT HOURS ON THIS)
        drawSprite("startButtonActive",x,100);
        if (Gdx.input.isTouched()){
            //Gdx.app.exit() this is for an exit or a quit game button
            game.setScreen(new MainGameScreen(game));
            this.dispose();
        }
        }
        else {
            drawSprite("startButtonInactive",x,100);;
        }




        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);

        game.batch.setProjectionMatrix(camera.combined);
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
