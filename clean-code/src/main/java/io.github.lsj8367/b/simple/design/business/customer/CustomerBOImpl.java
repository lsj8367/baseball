package io.github.lsj8367.b.simple.design.business.customer;

import io.github.lsj8367.b.simple.design.business.exception.DifferentCurrenciesException;
import io.github.lsj8367.b.simple.design.model.customer.Amount;
import io.github.lsj8367.b.simple.design.model.customer.AmountImpl;
import io.github.lsj8367.b.simple.design.model.customer.Currency;
import io.github.lsj8367.b.simple.design.model.customer.Product;
import java.math.BigDecimal;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {

    @Override
    public Amount getCustomerProductsSum(final List<Product> products) {

        if (products.isEmpty()) {
            return new AmountImpl(BigDecimal.ZERO, Currency.EURO);
        }

        if (!allMatchFirstProduct(products)) {
            throw new DifferentCurrenciesException();
        }

        return sumAmountProducts(products);
    }

    private boolean allMatchFirstProduct(final List<Product> products) {
        final Currency firstProductCurrency = products.get(0).getAmount()
            .getCurrency();

        return products.stream()
            .map(product -> product.getAmount().getCurrency())
            .allMatch(currency -> currency.equals(firstProductCurrency));
    }

    private AmountImpl sumAmountProducts(final List<Product> products) {
        final Currency firstProductCurrency = products.get(0).getAmount()
            .getCurrency();

        final BigDecimal sum = products.stream()
            .map(product -> product.getAmount().getValue())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create new product
        return new AmountImpl(sum, firstProductCurrency);
    }

}