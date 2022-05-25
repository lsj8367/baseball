package io.github.lsj8367.b.simple.design.model.customer;

import java.math.BigDecimal;

public interface Amount {
	BigDecimal getValue();

	Currency getCurrency();
}
