package io.github.lsj8367.design.model.customer;

import java.math.BigDecimal;

public interface Amount {

    BigDecimal getValue();

    Currency getCurrency();

}
