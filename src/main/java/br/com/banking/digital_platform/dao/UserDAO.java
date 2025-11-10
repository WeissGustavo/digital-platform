/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.dao;

import br.com.banking.digital_platform.entity.User;
import br.com.banking.digital_platform.entity.User_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
public class UserDAO extends GenericDAO<User>{
    
    public User findByUsername(String username){
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        
        root.fetch(User_.roles);
        
        List<Predicate> condicoes = new ArrayList<>();
        
        condicoes.add(builder.equal(root.get(User_.username), username));
        
        criteria.where(condicoes);
        List<User> result = entityManager.createQuery(criteria).getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
            
}
