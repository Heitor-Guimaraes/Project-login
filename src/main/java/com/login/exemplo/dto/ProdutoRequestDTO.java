package com.login.exemplo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProdutoRequestDTO {

    @NotBlank(message = "nescessario  ter nome")
    private String name;

    @NotBlank (message = "valor nao pode ser vazio")
    private double price;

    @Size(min = 1,  max = 5 , message = "quantidade deve ter no minimo 1 produto")
    private int quantity;

    public ProdutoRequestDTO(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public ProdutoRequestDTO() {

    }

    public @NotBlank(message = "valor n pode ser nulo") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "valor n pode ser nulo") String name) {
        this.name = name;
    }

    public @NotBlank(message = "valor nao pode ser vazio") double getPrice() {
        return price;
    }

    public void setPrice(@NotBlank(message = "valor nao pode ser vazio") double price) {
        this.price = price;
    }

    public @Size(min = 1, max = 20, message = "quantidade deve ter no minimo 2 produto") int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Size(min = 1, max = 20, message = "quantidade deve ter no minimo 2 produto") int quantity) {
        this.quantity = quantity;
    }
}
