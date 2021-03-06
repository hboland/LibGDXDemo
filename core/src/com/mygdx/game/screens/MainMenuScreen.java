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
import com.mygdx.game.Level;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.World;
import com.mygdx.game.entities.LevelStatus;
import com.mygdx.game.entities.MapLevelStatus;
import com.mygdx.game.entities.World2;
import com.mygdx.game.entities.World2_Levels.World2_Level1;


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
    Level level;
    World world;


    public MainMenuScreen(MyGdxGame game)
    {
        this.game = game;
        level = new World2_Level1();
        world = new World2();
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

        drawSprite("game title",250,300);

        TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);

        drawAnimation(currentFrame, 530, 300);


//start
        if (Gdx.input.getX() < 250 + (sprites.get("active settings").getWidth()-26)*2  && Gdx.input.getX() > 300 && game.HEIGHT - Gdx.input.getY() < 325 + sprites.get("active start").getHeight() && game.HEIGHT - Gdx.input.getY() > sprites.get("active start").getHeight()*3 ) {
        drawSprite("active start",250,275);
        if (Gdx.input.isTouched()){
            mainSong.stop();
            LevelStatus.setStatus(MapLevelStatus.level1);
            game.setScreen(new MapOverviewScreen(game, new World2()));
            //game.setScreen(new World1_BossFightScreen(game));
            //game.setScreen(new Intro(game));
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
                game.setScreen(new EmupediaScreen(game, 0, true, level));
                this.dispose();
            }
        }
        else {
            drawSprite("emupedia",250,200);
        }

//exit
        if (Gdx.input.getX() < 250 + (sprites.get("active exit").getWidth()-26)*2 && Gdx.input.getX() > 300 && game.HEIGHT - Gdx.input.getY() < 140 + sprites.get("active exit").getHeight() && game.HEIGHT - Gdx.input.getY() > sprites.get("active exit").getHeight() + 70) {
            drawSprite("active exit",250,125);
            if (Gdx.input.isTouched()){
                mainSong.stop();
                Gdx.app.exit() ;
            this.dispose();
            }
        }
        else {
            drawSprite("exit",250,125);
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
