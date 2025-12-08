package com.gic.rosm.Entity;


import com.gic.rosm.Entity.Enums.SlipStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Slips {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Double paidAmount;
    private Double changeAmount;
    private Double totalAmount;
    private LocalDateTime paymentTime;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private SlipStatus status;

    @OneToMany(mappedBy = "slip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id")
    private Staff cashier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private Tables table;
}
