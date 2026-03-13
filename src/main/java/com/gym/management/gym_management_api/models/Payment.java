package com.gym.management.gym_management_api.models;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
public class Payment {
    
    @Id
    private String id;
    
    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    @JsonIgnoreProperties({"payments"})
    private Membership membership;
    
    @Column(nullable = false)
    private BigDecimal amount;
    
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    
    @Column(name = "transaction_reference")
    private String transactionReference;
    
    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
    
    public enum PaymentMethod {
        CASH, MOBILE_MONEY, BANK_CARD
    }
}
