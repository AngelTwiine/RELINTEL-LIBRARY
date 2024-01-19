public GaitFeatures analyzeGait(List<double[]> imuData) {
    // ... Existing code for analysis

    double confidenceScore = calculateConfidenceScore(imuData, strides, swingPhases);

    GaitFeatures features = new GaitFeatures(matchResult, confidenceScore);
    features.setIndividualFeatureScores(getIndividualFeatureScores(imuData, strides, swingPhases)); // Map of extracted features to scores
    features.setSpecificComparisons(getSpecificComparisons(imuData, strides, swingPhases)); // Map of feature comparisons to values

    return features;
}

private Map<String, Double> getIndividualFeatureScores(List<double[]> imuData, List<Integer> strides, List
