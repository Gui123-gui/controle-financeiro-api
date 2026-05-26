package api.controleFinanceiro.data.dto;

import api.controleFinanceiro.model.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {

    @NotBlank
    private String description;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private LocalDate date;
    @NotNull
    private TransactionType type;
    @NotNull
    private Long categoryId;
}