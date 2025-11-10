/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.service;

import br.com.banking.digital_platform.dao.TransactionDAO;
import br.com.banking.digital_platform.dto.TransactionEventDTO;
import br.com.banking.digital_platform.entity.Transaction;
import br.com.banking.digital_platform.kafka.TransactionProducer;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gusta
 */
@Service
public class TransactionService {
    
    @Autowired
    private TransactionDAO transactionDao;
    @Autowired
    private TransactionProducer producer;
    
    public Transaction createTransaction(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionDao.findByUserId(userId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDao.findAll(Transaction.class);
    }

    public void deleteTransaction(Long id) {
        transactionDao.delete(id, Transaction.class);
    }

    public Transaction send(Transaction transaction) {
        transaction.setSendTime(Instant.now());
        transaction = createTransaction(transaction);
        
        TransactionEventDTO dto = new TransactionEventDTO(transaction);   
        
        producer.sendTransaction(dto);
        producer.sendNotification(dto);
        
        return transaction;
    }

    public void processTransaction(TransactionEventDTO event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void notifyReceivingUser(TransactionEventDTO event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
