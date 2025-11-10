/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.dao;

import br.com.banking.digital_platform.entity.Account;
import br.com.banking.digital_platform.entity.Account_;
import br.com.banking.digital_platform.entity.Transaction;
import br.com.banking.digital_platform.entity.Transaction_;
import br.com.banking.digital_platform.entity.User;
import br.com.banking.digital_platform.entity.User_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gusta
 */
@Repository
public class TransactionDAO extends GenericDAO<Transaction> {

    public List<Transaction> findByUserId(Long userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);
        Root<Transaction> root = criteria.from(Transaction.class);
        criteria.distinct(true);

        Join<Transaction, Account> sendJoin = root.join(Transaction_.sendingAccount);
        Join<Account, User> userSendJoin = sendJoin.join(Account_.users);
        Join<Transaction, Account> receiveJoin = root.join(Transaction_.sendingAccount);
        Join<Account, User> userReceiveJoin = receiveJoin.join(Account_.users);

        List<Predicate> condicoes = new ArrayList<>();

        condicoes.add(builder.or(builder.equal(userSendJoin.get(User_.id), userId), builder.equal(userReceiveJoin.get(User_.id), userId)));

        criteria.where(condicoes);
        return entityManager.createQuery(criteria).getResultList();

    }

    public Transaction findFetch(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteria = builder.createQuery(Transaction.class);
        Root<Transaction> root = criteria.from(Transaction.class);

        Fetch<Transaction,Account> sendFetch = root.fetch(Transaction_.sendingAccount);
        Fetch<Transaction,Account> receiveFetch = root.fetch(Transaction_.receivingAccount);
        sendFetch.fetch(Account_.users);
        receiveFetch.fetch(Account_.users);
        root.fetch(Transaction_.documents, JoinType.LEFT);        

        List<Predicate> condicoes = new ArrayList<>();

        condicoes.add(builder.equal(root.get(Transaction_.id), id));

        criteria.where(condicoes);
        List<Transaction> result = entityManager.createQuery(criteria).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

}
