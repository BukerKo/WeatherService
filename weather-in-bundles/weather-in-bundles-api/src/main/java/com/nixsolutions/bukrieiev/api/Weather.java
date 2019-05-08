package com.nixsolutions.bukrieiev.api;

public class Weather {
    private MainInfo main;
    private WindInfo wind;


    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public WindInfo getWind() {
        return wind;
    }

    public void setWind(WindInfo wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "{\"main\":" + main +
                ",\"wind\":" + wind +
                '}';
    }
}
