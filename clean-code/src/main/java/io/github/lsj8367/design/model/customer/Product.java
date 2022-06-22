package io.github.lsj8367.design.model.customer;

/**
 * Product Model API.
 */
public interface Product {

    long getId();

    String getName();

    ProductType getType();

    Amount getAmount();

}
