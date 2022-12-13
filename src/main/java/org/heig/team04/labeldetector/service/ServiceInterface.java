package org.heig.team04.labeldetector.service;

public interface ServiceInterface {

    /**
     * Detects labels in an image.
     *
     * @param objectUri     the uri of the image
     * @param maxLabels     the maximum number of labels to return
     * @param minConfidence the minimum confidence for a label to be returned in
     *                      percent
     * @return String containing the result of the request.
     */
    String analyze(String objectUri, int maxLabels, float minConfidence);

    /**
     * Detects labels in an image.
     *
     * @param objectBytes   the bytes of the image 64 encoded
     * @param maxLabels     the maximum number of labels to return
     * @param minConfidence the minimum confidence for a label to be returned in
     *                      percent
     * @return String containing the result of the request.
     */
    String analyze(byte[] objectBytes, int maxLabels, float minConfidence);
}
