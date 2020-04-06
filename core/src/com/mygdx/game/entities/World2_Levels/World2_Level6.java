package com.mygdx.game.entities.World2_Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Level;
import com.mygdx.game.entities.MapLevelStatus;
import com.mygdx.game.entities.World2;

public class World2_Level6 implements Level {


    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("Banger Alert Times a Million Plus Aliens.mp3"));

    Texture evolveSheet;

    TextureAtlas textureAtlas;
    TextureAtlas walkingTextureAtlas;
    TextureAtlas attackTextureAtlas;
    TextureAtlas absorbTextureAtlas;

    Animation<TextureRegion> jimmyAbsorbAnimation;
    Animation<TextureRegion> jimmyAttackAnimation;
    Animation<TextureRegion> walkingAnimation;

    String background;
    String jimmyName;
    String jimmyAttackName;
    String jimmyAbsorbName;
    int deathToll;
    MapLevelStatus levelStatus;


    public World2_Level6(){

        walkingTextureAtlas = new TextureAtlas("radioactive jimmy walking.txt");
        textureAtlas = new TextureAtlas("World1_level1.txt");
        attackTextureAtlas = new TextureAtlas("jimmy attack.txt");
        absorbTextureAtlas = new TextureAtlas("radioactive jimmy absorb.txt");

        jimmyName = "Radioactive Jimmy";
        jimmyAttackName = "Radioactive Jimmy attack";
        jimmyAbsorbName = "Radioactive Jimmy absorb";
        background = "town";

        walkingAnimation = new Animation<TextureRegion>(0.1f, walkingTextureAtlas.getRegions());
        jimmyAttackAnimation = new Animation<TextureRegion>(1f, attackTextureAtlas.getRegions());
        jimmyAbsorbAnimation = new Animation<TextureRegion>(1f, absorbTextureAtlas.getRegions());
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

    public TextureAtlas attackTextureAtlas(){
        return attackTextureAtlas;
    }

    public TextureAtlas absorbTextureAtlas(){
        return absorbTextureAtlas;
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

    public Animation<TextureRegion> attackAnimation(){
        return jimmyAttackAnimation;
    }

    public Animation<TextureRegion> absorbAnimation(){
        return jimmyAbsorbAnimation;
    }

    public int deathToll(){
        return deathToll;
    }

    public MapLevelStatus completed(){
        return levelStatus;
    }

    public World2 worldname(){ return new World2();}

}
