package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getRoleList() {
        return roleRepository.findAll();
    }

    public Role getRoleById(int i){

        return roleRepository.findById(i).orElse(null);
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

}
