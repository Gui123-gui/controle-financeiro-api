package api.controleFinanceiro.controller;

import api.controleFinanceiro.data.dto.SummaryDto;
import api.controleFinanceiro.service.SummaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summary")
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping
    public ResponseEntity<SummaryDto> calculateSummary(){
        SummaryDto s = summaryService.calculateSummary();
        return ResponseEntity.ok(s);
    }
}
