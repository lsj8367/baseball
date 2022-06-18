package io.github.lsj8367.b.simple.design.business.customer;

import io.github.lsj8367.b.simple.design.business.exception.DifferentCurrenciesException;
import io.github.lsj8367.b.simple.design.model.customer.Amount;
import io.github.lsj8367.b.simple.design.model.customer.Product;
import java.util.List;

public interface CustomerBO {

    Amount getCustomerProductsSum(List<Product> products)
        throws DifferentCurrenciesException;

}