package com.power.max.timer.model;

/**
 * Created by Karsten on 19.09.2015.
 */
public class customTimer {
    private String strName;
    private timerType ttType;
    private double dblDuration;

    public  customTimer() {}

    public customTimer(String name, timerType type, double duration) {
        this.strName = name;
        this.ttType = type;
        this.dblDuration = duration;
    }
}
