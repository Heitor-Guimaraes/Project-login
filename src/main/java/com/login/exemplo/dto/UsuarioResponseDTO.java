package com.login.exemplo.dto;

import com.login.exemplo.Entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UsuarioResponseDTO {

   private int id;
   private String name;
   private String email;

    public UsuarioResponseDTO(Usuario user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return name;
    }

    public String getEmail() {
        return email;
    }




}
