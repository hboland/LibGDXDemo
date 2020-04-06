package com.mygdx.game.entities.World1_Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.mygdx.game.Level;
import com.mygdx.game.World;
import com.mygdx.game.entities.MapLevelStatus;
import com.mygdx.game.entities.World1;

import java.util.HashMap;

public class World1_Level6 implements Level {


    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Banger Alert Times a Million Plus Aliens.mp3"));

    Texture evolveSheet;

    TextureAtlas textureAtlas;
    TextureAtlas walkingTextureAtlas;

    Animation<TextureRegion> walkingAnimation;

    String jimmyName;
    String jimmyAttackName;
    String jimmyAbsorbName;
    String background;
    int deathToll;
    MapLevelStatus levelStatus;


    public World1_Level6() {

        walkingTextureAtlas = new TextureAtlas("jimmy hits the slopes walking.txt");
        textureAtlas = new TextureAtlas("world1_level6.txt");
        evolveSheet = new Texture(Gdx.files.internal("jimmy in space evolution.png"));

        jimmyName = "Jimmy hits the slopes";
        jimmyAttackName = "Jimmy hits the slopes attack";
        jimmyAbsorbName = "Jimmy hits the slopes absorb";
        background = "world1_level2.jpeg";

        walkingAnimation = new Animation<TextureRegion>(0.3f, walkingTextureAtlas.getRegions());
        deathToll = 10;

        levelStatus = MapLevelStatus.level7;

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

}
