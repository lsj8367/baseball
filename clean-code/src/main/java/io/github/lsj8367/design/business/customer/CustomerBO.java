package io.github.lsj8367.design.business.customer;

import io.github.lsj8367.design.model.customer.Amount;
import io.github.lsj8367.design.model.customer.Product;
import java.util.List;

public interface CustomerBO {

    Amount getCustomerProductsSum(final List<Product> products);

}