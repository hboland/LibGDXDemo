package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.MapLevelStatus;

public interface Level {

    Music getSong();

    TextureAtlas mainTextureAtlas();
    TextureAtlas walkingTextureAtlas();
    Texture evolveTexture();

    String getJimmyName();
    String getJimmyAttackName();
    String getJimmyAbsorbName();
    String getBackground();

    Animation<TextureRegion> walkingAnimation();

    int deathToll();

    MapLevelStatus completed();

    World worldname();
    String worldString();



}
