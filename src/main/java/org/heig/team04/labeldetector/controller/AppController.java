package org.heig.team04.labeldetector.controller;

import org.heig.team04.labeldetector.dto.SourceDTO;
import org.heig.team04.labeldetector.service.ServiceInterface;
import org.heig.team04.labeldetector.service.exceptions.ExternalServiceException;
import org.heig.team04.labeldetector.service.exceptions.InvalidURLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


/**
 * This class is the controller of the application.
 *
 * @author Ivan Vecerina, Yanik Lange
 * @version 1.0
 */
@RestController
@RequestMapping("/label-detector")
public class AppController {
    private final ServiceInterface appService;

    public AppController(ServiceInterface appService) {
        this.appService = appService;
    }

    // TODOD ça devrait être un GET pas un POST vu que la resource sur le serveur n'est pas modifié.
    @PostMapping ("/analyze")
    public ResponseEntity<String> analyzeUri(@RequestBody SourceDTO source) throws IOException {
        String result;
        try {
            if (source.getUri().equals("")) {
                result = appService.analyzeContent(source.getContent(), source.getMaxLabels(), source.getMinConfidence());
            } else {
                result = appService.analyze(source.getUri(), source.getMaxLabels(), source.getMinConfidence());
            }
            // TODO le InvalidURLException devrait etre un BadRequest (400) et pas un InternalServerError (500) 
            // vu que l'URL en question est donnée par l'utilisateur
        } catch(IOException | InvalidURLException | ExternalServiceException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

}
