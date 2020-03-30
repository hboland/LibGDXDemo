package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;


import com.badlogic.gdx.Files;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import com.mygdx.game.Twars;


import java.io.IOException;
import java.util.HashMap;

public class Intro implements Screen {


    private MyGdxGame parent; // a field to store our orchestrator
    Stage stage;

    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    TextureAtlas textureAtlas;
    OrthographicCamera camera;
    ExtendViewport viewport;
    SpriteBatch batch;
    float x = 0;
    float y = 0;
    private float elapsed;
    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("STAR WARS THEME - shitty flute version.mp3"));


    public Intro(MyGdxGame game){
        parent = game;     // setting the argument to our field.

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();


           //this.game = game;
    camera = new OrthographicCamera();
    viewport = new ExtendViewport(1080, 720, camera);

        Box2D.init();
        textureAtlas = new TextureAtlas("longago.txt");
        batch = new SpriteBatch();
        addSprites();

    }


    @Override
    public void render (float delta) {

        elapsed += delta;

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //mainSong.play();
        parent.batch.begin();

        drawSprite("longago", -57, 20);

        if (elapsed > 2.0) {
            mainSong.play();
            drawSprite("emuwars", 100, 0);
        }
        if (elapsed > 4.0) {
            try {
                parent.setScreen(new Twars());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        parent.batch.end();

    }

    @Override
    public void dispose () {
        //batch.dispose();
        //img.dispose();
        textureAtlas.dispose();
        sprites.clear();
        //world.dispose();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    private void drawSprite(String name, float x, float y){
        Sprite sprite = sprites.get(name);
        sprite.setPosition(x, y);
        sprite.draw(parent.batch);
    }

    private void addSprites(){
        Array<TextureAtlas.AtlasRegion> regions = textureAtlas.getRegions();

        for(TextureAtlas.AtlasRegion region : regions){
            Sprite sprite = textureAtlas.createSprite(region.name);

            sprites.put(region.name, sprite);
        }
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
    }


    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }
}
