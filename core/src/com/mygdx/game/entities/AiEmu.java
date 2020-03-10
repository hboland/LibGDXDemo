package com.mygdx.game.entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import com.mygdx.game.MyGdxGame;

import java.util.HashMap;
import java.util.Random;

 // add diagonal later

public class AiEmu {

    Random random = new Random();
    private String state;
    public String movementDirection;
    private float positionX;
    private float positionY;
    public boolean isMoving;
    public float timeUntilRespawn;
    public float timeSinceDespawn;
    public float emuTime;

    public AiEmu(){
        state = "despawned";
        movementDirection = "none";
        isMoving = false;
        timeUntilRespawn = 10 + random.nextInt(5);
        timeSinceDespawn = 0;
        this.positionX = random.nextInt(400);
        this.positionY = random.nextInt(200);
        emuTime = 0;

    }

    public void newRandomDirection(){
         // add diagonal later
        int num = random.nextInt(4);
        if (num == 3) {
            this.movementDirection = "left";
        }
        else if (num == 2){
            this.movementDirection = "right";
        }
        else if (num == 1){
            this.movementDirection = "up";
        }
        else {
            this.movementDirection = "down";
        }


    }
    public void respawn(){

        Random random = new Random();
        this.positionX = random.nextInt(400);
        this.positionY = random.nextInt(200);
        this.state = "alive";
    }
    public void killed(){
        this.state = "dead";
    }
    public void despawn(){
        this.state = "despawned";
    }
    public float getX(){
        return this.positionX;
    }
    public float getY(){
        return this.positionY;
    }
    public String getState(){
        return this.state;
    }
    public void changeState(String state)
    {
        this.state = state;
    }
    public void changePosition(float x, float y)
    {
        this.positionX = x;
        this.positionY = y;
    }

}
