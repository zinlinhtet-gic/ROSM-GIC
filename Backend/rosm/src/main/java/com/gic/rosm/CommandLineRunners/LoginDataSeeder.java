package com.gic.rosm.CommandLineRunners;

import com.gic.rosm.Entity.Enums.StaffRole;
import com.gic.rosm.Entity.Staff;
import com.gic.rosm.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginDataSeeder implements CommandLineRunner {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
            Staff admin = new Staff();
            admin.setLoginName("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRole(StaffRole.ADMIN);

            Staff waiter = new Staff();
            waiter.setLoginName("waiter1");
            waiter.setPassword(passwordEncoder.encode("1234"));
            waiter.setRole(StaffRole.WAITER);

            Staff cashier = new Staff();
            cashier.setLoginName("cashier1");
            cashier.setPassword(passwordEncoder.encode("1234"));
            cashier.setRole(StaffRole.CASHIER);

            staffRepository.save(admin);
            staffRepository.save(waiter);
            staffRepository.save(cashier);

            System.out.println("=== Test Staff Users Created ===");
            System.out.println("admin / 1234  (ADMIN)");
            System.out.println("staff1 / 1234 (STAFF)");
            System.out.println("manager / 1234 (MANAGER)");
    }
}

