package org.heig.team04.labeldetector.controller;

import org.heig.team04.labeldetector.dto.SourceDTO;
import org.heig.team04.labeldetector.service.ServiceInterface;
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
    public String analyzeUri(@RequestBody SourceDTO source) throws IOException {
        if(source.getUri().equals("")){
            return appService.analyzeContent(source.getContent(), source.getMaxLabels(), source.getMinConfidence());
        } else {
            return appService.analyze(source.getUri(), source.getMaxLabels(), source.getMinConfidence());
        }
    }

}
