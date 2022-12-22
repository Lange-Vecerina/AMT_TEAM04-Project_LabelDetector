package org.heig.team04.labeldetector;


import org.heig.team04.labeldetector.service.ServiceInterface;
import org.heig.team04.labeldetector.service.ServiceInterfaceImp;
import org.heig.team04.labeldetector.service.exceptions.ExternalServiceException;
import org.heig.team04.labeldetector.service.exceptions.InvalidURLException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class LabelDetectorServiceTests {
    private static final ServiceInterface SERVICE = new ServiceInterfaceImp();
    private static final String carImageUrl = "https://carwow-uk-wp-3.imgix.net/18015-MC20BluInfinito-scaled-e1666008987698.jpg";
    private static final String treeImageUrl = "https://m.media-amazon.com/images/I/71LP+MOviUL._AC_UL320_.jpg";
    private static byte[] carImageBytes;
    private static byte[] treeImageBytes;
    private static String carImageBucketURL;
    private static String treeImageBucketUrl;


    @Test
    void AnalyzeParametersWithValues() throws IOException, InvalidURLException, ExternalServiceException {
        // given

        // when
        String result = SERVICE.analyze(carImageUrl, 2, 0.9f);
        // then
        assertTrue(result.contains("Car"));

    }


    @Test
    void contextLoads() {
        assertEquals(3, 3);
    }

    @Test
    void failing() {
        assertEquals(2, 2);
    }

}
