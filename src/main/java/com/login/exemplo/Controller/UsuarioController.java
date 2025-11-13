package com.login.exemplo.Controller;

import com.login.exemplo.Service.UsuarioService;
import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUser(usuarioRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@Valid @RequestBody UsuarioRequestDTO user) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.usuarioPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.deletarUsuario(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        try {
            return ResponseEntity.ok(usuarioService.listarUsuarios1());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @Valid @RequestBody UsuarioRequestDTO usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.atualizarNomeUsuario(id, usuario));
    }
}