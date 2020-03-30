package com.mygdx.game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.World1_Status;

public interface Level {

    public Music getSong();

    public TextureAtlas mainTextureAtlas();
    public TextureAtlas walkingTextureAtlas();
    public TextureAtlas attackTextureAtlas();
    public TextureAtlas absorbTextureAtlas();

    public String getJimmyName();
    public String getBackground();

    public Animation<TextureRegion> walkingAnimation();
    public Animation<TextureRegion> attackAnimation();
    public Animation<TextureRegion> absorbAnimation();

    public int deathToll();

    public World1_Status completed();

}
