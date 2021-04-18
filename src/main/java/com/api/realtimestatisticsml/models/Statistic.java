package com.api.realtimestatisticsml.models;

public class Statistic {

    private final Double sum;
    private final Double max;
    private final Double min;
    private final Double avg;
    private final long count;

    public Statistic(Double sum, Double max, Double min, Double avg, long count) {
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.avg = avg;
        this.count = count;
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

    public long getCount() {
        return this.count;
    }
}
