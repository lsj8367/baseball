package io.github.lsj8367.b.simple.design.business.customer;

import java.util.List;

import io.github.lsj8367.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.Product;

public interface CustomerBO {

	Amount getCustomerProductsSum(List<Product> products)
			throws DifferentCurrenciesException;

}