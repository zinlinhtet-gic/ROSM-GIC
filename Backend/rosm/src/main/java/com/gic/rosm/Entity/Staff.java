package com.gic.rosm.Entity;

import com.gic.rosm.Entity.Enums.StaffRole;
import com.gic.rosm.Entity.Enums.StaffStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String loginName;
    private String password;
    @Enumerated(EnumType.STRING)
    private StaffRole role;
    private LocalDateTime hireDate;
    private LocalDateTime dob;
    private String phone;
    private String address;
    @Enumerated(EnumType.STRING)
    private StaffStatus status;

    @OneToMany(mappedBy = "waiter", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Orders> ordersAsWaiter;

    @OneToMany(mappedBy = "cashier", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Slips> slipsAsCashier;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public StaffRole getRole() {
        return role;
    }

    public String getLoginName() {
        return loginName;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public List<Orders> getOrdersAsWaiter() {
        return ordersAsWaiter;
    }

    public List<Slips> getSlipsAsCashier() {
        return slipsAsCashier;
    }

    public String getPhone() {
        return phone;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public String getAddress() {
        return address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(StaffRole role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public void setOrdersAsWaiter(List<Orders> ordersAsWaiter) {
        this.ordersAsWaiter = ordersAsWaiter;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSlipsAsCashier(List<Slips> slipsAsCashier) {
        this.slipsAsCashier = slipsAsCashier;
    }

    public void setStatus(StaffStatus status) {
        this.status = status;
    }
}
