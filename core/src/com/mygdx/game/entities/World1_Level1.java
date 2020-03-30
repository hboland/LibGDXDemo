package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Level;
import com.mygdx.game.MyGdxGame;

import java.util.HashMap;

public class World1_Level1 implements Level{


    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Banger Alert Times a Million Plus Aliens.mp3"));

    TextureAtlas textureAtlas;
    TextureAtlas walkingTextureAtlas;
    TextureAtlas attackTextureAtlas;
    TextureAtlas absorbTextureAtlas;

    Animation<TextureRegion> jimmyAbsorbAnimation;
    Animation<TextureRegion> jimmyAttackAnimation;
    Animation<TextureRegion> walkingAnimation;
    final HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();

    String background;
    String jimmyName;
    int deathToll;
    World1_Status levelStatus;


    // boolean completed = false;


    public World1_Level1(){

        walkingTextureAtlas = new TextureAtlas("radioactive jimmy walking.txt");
        textureAtlas = new TextureAtlas("world1_level1.txt");
        attackTextureAtlas = new TextureAtlas("jimmy attack.txt");
        absorbTextureAtlas = new TextureAtlas("radioactive jimmy absorb.txt");

        jimmyName = "Radioactive Jimmy";
        background = "badwallpaperjpg";

        walkingAnimation = new Animation<TextureRegion>(0.1f, walkingTextureAtlas.getRegions());
        jimmyAttackAnimation = new Animation<TextureRegion>(0.1f, attackTextureAtlas.getRegions());
        jimmyAbsorbAnimation = new Animation<TextureRegion>(0.1f, absorbTextureAtlas.getRegions());

        deathToll = 10;

        levelStatus = World1_Status.level2;


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

    public TextureAtlas attackTextureAtlas(){
        return attackTextureAtlas;
    }

    public TextureAtlas absorbTextureAtlas(){
        return absorbTextureAtlas;
    }

    public String getJimmyName(){
        return jimmyName;
    }

    public String getBackground(){
        return background;
    }

    public Animation<TextureRegion> walkingAnimation(){
        return walkingAnimation;
    }

    public Animation<TextureRegion> attackAnimation(){
        return jimmyAttackAnimation;
    }

    public Animation<TextureRegion> absorbAnimation(){
        return jimmyAbsorbAnimation;
    }

    public int deathToll(){
        return deathToll;
    }

    public World1_Status completed(){
        return levelStatus;
    }
}
