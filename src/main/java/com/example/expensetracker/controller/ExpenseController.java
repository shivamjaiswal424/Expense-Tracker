package com.example.expensetracker.controller;

import com.example.expensetracker.dto.ExpenseRequest;
import jakarta.validation.Valid;
import com.example.expensetracker.model.Expense;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.example.expensetracker.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin(origins="*")
public class ExpenseController {
    public final ExpenseService expenseService;
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @GetMapping
    List<Expense> list(@RequestParam(required = false) String period,
                       @RequestParam(required = false) LocalDate startDate,
                       @RequestParam(required = false) LocalDate endDate,
                       Authentication authentication) {
        return expenseService.listExpenses(period, startDate, endDate, authentication);
    }

    @PostMapping
    public Expense create(@Valid @RequestBody ExpenseRequest expenseRequest,Authentication authentication) {
        return expenseService.createExpense(expenseRequest, authentication);
    }
    @PutMapping("/{id}")
    public Expense update(@PathVariable Long id,@Valid @RequestBody ExpenseRequest expenseRequest,Authentication authentication) {
        return expenseService.updateExpense(id, expenseRequest, authentication);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id,Authentication authentication) {
        expenseService.deleteExpense(id, authentication);
    }
}
