package org.heig.team04.labeldetector;

import com.amazonaws.util.IOUtils;
import org.heig.team04.labeldetector.service.ServiceInterface;
import org.heig.team04.labeldetector.service.ServiceInterfaceImp;
import org.heig.team04.labeldetector.service.exceptions.ExternalServiceException;
import org.heig.team04.labeldetector.service.exceptions.InvalidURLException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class LabelDetectorServiceTests {
    private static final ServiceInterface SERVICE = new ServiceInterfaceImp();
    private static final String carImageUrl = "https://carwow-uk-wp-3.imgix.net/18015-MC20BluInfinito-scaled-e1666008987698.jpg";

    @Test
    void analyzeCarWebUrl() throws IOException, InvalidURLException, ExternalServiceException {
        // when
        String result = SERVICE.analyze(carImageUrl, 3, 0.8f);
        // then
        assertTrue(result.contains("Car"));
    }

    @Test
    void analyzeCarWebUrlMaxLabels20MinConfidence70() throws ExternalServiceException, IOException, InvalidURLException {
        //when
        String result = SERVICE.analyze(carImageUrl, 20, 0.7f);

        // then
        assertTrue(result.contains("Car"));
    }

    @Test
    void analyzeWithWrongUrl() throws ExternalServiceException, IOException, InvalidURLException {
        // given
        String wrongUrl = "wronghttps://carwow-uk-wp-3.imgix.net/18015-MC20BluInfinito-scaled-e1666008987698.jpg";

        // when
        Exception thrown = assertThrows(
                Exception.class, () -> SERVICE.analyze(wrongUrl, 20, 0.9f)
        );

        // then
        assertTrue(thrown.getMessage().contentEquals("Invalid URL: Invalid URL"));
    }

    @Test
    void analyzeCarWebUrlWithMaxLabel50MinConfidence50() throws ExternalServiceException, IOException, InvalidURLException {
        // when
        String result = SERVICE.analyze(carImageUrl, 50, 0.5f);

        // then
        assertTrue(result.contains("Car"));
    }

    @Test
    void analyzeCarByteArray() throws IOException, InvalidURLException, ExternalServiceException {
        // given
        byte[] carByteArray = IOUtils.toByteArray(new URL(carImageUrl).openStream());

        // when
        String result = SERVICE.analyzeContent(carByteArray, 3, 0.8f);

        // then
        assertTrue(result.contains("Car"));
    }

    @Test
    void analyzeCarByteArrayMaxLabels20MinConfidence70() throws ExternalServiceException, IOException, InvalidURLException {
        // given
        byte[] carByteArray = IOUtils.toByteArray(new URL(carImageUrl).openStream());

        //when
        String result = SERVICE.analyzeContent(carByteArray, 20, 0.7f);

        // then
        assertTrue(result.contains("Car"));
    }

    @Test
    void analyzeCarByteArrayWithMaxLabel50MinConfidence50() throws ExternalServiceException, IOException, InvalidURLException {
        // given
        byte[] carByteArray = IOUtils.toByteArray(new URL(carImageUrl).openStream());

        // when
        String result = SERVICE.analyzeContent(carByteArray, 50, 0.5f);

        // then
        assertTrue(result.contains("Car"));
    }
}
