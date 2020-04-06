package com.mygdx.game.entities.World1_Levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.mygdx.game.Level;
import com.mygdx.game.World;
import com.mygdx.game.entities.MapLevelStatus;
import com.mygdx.game.entities.World1;

public class World1_Level2 implements Level {


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


    public World1_Level2() {

        walkingTextureAtlas = new TextureAtlas("dragon jimmy walk.txt");
        textureAtlas = new TextureAtlas("World1_Level2.txt");
        evolveSheet = new Texture(Gdx.files.internal("gymmy evolution.png"));

        jimmyName = "dragon jimmy";
        jimmyAttackName = "dragon jimmy attack";
        jimmyAbsorbName = "dragon jimmy absorb";
        background = "forest";

        walkingAnimation = new Animation<TextureRegion>(0.3f, walkingTextureAtlas.getRegions());
        deathToll = 10;

        levelStatus = MapLevelStatus.level3;

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
