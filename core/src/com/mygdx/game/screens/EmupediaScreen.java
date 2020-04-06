package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.entities.MapLevelStatus;
import com.mygdx.game.entities.World1_Levels.World1_Level1;
import com.mygdx.game.entities.World1_Levels.World1_Level2;
import com.mygdx.game.entities.World1_Levels.World1_Level3;
import com.mygdx.game.entities.World1_Levels.World1_Level4;
import com.mygdx.game.entities.World1_Levels.World1_Level5;
import com.mygdx.game.entities.World1_Levels.World1_Level6;
import com.mygdx.game.entities.World1_Levels.World1_Level7;
//import com.mygdx.game.entities.World1_Levels.World1_Level8;


import java.util.HashMap;
import java.util.List;


public class EmupediaScreen implements Screen {

    BitmapFont font = new BitmapFont();//(Gdx.files.internal("Calibri.fnt"),Gdx.files.internal("Calibri.png"),false);
    OrthographicCamera camera;
    ExtendViewport viewport;
    TextureAtlas textureAtlas;
    TextureAtlas jimmyTextureAtlas;
    TextureAtlas buttonsTextureAtlas;
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Banger Alert Times a Million Plus Aliens.mp3"));
    Animation<TextureRegion> jimmyAnimation;
    float elapsedTime = 0;
    float prevTime = 0;
    Sprite backgroundSprite = new Sprite();

    public int page = 0;
    public int maxPages = 14;
    String[] emuList;
    private int lastDeathToll;
    private boolean menu;
    private MapLevelStatus levelStatus;

