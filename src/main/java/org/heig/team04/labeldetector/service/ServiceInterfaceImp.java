package org.heig.team04.labeldetector.service;

import java.io.*;

import com.amazonaws.util.IOUtils;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.util.List;

@Service
public class ServiceInterfaceImp implements ServiceInterface{
    private final RekognitionClient client;

    public ServiceInterfaceImp() {
        client = RekognitionClient.builder()
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY_ID"), System.getenv("AWS_SECRET_KEY"))))
                .region(Region.EU_WEST_2)
                .build();
    }

    // TODO s3 ?

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
    public String analyze(String objectUri, int maxLabels, float minConfidence) throws IOException {
        try {
            URL url = new URL(objectUri);
            InputStream is = url.openStream();
            String suffix = objectUri.substring(objectUri.lastIndexOf(".") + 1);
            File tempFile = File.createTempFile("tempImg", "." + suffix);
            OutputStream os = Files.newOutputStream(tempFile.toPath());

            IOUtils.copy(is, os);
            is.close();
            os.close();
            InputStream sourceStream = new FileInputStream(tempFile);
            var labels = getLabels(maxLabels, minConfidence, sourceStream);
            tempFile.delete();
            return labels;
        } catch (MalformedURLException e) {
            throw new MalformedURLException("Invalid URL");
        } catch (IOException e) {
            throw new IOException("File not found", e);
        } catch (InvalidImageFormatException e){
            System.out.println(e);
        }
        return null;
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
    public String analyzeContent(byte[] objectBytes, int maxLabels, float minConfidence) {

        try {
            InputStream sourceStream = new ByteArrayInputStream(objectBytes);
            return getLabels(maxLabels, minConfidence, sourceStream);

        } catch (RekognitionException e) {

        }

        return null;
    }
}
