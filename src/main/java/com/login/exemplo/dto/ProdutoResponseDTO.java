package com.login.exemplo.dto;

import com.login.exemplo.Entity.Produto;

public class ProdutoResponseDTO {
    
    
    
    private String name;
    private double price;
    private int quantity;
    private double subtotal;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public ProdutoResponseDTO(Produto produto) {
        this.name = produto.getName();
        this.price = produto.getPrice();
        this.quantity = produto.getQuantity();
        this.subtotal = produto.getQuantity() * produto.getPrice();
    }
}
