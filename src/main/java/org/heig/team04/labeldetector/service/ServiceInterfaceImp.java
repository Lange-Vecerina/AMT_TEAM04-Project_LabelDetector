package org.heig.team04.labeldetector.service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ServiceInterfaceImp implements ServiceInterface{
    private final RekognitionClient client;

    public ServiceInterfaceImp(RekognitionClient rekognitionClient) {
        client = rekognitionClient;
    }

    // TODO s3 ?

    /**
     * get the labels list of the image
     *
     * @param maxLabels     the maximum number of labels to return
     * @param minConfidence the minimum confidence for a label to be returned in
     *                      percent
     * @param sourceStream  the stream of the image
     * @return the labels list
     */
    private String getLabels(int maxLabels, float minConfidence, InputStream sourceStream) {
        SdkBytes sourceBytes = SdkBytes.fromInputStream(sourceStream);

        // Create an Image object for the source image.
        Image souImage = Image.builder()
                .bytes(sourceBytes)
                .build();

        DetectLabelsRequest detectLabelsRequest = DetectLabelsRequest.builder()
                .image(souImage)
                .maxLabels(maxLabels)
                .minConfidence(minConfidence)
                .build();

        DetectLabelsResponse labelsResponse = client.detectLabels(detectLabelsRequest);
        List<Label> labels = labelsResponse.labels();
        return labels.toString();
    }

    /**
     * Detects labels in an image.
     *
     * @param objectUrl     the uri of the image
     * @param maxLabels     the maximum number of labels to return
     * @param minConfidence the minimum confidence for a label to be returned in
     *                      percent
     * @return String containing the result of the request.
     */
    @Override
    public String analyze(String objectUrl, int maxLabels, float minConfidence) {
        try {
            InputStream sourceStream = new FileInputStream(objectUrl);
            return getLabels(maxLabels, minConfidence, sourceStream);
        } catch (RekognitionException | FileNotFoundException e) {
            System.exit(1);
        }
        return null;
    }

    /**
     * Detects labels in an image.
     *
     * @param objectBytes   the bytes of the image 64 encoded
     * @param maxLabels     the maximum number of labels to return
     * @param minConfidence the minimum confidence for a label to be returned in
     *                      percent
     * @return String containing the result of the request.
     */
    @Override
    public String analyze(byte[] objectBytes, int maxLabels, float minConfidence) {

        try {
            InputStream sourceStream = new ByteArrayInputStream(objectBytes);
            return getLabels(maxLabels, minConfidence, sourceStream);

        } catch (RekognitionException e) {
            System.exit(1);
        }

        return null;
    }
}
