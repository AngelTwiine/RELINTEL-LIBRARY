private double evaluateDataQuality(List<double[]> imuData) {
    double score = 1.0; // Start with maximum score

    // Missing Data Check
    double missingDataPercentage = ((double) countMissingDataPoints(imuData)) / imuData.size();
    if (missingDataPercentage > 0.1) { // Adjust threshold based on tolerance
        score -= missingDataPercentage * 0.5; // Penalize score based on percentage
    }

    // Noise Level Check
    double noiseStdDev = calculateStandardDeviation(imuData);
    if (noiseStdDev > 0.2) { // Adjust threshold based on sensor and data properties
        score -= noiseStdDev * 0.3; // Penalize score based on standard deviation
    }

    // Calibration Check (if applicable)
    // ... Implement logic to check calibration inconsistencies and penalize score accordingly

    return score;
}

private int countMissingDataPoints(List<double[]> imuData) {
    int count = 0;
    for (double[] dataPoint : imuData) {
        for (double value : dataPoint) {
            if (Double.isNaN(value)) {
                count++;
            }
        }
    }
    return count;
}

private double calculateStandardDeviation(List<double[]> imuData) {
    // ... Implement your chosen algorithm for calculating standard deviation across all data points
}

private double assessFeatureConsistency(List<Integer> strides, List<Double> swingPhases) {
    double strideLengthConsistency = calculateStandardDeviation(getStrideLengths(imuData, strides));
    double swingPeriodConsistency = calculateStandardDeviation(swingPhases);

    // Define thresholds for acceptable variance based on expected gait variability
    if (strideLengthConsistency > 0.1 || swingPeriodConsistency > 0.05) {
        return 0.8; // Lower score for inconsistent features
    } else {
        return 1.0; // Maintain full score for consistent features
    }
}

private List<Double> getStrideLengths(List<double[]> imuData, List<Integer> strides) {
    // ... Implement logic to extract stride lengths from IMU data based on stride indices
}

private double checkFeaturePlausibility(List<Double> swingPhases) {
    double score = 1.0; // Start with maximum score

    // Define expected swing period range based on your target population or application context
    double minSwingPeriod = 0.7;
    double maxSwingPeriod = 1.3;

    for (double swingPeriod : swingPhases) {
        if (swingPeriod < minSwingPeriod || swingPeriod > maxSwingPeriod) {
            score -= 0.2; // Penalize score for outliers
        }
    }

    return score;
}