    MyGdxGame game;
    private String[] emuNames = {"Jimmy", "Mutant Jimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "Bat Jimmy", "GYMmy", "Sad Jimmy", "Pirate Jimmy", "Jimmy hits the Slopes", "Galactic Jimmy", "Jimmy Skywalker"};
    private String[] info = {"Just a regular Emu named Jimmy. He enjoys long walks on the beach and is an avid sky",
            "Jimmy has become the most powerful emu on the planet, but how far will his new powers", "", "", "", "", "", "",
            "The most cliche backstory ever",
            "SPEAKS IN ALL CAPS, ALWAYS ASKS WHEN YOU'LL BE DONE YOUR SET", "His Parents 'just don't understand'. ", "Hates filthy landlubbers, doesn't understand common social cues.", "Jimmy had the bird flu and missed the qualifiers for the PyeongChang Winter Olympics", "a long time ago in a galaxy far, far away....", "nothing"};

    private String[] info2 = {"diver.", "take him?", "", "", "", "", "", "", "", "","","","in 2018. Now Jimmy is taking his training to the next level and will surely be a force to be","",""};
    private String[] info3 = {"", "", "", "", "", "", "", "", "", "","","","reckoned with in Beijing 2022.","",""};



    public EmupediaScreen(MyGdxGame game, int deathToll, boolean fromMenu, MapLevelStatus level)
    {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(900, 560, camera);
        textureAtlas = new TextureAtlas("emupedia.txt");
        jimmyTextureAtlas = new TextureAtlas("BouncingJimmy.txt");
        buttonsTextureAtlas = new TextureAtlas("nextBackArrows.txt");
        jimmyAnimation = new Animation<TextureRegion>(0.1f, jimmyTextureAtlas.getRegions());
        addSprites();
        textureAtlas = buttonsTextureAtlas;
        addSprites();

        sprites.get("previousActive").setSize(90,51);
        sprites.get("previousInactive").setSize(90,51);
        sprites.get("nextActive").setSize(90,51);
        sprites.get("nextInactive").setSize(90,51);
        //resize Jimmy sprites...
        sprites.get("silhouetteJimmy").setSize(250,250);
        sprites.get("jimmy").setSize(250,250);
        sprites.get("mutatedJimmy").setSize(250,250);
        sprites.get("batJimmy").setSize(250,250);
        sprites.get("gymmy").setSize(250,250);
        sprites.get("darkJimmy").setSize(250,250);
        sprites.get("pirateJimmy").setSize(250,250);
        sprites.get("jimmySlopes").setSize(250,250);
        sprites.get("spaceJimmy").setSize(250,250);


        sprites.get("hydraJimmy").setSize(250,250);
        sprites.get("realJimmy").setSize(250,250);
        sprites.get("sharkJimmy").setSize(250,250);
        sprites.get("sherifJimmy").setSize(250,250);
        sprites.get("snorkelJimmy").setSize(250,250);
        sprites.get("jimmySkywalker").setSize(250,250);


        //String[] list = {"previousActive", "previousInactive", "previousActive", "previousInactive", "previousActive", "previousInactive", "previousActive", "previousInactive", "previousActive", "previousInactive"};
        lastDeathToll = deathToll;
        menu = fromMenu;
        levelStatus = level;

        if (levelStatus == MapLevelStatus.level1){
            // all silhouettes
            String[] list = {"jimmy", "mutatedJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level2){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy", "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level3){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level4){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level5){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level6){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "spaceJimmy", "silhouetteJimmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level7){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "batJimmy", "silhouetteJimmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level8) {
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "batJimmy", "gymmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level9){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "batJimmy", "gymmy", "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }

        //world 2 now
        else if (levelStatus == MapLevelStatus.level9){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "batJimmy", "gymmy", "sadJimmy" , "pirateJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level9){
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "batJimmy", "gymmy", "sadJimmy" , "pirateJimmy" , "silhouetteJimmy" , "silhouetteJimmy" , "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level9) {
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "batJimmy", "gymmy", "sadJimmy", "pirateJimmy", "jimmySlopes", "silhouetteJimmy", "silhouetteJimmy"};
            emuList = list;
        }
        else if (levelStatus == MapLevelStatus.level9) {
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "batJimmy", "gymmy", "sadJimmy", "pirateJimmy", "jimmySlopes", "spaceJimmy", "silhouetteJimmy"};
            emuList = list;
        }else if (levelStatus == MapLevelStatus.level9) {
            String[] list = {"jimmy", "mutatedJimmy", "sharkJimmy", "hydraJimmy", "sherifJimmy", "realJimmy", "rogueJimmy", "snorkelJimmy", "batJimmy", "gymmy", "sadJimmy", "pirateJimmy", "jimmySlopes", "spaceJimmy", "jimmySkywalker"};
            emuList = list;
        }

        font.setColor(Color.BLACK);
        font.getData().setScale(1.5f);
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
        elapsedTime += Gdx.graphics.getDeltaTime();
        game.batch.begin();

        renderBackground();

        if (Gdx.input.getX() < 10 + (sprites.get("active back arrow").getWidth()+13)  && Gdx.input.getX() > 10 && game.HEIGHT - Gdx.input.getY() < 670 + sprites.get("active back arrow").getHeight() && game.HEIGHT - Gdx.input.getY() > 720 - sprites.get("active back arrow").getHeight()) {
            drawSprite("active back arrow", 10, 555);
            if (Gdx.input.isTouched()) {
                mainSong.stop();
                if (menu) {
                    game.setScreen(new MainMenuScreen(game));
                }
                else{
                    //go back to the level we came from
                    if (levelStatus == MapLevelStatus.level1){
                        game.setScreen((new MainGameScreen(game, new World1_Level1())));
                    }
                    else if (levelStatus == MapLevelStatus.level2){
                        game.setScreen((new MainGameScreen(game, new World1_Level2())));
                    }
                    else if (levelStatus == MapLevelStatus.level3){
                        game.setScreen((new MainGameScreen(game, new World1_Level3())));
                    }
                    else if (levelStatus == MapLevelStatus.level4){
                        game.setScreen((new MainGameScreen(game, new World1_Level4())));
                    }
                    else if (levelStatus == MapLevelStatus.level5){
                        game.setScreen((new MainGameScreen(game, new World1_Level5())));
                    }
                    else if (levelStatus == MapLevelStatus.level6){
                        game.setScreen((new MainGameScreen(game, new World1_Level6())));
                    }
                    else if (levelStatus == MapLevelStatus.level7){
                        game.setScreen((new MainGameScreen(game, new World1_Level7())));
                    }
                    /*else if (levelStatus == MapLevelStatus.level8){
                        game.setScreen((new MainGameScreen(game, new World1_Level8())));
                    }
                    else if (levelStatus == MapLevelStatus.level9){
                        game.setScreen((new MainGameScreen(game, new World1_Level8())));
                    }*/
                }
            }
        }
        else{
            drawSprite("back arrow", 10, 555);
        }

        //next page arrow
        if (page == 0) {

            if (Gdx.input.getX() < 1000 && Gdx.input.getX() > 875 && Gdx.input.getY() < 220  && Gdx.input.getY() > 180) {
                drawSprite("nextActive", 750, 400);
                if (Gdx.input.isTouched()) {
                    if (elapsedTime - prevTime > 0.4) {
                        page++;
                        prevTime = elapsedTime;
                    }
                }
            } else {
                drawSprite("nextInactive", 750, 400);
            }
        }
        else if (page < maxPages) {
            if (Gdx.input.getX() < 850 && Gdx.input.getX() > 725 && Gdx.input.getY() < 220  && Gdx.input.getY() > 180) {
                drawSprite("previousActive", 600, 400);
                if (Gdx.input.isTouched()) {
                    if (elapsedTime - prevTime > 0.4) {
                        page--;
                        prevTime = elapsedTime;
                    }
                }
            } else {
                drawSprite("previousInactive", 600, 400);
            }
            if (Gdx.input.getX() < 1000 && Gdx.input.getX() > 875 && Gdx.input.getY() < 220  && Gdx.input.getY() > 180) {
                drawSprite("nextActive", 750, 400);
                if (Gdx.input.isTouched()) {
                    if (elapsedTime - prevTime > 0.4) {
                        page++;
                        prevTime = elapsedTime;
                    }
                }
            } else {
                drawSprite("nextInactive", 750, 400);
            }
        }
        else {
            if (Gdx.input.getX() < 850 && Gdx.input.getX() > 725 && Gdx.input.getY() < 220  && Gdx.input.getY() > 180) {
                drawSprite("previousActive", 600, 400);
                if (Gdx.input.isTouched()) {
                    if (elapsedTime - prevTime > 0.4) {
                        page--;
                        prevTime = elapsedTime;
                    }
                }
            } else {
                drawSprite("previousInactive", 600, 400);
            }
        }
        // use page variable to index array of sprites to print

        //mainSong.play();


        //print the emu here and corresponding name and info
        if (true){ //this will check if the level has been completed or not
            drawSprite(emuList[page],125, 300);
            font.draw(game.batch, emuNames[page], 550, 505);
            font.draw(game.batch, info[page], 25, 165);
            font.draw(game.batch, info2[page], 25, 120);
            font.draw(game.batch, info3[page], 25, 75);
        }
        TextureRegion currentFrame = jimmyAnimation.getKeyFrame(elapsedTime, true);




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
