package com.github.EvgeniyaZz.bankingservice.to;

import com.github.EvgeniyaZz.bankingservice.HasId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Перевод денег")
public class TransferTo extends BaseTo implements HasId {

    int amount;
    int accountToId;

    public TransferTo(Integer id, int amount, int accountToId) {
        super(id);
        this.amount = amount;
        this.accountToId = accountToId;
    }
}