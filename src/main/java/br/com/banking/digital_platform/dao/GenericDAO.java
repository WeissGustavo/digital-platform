package br.com.banking.digital_platform.dao;


import br.com.banking.digital_platform.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.springframework.stereotype.Repository;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gusta
 * @param <T>
 */
@Repository
public class GenericDAO<T extends BaseEntity> {
    
    @PersistenceContext
    protected EntityManager entityManager;

    public T findById(Long id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    public List<T> findAll(Class<T> clazz) {
        return entityManager.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
    }

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(Long id, Class<T> clazz) {
        T entity = findById(id,clazz);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}