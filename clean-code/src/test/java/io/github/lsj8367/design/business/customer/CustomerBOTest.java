package io.github.lsj8367.design.business.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.github.lsj8367.design.business.customer.CustomerBO;
import io.github.lsj8367.design.business.customer.CustomerBOImpl;
import io.github.lsj8367.design.business.exception.DifferentCurrenciesException;
import io.github.lsj8367.design.model.customer.Amount;
import io.github.lsj8367.design.model.customer.AmountImpl;
import io.github.lsj8367.design.model.customer.Currency;
import io.github.lsj8367.design.model.customer.Product;
import io.github.lsj8367.design.model.customer.ProductImpl;
import io.github.lsj8367.design.model.customer.ProductType;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomerBOTest {

    private final CustomerBO customerBO = new CustomerBOImpl();

    @Test
    @DisplayName("두 제품이 같은 통화인 경우 value 합산")
    void testCustomerProductSum_TwoProductsSameCurrencies()
        throws DifferentCurrenciesException {

        final List<Product> products = List.of(
            new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE,
                new AmountImpl(new BigDecimal("5.0"), Currency.EURO)),
            new ProductImpl(120, "Product 20", ProductType.BANK_GUARANTEE,
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO))
        );

        final Amount actual = customerBO.getCustomerProductsSum(products);
        assertAmount(actual, new AmountImpl(new BigDecimal("11.0"), Currency.EURO));
    }

    @Test
    @DisplayName("전부 통화가 같지 않다면 예외 발생")
    void notAllMatchCurrencyExceptionTest() {
        final List<Product> products = List.of(
            new ProductImpl(100, "Product 15",
                ProductType.BANK_GUARANTEE,
                new AmountImpl(new BigDecimal("5.0"), Currency.INDIAN_RUPEE)),
            new ProductImpl(120, "Product 20", ProductType.BANK_GUARANTEE,
                new AmountImpl(new BigDecimal("6.0"), Currency.EURO))
        );

        assertThatThrownBy(() -> customerBO.getCustomerProductsSum(products))
            .isInstanceOf(DifferentCurrenciesException.class);
    }

    @Test
    @DisplayName("리스트 길이가 0인 경우 0원의 유로화폐 반환")
    void ifEmptyThenReturnEuroZeroAmountTest() {
        final List<Product> products = Collections.emptyList();
        final Amount actual = customerBO.getCustomerProductsSum(products);

        assertAmount(actual, new AmountImpl(BigDecimal.ZERO, Currency.EURO));
    }

    private void assertAmount(final Amount actual, final Amount expected) {
        assertThat(actual.getCurrency()).isEqualTo(expected.getCurrency());
        assertThat(actual.getValue()).isEqualTo(expected.getValue());
    }

}
