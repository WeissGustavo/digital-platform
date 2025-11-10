package br.com.banking.digital_platform.controller;

import br.com.banking.digital_platform.entity.Transaction;
import br.com.banking.digital_platform.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/send")
    public Transaction sendTransaction(@RequestBody Transaction transaction) {
        return transactionService.send(transaction);
    }

    @GetMapping("/user/{userId}")
    public List<Transaction> getUserTransactions(@PathVariable Long userId) {
        return transactionService.getTransactionsByUser(userId);
    }

    @GetMapping("/manage/all")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @DeleteMapping("/manage/delete/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
