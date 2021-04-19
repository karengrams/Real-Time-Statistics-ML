package com.api.realtimestatisticsml.utils;

import com.google.common.math.Quantiles;

import java.util.List;

public final class StatisticsUtils {
    public static Double getSum(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).sum();
    }

    public static Double getMax(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
    }

    public static Double getMin(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).min().getAsDouble();
    }

    public static Double getAvg(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }

    public static Double getPercentile(List<Double> values){
        return Quantiles.percentiles().index(90).compute(values);
    }



}
