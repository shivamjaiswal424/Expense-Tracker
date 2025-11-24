package dto;

import enums.ExpenseCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ExpenseRequest {
    @NotNull
    private BigDecimal amount;
    private String description;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private ExpenseCategory expenseCategory;
}
