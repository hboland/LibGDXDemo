package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.MapLevelStatus;

public interface World {

    String getname();

    Music getSong();

    TextureAtlas mainTextureAtlas();

    TextureAtlas walkingTextureAtlas();

    String getJimmyName();

    String getBackground();

    Animation<TextureRegion> walkingAnimation();

    //MapLevelStatus completed();

    int LevelToken_x1();
    int LevelToken_y1();
    int LevelToken_x2();
    int LevelToken_y2();
    int LevelToken_x3();
    int LevelToken_y3();
    int LevelToken_x4();
    int LevelToken_y4();
    int LevelToken_x5();
    int LevelToken_y5();
    int LevelToken_x6();
    int LevelToken_y6();
    int LevelToken_x7();
    int LevelToken_y7();

    Level level1();
    Level level2();
    Level level3();
    Level level4();
    Level level5();
    Level level6();
    Level level7();
    //Screen bossFight();

}