package com.login.exemplo.Controller;

import com.login.exemplo.Entity.Usuario;
import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.dto.UsuarioResponseDTO;
import com.login.exemplo.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping(value = "usuario/cadastro")
    public ResponseEntity<String> saveUser(@Valid @RequestBody UsuarioRequestDTO user) {
        Usuario usuario = new Usuario(user.getName(), user.getEmail(), user.getPassword());
        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuario cadastrado com sucesso");
    }

    @PostMapping("login")
    public ResponseEntity<?> findUser(@Valid @RequestBody UsuarioRequestDTO user) {
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
        if (findUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok(new UsuarioResponseDTO(findUser));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
    }

//    @GetMapping(value = "usuario/listar")
//    public ResponseEntity<?> findAll(){
//        return ResponseEntity.ok(usuarioRepository.findAll());
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> usuarioPorId(@PathVariable int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(new UsuarioResponseDTO(usuario.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }


    @DeleteMapping(value = "deletar/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario deletado com sucesso");
    }


    @GetMapping(value = "usuario/listar")
    public List<UsuarioResponseDTO> listarUsuarios1(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> listaDeUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();
        return listaDeUsuarios;
    }

    @PutMapping(value = "usuario/atualizar/{id}")
    public ResponseEntity<?> atualizarNomeUsuario(@PathVariable int id, @Valid @RequestBody UsuarioRequestDTO usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setName(usuario.getName());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setPassword(usuario.getPassword());
            usuarioRepository.save(usuarioExistente);
            return ResponseEntity.ok(new UsuarioResponseDTO(usuarioExistente));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }
}