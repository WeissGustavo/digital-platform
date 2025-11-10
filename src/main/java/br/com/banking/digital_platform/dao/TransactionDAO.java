/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.dao;

import br.com.banking.digital_platform.entity.Transaction;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gusta
 */
@Repository
public class TransactionDAO extends GenericDAO<Transaction> {

    public List<Transaction> findByUserId(Long userId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
