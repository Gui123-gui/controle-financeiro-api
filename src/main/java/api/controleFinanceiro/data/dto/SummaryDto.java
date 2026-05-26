package api.controleFinanceiro.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record SummaryDto(
        BigDecimal totalIncome,
        BigDecimal totalExpense
) {
    @JsonProperty("balance")
    public BigDecimal balance() {
        return totalIncome.subtract(totalExpense);
    }
}
