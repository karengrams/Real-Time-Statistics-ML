package com.api.realtimestatisticsml.models;

public class Statistic {
    private final Float sum;
    private final Float avg;
    private final Float max;
    private final Float p90;
    private final Float min;
    private final Integer count;


    public Statistic(Float sum, Float avg, Float max, Float p90, Float min, Integer count) {
        this.sum = sum;
        this.avg = avg;
        this.max = max;
        this.p90 = p90;
        this.min = min;
        this.count = count;
    }

}
