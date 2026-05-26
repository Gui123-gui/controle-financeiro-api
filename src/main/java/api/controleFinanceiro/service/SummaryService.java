package api.controleFinanceiro.service;

import api.controleFinanceiro.data.dto.SummaryDto;
import api.controleFinanceiro.model.TransactionType;
import api.controleFinanceiro.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Slf4j
@Service
public class SummaryService {

    private final TransactionRepository transactionRepository;

    public SummaryService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public SummaryDto calculateSummary(){
        BigDecimal totalIncome = Optional.ofNullable(transactionRepository.sumByType(TransactionType.INCOME))
                .orElse(BigDecimal.ZERO);

        BigDecimal totalExpense = Optional.ofNullable(transactionRepository.sumByType(TransactionType.EXPENSE))
                .orElse(BigDecimal.ZERO);

        return new SummaryDto(totalIncome, totalExpense);
    }

}
