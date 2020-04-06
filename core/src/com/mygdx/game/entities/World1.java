package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Level;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.World;
import com.mygdx.game.entities.World1_Levels.World1_Level1;
import com.mygdx.game.entities.World1_Levels.World1_Level2;
import com.mygdx.game.entities.World1_Levels.World1_Level3;
import com.mygdx.game.entities.World1_Levels.World1_Level4;
import com.mygdx.game.entities.World1_Levels.World1_Level5;
import com.mygdx.game.entities.World1_Levels.World1_Level6;
import com.mygdx.game.entities.World1_Levels.World1_Level7;
import com.mygdx.game.screens.World1_BossFightScreen;

import java.util.HashMap;

public class World1 implements World {

    Music mainSong = Gdx.audio.newMusic(Gdx.files.internal("You Reposted in the Wrong Dimmadome.mp3"));
    MapLevelStatus levelStatus;

    TextureAtlas textureAtlas;
    TextureAtlas walkingTextureAtlas;
    TextureAtlas walkingTextureAtlasLevel1;
    TextureAtlas walkingTextureAtlasLevel2;
    TextureAtlas walkingTextureAtlasLevel3;
    TextureAtlas walkingTextureAtlasLevel4;
    TextureAtlas walkingTextureAtlasLevel5;
    TextureAtlas walkingTextureAtlasLevel6;
    TextureAtlas walkingTextureAtlasLevel7;
    TextureAtlas walkingTextureAtlasLevel8;
    TextureAtlas walkingTextureAtlasLevel9;


    Animation<TextureRegion> walkingAnimation;

    String name;
    String background;
    String jimmyNameLevel1;
    String jimmyNameLevel2;
    String jimmyNameLevel3;
    String jimmyNameLevel4;
    String jimmyNameLevel5;
    String jimmyNameLevel6;
    String jimmyNameLevel7;


    int level1_x;
    int level2_x;
    int level3_x;
    int level4_x;
    int level5_x;
    int level6_x;
    int level7_x;

    int level1_y;
    int level2_y;
    int level3_y;
    int level4_y;
    int level5_y;
    int level6_y;
    int level7_y;

    MyGdxGame game;



    public World1(){

        name = "World1";
        //walkingTextureAtlas = new TextureAtlas("mapjimmy.txt");
        walkingTextureAtlasLevel1 = new TextureAtlas("snorkelling jimmy map walk.txt");
        walkingTextureAtlasLevel2 = new TextureAtlas("dragon jimmy map walk.txt");
        walkingTextureAtlasLevel3 = new TextureAtlas("gymmy map walk.txt");
        walkingTextureAtlasLevel4 = new TextureAtlas("emo emu map walk.txt");
        walkingTextureAtlasLevel5 = new TextureAtlas("blackbeak map walk.txt");
        walkingTextureAtlasLevel6 = new TextureAtlas("jimmy hits the slopes map walk.txt");
        walkingTextureAtlasLevel7 = new TextureAtlas("jimmy in space map walk .txt");


        textureAtlas = new TextureAtlas("World1 Map.txt");

        jimmyNameLevel1 = "snorkelling jimmy";
        jimmyNameLevel2 = "dragon jimmy";
        jimmyNameLevel3 = "Gymmy";
        jimmyNameLevel4 = "emo emu";
        jimmyNameLevel5 = "blackbeak";
        jimmyNameLevel6 = "Jimmy hits the slopes";
        jimmyNameLevel7 = "Jimmy in space";

        level1_x = 255;
        level2_x = 420;
        level3_x = 335;
        level4_x = 90;
        level5_x = 285;
        level6_x = 100;
        level7_x = 70;

        level1_y = 165;
        level2_y = 170;
        level3_y = 285;
        level4_y = 120;
        level5_y = 50;
        level6_y = 200;
        level7_y = 270;

        background = "Paper Mario map";

        walkingAnimation = new Animation<TextureRegion>(0.1f, walkingTextureAtlas().getRegions());

        //levelStatus = MapLevelStatus.level2;

    }

    public String getname()  {return name;}

    public Music getSong(){
        return mainSong;
    }

    public TextureAtlas mainTextureAtlas(){
        return textureAtlas;
    }

    public TextureAtlas walkingTextureAtlas(){
        if(LevelStatus.getStatus() == MapLevelStatus.level1){
            return walkingTextureAtlasLevel1;}
        if(LevelStatus.getStatus() == MapLevelStatus.level2){
            return walkingTextureAtlasLevel2;}
        if(LevelStatus.getStatus() == MapLevelStatus.level3){
            return walkingTextureAtlasLevel3;}
        if(LevelStatus.getStatus() == MapLevelStatus.level4){
            return walkingTextureAtlasLevel4;}
        if(LevelStatus.getStatus() == MapLevelStatus.level5){
            return walkingTextureAtlasLevel5;}
        if(LevelStatus.getStatus() == MapLevelStatus.level6){
            return walkingTextureAtlasLevel6;}
        else{
            return walkingTextureAtlasLevel7;}
    }

    public String getJimmyName(){
        if(LevelStatus.getStatus() == MapLevelStatus.level1){
            return jimmyNameLevel1;}
        if(LevelStatus.getStatus() == MapLevelStatus.level2){
            return jimmyNameLevel2;}
        if(LevelStatus.getStatus() == MapLevelStatus.level3){
            return jimmyNameLevel3;}
        if(LevelStatus.getStatus() == MapLevelStatus.level4){
            return jimmyNameLevel4;}
        if(LevelStatus.getStatus() == MapLevelStatus.level5){
            return jimmyNameLevel5;}
        if(LevelStatus.getStatus() == MapLevelStatus.level6){
            return jimmyNameLevel6;}
        else{
            return jimmyNameLevel7;}
    }

    public String getBackground(){
        return background;
    }

    public Animation<TextureRegion> walkingAnimation(){
        return walkingAnimation;
    }

    /*public MapLevelStatus completed(){
        return levelStatus;
    }*/

    public int LevelToken_x1(){
        return level1_x;
    }
    public int LevelToken_y1(){
        return level1_y;
    }

    public int LevelToken_x2(){
        return level2_x;
    }
    public int LevelToken_y2(){
        return level2_y;
    }

    public int LevelToken_x3(){
        return level3_x;
    }
    public int LevelToken_y3(){
        return level3_y;
    }

    public int LevelToken_x4(){
        return level4_x;
    }
    public int LevelToken_y4(){
        return level4_y;
    }

    public int LevelToken_x5(){
        return level5_x;
    }
    public int LevelToken_y5(){
        return level5_y;
    }

    public int LevelToken_x6(){
        return level6_x;
    }
    public int LevelToken_y6(){
        return level6_y;
    }

    public int LevelToken_x7(){
        return level7_x;
    }
   public int LevelToken_y7(){
        return level7_y;
    }

    public Level level1(){ return new World1_Level1();}
    public Level level2(){ return new World1_Level2();}
    public Level level3(){ return new World1_Level3();}
    public Level level4(){ return new World1_Level4();}
    public Level level5(){ return new World1_Level5();}
    public Level level6(){ return new World1_Level6();}
    public Level level7(){ return new World1_Level7();}
   //public Screen bossFight(){return new World1_BossFightScreen(game);}

}
