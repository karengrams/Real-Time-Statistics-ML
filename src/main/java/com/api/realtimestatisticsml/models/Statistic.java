package com.api.realtimestatisticsml.models;

public class Statistic {

    private final Double sum;
    private final Double max;
    private final Double min;
    private final Double avg;
    private final long count;
    private final Double p90;


    public Statistic(Double sum, Double max, Double min, Double avg, long count, Double p90) {
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.count = count;
        this.p90 = p90;
    }

    public Double getSum() {
        return this.sum;
    }

    public Double getMax() {
        return this.max;
    }

    public Double getMin() {
        return this.min;
    }

    public Double getAvg() {
        return this.avg;
    }

    public Double getPercentile() {
        return this.p90;
    }


}
