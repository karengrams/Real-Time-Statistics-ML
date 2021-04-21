package com.api.realtimestatisticsml.utils;

import com.google.common.math.Quantiles;

import java.util.List;

public final class StatisticsUtils {

    public static double getSum(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).sum();
    }

    public static double getMax(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
    }

    public static double getMin(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).min().getAsDouble();
    }

    public static double getAvg(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }

    public static double getP90(List<Double> values) {
        return Quantiles.percentiles().index(90).compute(values);
    }


}
