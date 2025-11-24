package repository;

import model.Expense;
import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
    List<Expense> findByUser(User user);
}
