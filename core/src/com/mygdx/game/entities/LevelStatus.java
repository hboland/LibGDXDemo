package com.mygdx.game.entities;

public class LevelStatus {

    static World1_Status status;

    static public void setStatus(World1_Status level) {
        status = level;
    }

    static public World1_Status getStatus(){
        return status;
    }

}