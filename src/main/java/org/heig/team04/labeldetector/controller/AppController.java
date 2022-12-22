package org.heig.team04.labeldetector.controller;

import org.heig.team04.labeldetector.dto.SourceDTO;
import org.heig.team04.labeldetector.service.ServiceInterface;
import org.heig.team04.labeldetector.service.exceptions.ExternalServiceException;
import org.heig.team04.labeldetector.service.exceptions.InvalidURLException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/label-detector")
public class AppController {
    private final ServiceInterface appService;

    public AppController(ServiceInterface appService) {
        this.appService = appService;
    }

    @PostMapping ("/analyze")
    public ResponseEntity<String> analyzeUri(@RequestBody SourceDTO source) throws IOException {
        String result;
        try {
            if (source.getUri().equals("")) {
                result = appService.analyzeContent(source.getContent(), source.getMaxLabels(), source.getMinConfidence());
            } else {
                result = appService.analyze(source.getUri(), source.getMaxLabels(), source.getMinConfidence());
            }
        } catch(IOException | InvalidURLException | ExternalServiceException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }

}
