package com.api.realtimestatisticsml.models;

public class Statistic {

    private final double sum;
    private final double max;
    private final double min;
    private final double avg;
    private final double count;
    private final double p90;


    public Statistic(double sum, double max, double min, double avg, double count, double p90) {
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.count = count;
        this.p90 = p90;
    }

    public double getSum() {
        return this.sum;
    }

    public double getMax() {
        return this.max;
    }

    public double getMin() {
        return this.min;
    }

    public double getAvg() {
        return this.avg;
    }

    public double getP90() {
        return this.p90;
    }

    public double getCount() {
        return this.count;
    }

}
