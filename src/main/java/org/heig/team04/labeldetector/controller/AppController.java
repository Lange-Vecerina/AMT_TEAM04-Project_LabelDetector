package org.heig.team04.labeldetector.controller;

import com.amazonaws.protocol.json.JsonContent;
import org.heig.team04.labeldetector.service.ServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.heig.team04.labeldetector.dto.DTOs;

@RestController
@RequestMapping("/label-detector")
public class AppController {
    private final ServiceInterface appService;

    public AppController(ServiceInterface appService) {
        this.appService = appService;
    }

    @GetMapping ("/analyzeUri")
    public String analyzeUri(@RequestBody DTOs.UriDTO uriDTO) {
        return appService.analyze(uriDTO.getUri(), uriDTO.getMaxLabels(), uriDTO.getMinConfidence());
    }

    @GetMapping ("/analyzeContent")
    public String analyzeContent(@RequestBody DTOs.ContentDTO contentDTO) {
        return appService.analyzeContent(contentDTO.getContent(), contentDTO.getMaxLabels(), contentDTO.getMinConfidence());
    }
}
