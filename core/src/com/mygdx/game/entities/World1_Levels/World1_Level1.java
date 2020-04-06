package com.mygdx.game.entities.World1_Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Level;
import com.mygdx.game.World;
import com.mygdx.game.entities.MapLevelStatus;
import com.mygdx.game.entities.World1;

public class World1_Level1 implements Level{


    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Banger Alert Times a Million Plus Aliens.mp3"));

    Texture evolveSheet;

    TextureAtlas textureAtlas;
    TextureAtlas walkingTextureAtlas;

    Animation<TextureRegion> walkingAnimation;

    String background;
    String jimmyName;
    String jimmyAttackName;
    String jimmyAbsorbName;
    int deathToll;
    MapLevelStatus levelStatus;


    public World1_Level1(){

        walkingTextureAtlas = new TextureAtlas("snorkelling jimmy walk.txt");
        textureAtlas = new TextureAtlas("World1_level1.txt");

        jimmyName = "snorkelling jimmy";
        jimmyAttackName = "snorkelling jimmy attack";
        jimmyAbsorbName = "snorkelling jimmy absorb";
        background = "town";

        walkingAnimation = new Animation<TextureRegion>(0.1f, walkingTextureAtlas.getRegions());
        evolveSheet = new Texture(Gdx.files.internal("dragon evolution.png"));

        deathToll = 10;

        levelStatus = MapLevelStatus.level2;

    }


    public Music getSong(){
        return mainSong;
    }

    public TextureAtlas mainTextureAtlas(){
        return textureAtlas;
    }

    public TextureAtlas walkingTextureAtlas(){
        return walkingTextureAtlas;
    }

    public Texture evolveTexture(){return evolveSheet;}

    public String getJimmyName(){
        return jimmyName;
    }

    public String getJimmyAttackName(){ return jimmyAttackName;}

    public String getJimmyAbsorbName(){return jimmyAbsorbName;}

    public String getBackground(){
        return background;
    }

    public Animation<TextureRegion> walkingAnimation(){
        return walkingAnimation;
    }

    public int deathToll(){
        return deathToll;
    }

    public MapLevelStatus completed(){
        return levelStatus;
    }

    public World worldname(){ return new World1();}

    public String worldString(){ return "World1";}

}
