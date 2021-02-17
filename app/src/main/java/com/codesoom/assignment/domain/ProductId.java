package com.codesoom.assignment.domain;

public class ProductId {
    private Long id;

    public ProductId(Long id) {
        setId(id);
    }

    public Long id() {
        return id;
    }

    private void setId(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("The id must be provided.");
        }
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        ProductId productId = (ProductId) o;
        return id.equals(productId.id);
    }
}
