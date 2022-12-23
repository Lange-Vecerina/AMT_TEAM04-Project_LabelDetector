package org.heig.team04.labeldetector.service;

import java.io.*;
import org.heig.team04.labeldetector.service.exceptions.ExternalServiceException;
import org.heig.team04.labeldetector.service.exceptions.InvalidURLException;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class ServiceInterfaceImp implements ServiceInterface{
    private final RekognitionClient client;

    public ServiceInterfaceImp() {
        client = RekognitionClient.builder()
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .region(Region.EU_WEST_2)
                .build();
    }

    /**
     * get the labels list of the image
     *
     * @param maxLabels     the maximum number of labels to return
     * @param minConfidence the minimum confidence for a label to be returned in percent
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

        try {
            DetectLabelsResponse labelsResponse = client.detectLabels(detectLabelsRequest);
            List<Label> labels = labelsResponse.labels();
            return labels.toString();
        } catch (InvalidImageFormatException e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Detects labels in an image.
     *
     * //@param objectUri     the uri of the image
     * @param maxLabels     the maximum number of labels to return
     * @param minConfidence the minimum confidence for a label to be returned in percent
     * @return String containing the result of the request.
     */
    @Override
    public String analyze(String objectUrl, int maxLabels, float minConfidence) throws IOException, InvalidURLException{
        try {
            URL url = new URL(objectUrl);
            InputStream is = url.openStream();
            String labels = getLabels(maxLabels, minConfidence, is);

            return labels;
        } catch (MalformedURLException e) {
            throw new InvalidURLException("Invalid URL", e);
        } catch (IOException e) {
            throw new IOException("File not found", e);
        }
    }

    /**
     * Detects labels in an image.
     *
     * @param objectBytes   the bytes of the image 64 encoded
     * @param maxLabels     the maximum number of labels to return
     * @param minConfidence the minimum confidence for a label to be returned in percent
     * @return String containing the result of the request.
     */
    @Override
    public String analyzeContent(byte[] objectBytes, int maxLabels, float minConfidence) throws ExternalServiceException {
        try {
            InputStream sourceStream = new ByteArrayInputStream(objectBytes);
            return getLabels(maxLabels, minConfidence, sourceStream);
        } catch (RekognitionException e) {
            throw new ExternalServiceException(e);
        }

    }
}
