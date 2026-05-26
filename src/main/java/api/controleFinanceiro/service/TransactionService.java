package api.controleFinanceiro.service;

import api.controleFinanceiro.data.dto.TransactionRequestDto;
import api.controleFinanceiro.data.dto.TransactionResponseDto;
import api.controleFinanceiro.exception.ResourceNotFoundException;
import api.controleFinanceiro.model.Category;
import api.controleFinanceiro.model.Transaction;
import api.controleFinanceiro.repository.CategoryRepository;
import api.controleFinanceiro.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;


    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
    }

    private TransactionResponseDto toDto(Transaction t) {
        return new TransactionResponseDto(
                t.getId(),
                t.getDescription(),
                t.getAmount(),
                t.getDate(),
                t.getType(),
                t.getCategory().getId(),
                t.getCategory().getName()
        );
    }

    @Transactional
    public TransactionResponseDto save(TransactionRequestDto dto) {
        log.info("Saving one transaction!");
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));

        Transaction t = new Transaction();
        t.setDescription(dto.getDescription());
        t.setAmount(dto.getAmount());
        t.setDate(dto.getDate());
        t.setType(dto.getType());
        t.setCategory(category);

        return toDto(transactionRepository.save(t));
    }

    @Transactional(readOnly = true)
    public List<TransactionResponseDto> findAll(){
        log.info("Finding all transactions!");
        return transactionRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public TransactionResponseDto findById(Long id){
        log.info("Finding one transaction!");
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        return toDto(t);
    }

    @Transactional
    public TransactionResponseDto update(TransactionRequestDto dto, Long id) {
        log.info("Updating one transaction!");
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));

        t.setDescription(dto.getDescription());
        t.setAmount(dto.getAmount());
        t.setDate(dto.getDate());
        t.setType(dto.getType());
        t.setCategory(category);

        return toDto(transactionRepository.save(t));
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting one transaction!");
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with id: " + id));
        transactionRepository.delete(t);
    }
}