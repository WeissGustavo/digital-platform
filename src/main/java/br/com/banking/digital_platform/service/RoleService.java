/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.service;

import br.com.banking.digital_platform.dao.GenericDAO;
import br.com.banking.digital_platform.entity.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gusta
 */
@Service
public class RoleService {
    
    @Autowired
    private GenericDAO<Role> roleDao;

    public List<Role> getAllRoles() {
        return roleDao.findAll(Role.class);
    }

    public void deleteRole(Long id) {
        roleDao.delete(id, Role.class);
    }

    public Role updateRole(Role role) {
        return roleDao.update(role);
    }

    public Role createRole(Role role) {
        return roleDao.save(role);
    }
    
}
