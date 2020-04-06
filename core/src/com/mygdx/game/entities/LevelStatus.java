package com.mygdx.game.entities;

public class LevelStatus {

    static MapLevelStatus status;

    static public void setStatus(MapLevelStatus level) {
        status = level;
    }

    static public MapLevelStatus getStatus(){
        return status;
    }

}