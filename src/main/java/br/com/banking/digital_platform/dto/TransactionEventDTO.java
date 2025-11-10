package br.com.banking.digital_platform.dto;

import br.com.banking.digital_platform.entity.Transaction;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEventDTO implements Serializable{

    private static final long serialVersionUID = 429661710199709650L;
    private Long transactionId;
    private Long sendingAccountId;
    private Long receivingAccountId;
    private BigDecimal value;
    private Instant sendTime;
    private String currency;

    public TransactionEventDTO(Transaction transaction) {
        this.transactionId = transaction.getId();
        this.sendingAccountId = transaction.getSendingAccount().getId();
        this.receivingAccountId = transaction.getReceivingAccount().getId();
        this.value = transaction.getValue();
        this.currency = transaction.getCurrency();
    }
}
