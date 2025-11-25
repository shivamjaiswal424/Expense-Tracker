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

    public Expense createExpense(ExpenseRequest expenseRequest, Authentication auth) {
        User user = getCurrentUser(auth);

        Expense expense = new Expense();
        expense.setUser(user);                                 // setUser exists now
        expense.setAmount(expenseRequest.getAmount());         // getAmount()
        expense.setDescription(expenseRequest.getDescription());// getDescription()
        expense.setDate(expenseRequest.getDate());             // getDate()
        expense.setCategory(expenseRequest.getCategory());     // getCategory(), NOT getExpenseCategory()

        return expenseRepository.save(expense);
    }

    public List<Expense> listExpenses(String period, LocalDate start, LocalDate end,
                                      Authentication auth) {
        User user = getCurrentUser(auth);
        LocalDate now = LocalDate.now();

        if (period != null) {
            switch (period) {
                case "week" -> {
                    start = now.minusWeeks(1);
                    end = now;
                }
                case "month" -> {
                    start = now.minusMonths(1);
                    end = now;
                }
                case "three_months" -> {
                    start = now.minusMonths(3);
                    end = now;
                }
            }
        }

        if (start != null && end != null) {
            return expenseRepository.findByUserAndDateBetween(user, start, end);
        }
        return expenseRepository.findByUser(user);
    }

    public Expense updateExpense(Long id, ExpenseRequest expenseRequest, Authentication auth) {
        User user = getCurrentUser(auth);

        Expense exp = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!exp.getUser().getId().equals(user.getId())) { // getUser() exists now
            throw new RuntimeException("Forbidden");
        }

        exp.setAmount(expenseRequest.getAmount());
        exp.setCategory(expenseRequest.getCategory());
        exp.setDate(expenseRequest.getDate());
        exp.setDescription(expenseRequest.getDescription());

        return expenseRepository.save(exp);
    }

    public void deleteExpense(Long id, Authentication auth) {
        User user = getCurrentUser(auth);

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (!expense.getUser().getId().equals(user.getId())) { // getUser() exists now
            throw new RuntimeException("Forbidden");
        }

        expenseRepository.delete(expense);
    }
}
