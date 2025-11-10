package br.com.banking.digital_platform.controller;

import br.com.banking.digital_platform.entity.Role;
import br.com.banking.digital_platform.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/manage/create")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping("/manage/update/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/manage/delete/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

    @GetMapping("/manage/all")
    public List<Role> listAllRoles() {
        return roleService.getAllRoles();
    }
}
