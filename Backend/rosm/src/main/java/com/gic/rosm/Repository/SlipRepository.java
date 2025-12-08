package com.gic.rosm.Repository;

import com.gic.rosm.Entity.Slips;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlipRepository extends JpaRepository<Slips,Long> {
}
