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

public class World2_Level3  implements Level {


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


    public World2_Level3(){

        walkingTextureAtlas = new TextureAtlas("hydra jimmy walk.txt");
        textureAtlas = new TextureAtlas("World2_level3.txt");

        jimmyName = "Hydra Jimmy";
        jimmyAttackName = "Hydra Jimmy attack";
        jimmyAbsorbName = "Hydra Jimmy absorb";
        background = "forest background";

        walkingAnimation = new Animation<TextureRegion>(0.1f, walkingTextureAtlas.getRegions());
        evolveSheet = new Texture(Gdx.files.internal("cowboy evolution.png"));

        deathToll = 10;

        levelStatus = MapLevelStatus.level4;

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

    public World2 worldname(){ return new World2();}

    public String worldString(){ return "World2";}

}
