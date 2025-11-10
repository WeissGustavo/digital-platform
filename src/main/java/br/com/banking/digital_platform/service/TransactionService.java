/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.service;

import br.com.banking.digital_platform.dao.TransactionDAO;
import br.com.banking.digital_platform.dto.TransactionEventDTO;
import br.com.banking.digital_platform.entity.Account;
import br.com.banking.digital_platform.entity.Transaction;
import br.com.banking.digital_platform.enumeration.TransactionStatus;
import br.com.banking.digital_platform.kafka.TransactionProducer;
import br.com.banking.digital_platform.validator.AccountValidator;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private AccountValidator accountValidator;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction createTransaction(Transaction transaction) {
        return transactionDao.save(transaction);
    }

    public List<Transaction> getTransactionsByUser(Long userId) {
        return transactionDao.findByUserId(userId);
    }

    public List<Transaction> getAllTransactions() {
        return transactionDao.findAll(Transaction.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteTransaction(Long id) {
        transactionDao.delete(id, Transaction.class);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Transaction send(Transaction transaction) {
        transaction.setSendTime(Instant.now());
        transaction = createTransaction(transaction);

        TransactionEventDTO dto = new TransactionEventDTO(transaction);

        producer.sendTransaction(dto);

        return transaction;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void processTransaction(TransactionEventDTO event) {
        Transaction transaction = transactionDao.findFetch(event.getTransactionId());
        try {
            BigDecimal transactionValue = transaction.getValue();
            //TODO convert currency values
            
            Account sending = transaction.getSendingAccount();
            accountValidator.validateBalance(sending, transactionValue);
            sending.setBalance(sending.getBalance().subtract(transactionValue));
            
            Account receiving = transaction.getReceivingAccount();
            receiving.setBalance(receiving.getBalance().add(transactionValue));
            
            transaction.setStatus(TransactionStatus.RECEIVED);
            producer.sendNotification(event);
        } catch (Exception ex) {
            transaction.setStatus(TransactionStatus.FAILED);
        }
        transactionDao.update(transaction);
    }

    public void notifyReceivingUser(TransactionEventDTO event) {
        System.out.println("Send email notification");
    }

}
