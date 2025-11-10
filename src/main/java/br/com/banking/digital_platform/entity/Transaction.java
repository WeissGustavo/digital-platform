/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.banking.digital_platform.entity;

import br.com.banking.digital_platform.enumeration.TransactionStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author gusta
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "transaction")
public class Transaction extends BaseEntity {

    private static final long serialVersionUID = -170441260226373055L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sending_account_id")
    private Account sendingAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiving_account_id")
    private Account receivingAccount;

    @Column(name = "send_time")
    private Instant sendTime;

    @Column(name = "receive_time")
    private Instant receiveTime;

    @Column(name = "value", precision = 19, scale = 4, nullable = false)
    private BigDecimal value;

    @Column(name = "external")
    private Boolean external;

    @Column(name = "external_account_identifier")
    private String externalAccountIdentifier;

    @Column(name = "currency")
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents;
}
