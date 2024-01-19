private double calculateConfidenceScore(List<double[]> imuData, List<Integer> strides, List<Double> swingPhases) {
    double dataQualityScore = evaluateDataQuality(imuData);
    double featureConsistencyScore = assessFeatureConsistency(strides, swingPhases);
    double plausibilityScore = checkFeaturePlausibility(swingPhases);

    // Adjust weights based on your priorities (e.g., data quality might be more important)
    double weightDataQuality = 0.4;
    double weightFeatureConsistency = 0.3;
    double weightPlausibility = 0.3;

    return (dataQualityScore * weightDataQuality + featureConsistencyScore * weightFeatureConsistency + plausibilityScore * weightPlausibility) / (weightDataQuality + weightFeatureConsistency + weightPlausibility);
}
