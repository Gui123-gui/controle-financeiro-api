package api.controleFinanceiro.controller;

import api.controleFinanceiro.data.dto.TransactionRequestDto;
import api.controleFinanceiro.data.dto.TransactionResponseDto;
import api.controleFinanceiro.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransactionResponseDto> create(@Valid @RequestBody TransactionRequestDto transaction){
        TransactionResponseDto t = transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> delete(@PathVariable("id") Long id){
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransactionResponseDto> update(@PathVariable Long id,@Valid @RequestBody TransactionRequestDto transaction){
        TransactionResponseDto t = transactionService.update(transaction, id);
        return ResponseEntity.ok(t);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TransactionResponseDto> findById(@PathVariable("id") Long id){
        TransactionResponseDto t = transactionService.findById(id);
        return ResponseEntity.ok(t);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TransactionResponseDto>> findAll(){
        List<TransactionResponseDto> t = transactionService.findAll();
        return ResponseEntity.ok(t);
    }
}
