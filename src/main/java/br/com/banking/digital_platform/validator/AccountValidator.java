/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.validator;

import br.com.banking.digital_platform.entity.Account;
import jakarta.transaction.InvalidTransactionException;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

/**
 *
 * @author gusta
 */
@Service
public class AccountValidator {
    
    public void validateBalance(Account account, BigDecimal transactionValue) throws InvalidTransactionException{
        if(account.getBalance().subtract(transactionValue).compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidTransactionException("Account balance cannot go below zero!");
        }
    }
}
