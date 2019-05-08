package com.nixsolutions.bukrieiev.api;


public class WindInfo {
    private Double speed;

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "{\"speed\":" + speed +
                '}';
    }
}
