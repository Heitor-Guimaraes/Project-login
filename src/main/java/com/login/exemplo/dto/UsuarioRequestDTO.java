package com.login.exemplo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioRequestDTO {


    @NotBlank(message = "valor n pode ser nulo")
    private String name;

    @NotBlank (message = "valor nao pode ser vazio")
    private String email;

    @Size(min = 6,  max = 20 , message = "senha deve ter no minimo 6 caracteres")
    private String password;

    public UsuarioRequestDTO() {

    }

    public UsuarioRequestDTO( String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }


    public @NotNull(message = "valor n pode ser nulo") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "valor n pode ser nulo") String name) {
        this.name = name;
    }

    public @NotBlank(message = "valor nao pode ser vazio") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "valor nao pode ser vazio") String email) {
        this.email = email;
    }

    public @Size(min = 6, max = 20, message = "senha deve ter no minimo 6 caracteres") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20, message = "senha deve ter no minimo 6 caracteres") String password) {
        this.password = password;
    }

}
