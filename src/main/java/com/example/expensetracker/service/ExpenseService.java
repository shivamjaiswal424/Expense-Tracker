package com.example.expensetracker.service;

import com.example.expensetracker.dto.ExpenseRequest;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository,
                          UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    private User getCurrentUser(Authentication auth) {
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Expense createExpense(ExpenseRequest request, Authentication auth) {
        User user = getCurrentUser(auth);

        Expense expense = new Expense();
        expense.setUser(user);
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setDate(request.getDate());
        expense.setExpenseCategory(request.getExpenseCategory());

        return expenseRepository.save(expense);
    }

    public List<Expense> listExpenses(String period, LocalDate start, LocalDate end,
                                      Authentication auth) {
        User user = getCurrentUser(auth);
        LocalDate now = LocalDate.now();

        if (period != null) {
            switch (period) {
                case "week" -> { start = now.minusWeeks(1); end = now; }
                case "month" -> { start = now.minusMonths(1); end = now; }
                case "three_months" -> { start = now.minusMonths(3); end = now; }
            }
        }

        if (start != null && end != null)
            return expenseRepository.findByUserAndDateBetween(user, start, end);

        return expenseRepository.findByUser(user);
    }

    public Expense updateExpense(Long id, ExpenseRequest request, Authentication auth) {
        User user = getCurrentUser(auth);

        Expense exp = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!exp.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Forbidden");

        exp.setAmount(request.getAmount());
        exp.setDescription(request.getDescription());
        exp.setDate(request.getDate());
        exp.setExpenseCategory(request.getExpenseCategory());

        return expenseRepository.save(exp);
    }

    public void deleteExpense(Long id, Authentication auth) {
        User user = getCurrentUser(auth);

        Expense exp = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!exp.getUser().getId().equals(user.getId()))
            throw new RuntimeException("Forbidden");

        expenseRepository.delete(exp);
    }
}
