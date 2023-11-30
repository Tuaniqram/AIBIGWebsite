package com.aibig.umk.services.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibig.umk.model.User.Adminstrative;
import com.aibig.umk.repository.User.AdminstrativeRepository;

@Service
public class AdminstrativeService {

    private final AdminstrativeRepository adminRepository;

    @Autowired
    public AdminstrativeService(AdminstrativeRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void saveadmin(Adminstrative admin) {
        adminRepository.save(admin);
    }

    public Adminstrative getadminById(int adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public void deleteadminById(int adminId) {
        adminRepository.deleteById(adminId);
    }

    public Adminstrative getadminByadminName(String adminName) {
        return adminRepository.findByAdminName(adminName);
    }

    public void updateadmin(Adminstrative admin) {
        adminRepository.save(admin);
    }

    public List<Adminstrative> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Adminstrative getAdminById(int adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    public void deleteAdminById(int adminId) {
        adminRepository.deleteById(adminId);
    }
}
