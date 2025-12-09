package com.gic.rosm.Repository;

import com.gic.rosm.Entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> {

    @Query("Select s from Staff s where s.loginName = :loginName")
    Optional<Staff> findByLoginName(@Param("loginName") String loginName);
}
