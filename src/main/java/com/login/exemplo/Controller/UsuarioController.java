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
    public ResponseEntity<?> findUser(@RequestBody Usuario user){
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null){
            return ResponseEntity.ok("Usuário não encontrado");
        }
        else {
            if (findUser.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("Logado com sucesso");
            }
            else{
                return ResponseEntity.ok("Senha incorreta");
            }
        }
    }

//    @GetMapping(value = "usuario/listar")
//    public ResponseEntity<?> findAll(){
//        return ResponseEntity.ok(usuarioRepository.findAll());
//    }

    @GetMapping(value = "/{id}")
    public Optional<Usuario> usuarioPorId(@PathVariable int id){
        return  usuarioRepository.findById(id);
    }


    @DeleteMapping(value = "deletar/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id){
        usuarioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario deletado com sucesso");
    }


    @GetMapping(value = "usuario/listar")
    public List<UsuarioResponseDTO> listarUsuarios1(){
        List<Usuario> usuarios = usuarioRepository.findAll();
//        List<UsuarioResponseDTO> listaDeUsuarios = new ArrayList<>();
        List<UsuarioResponseDTO> listaDeUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();
//        for (Usuario usuario : usuarios) {
//            listaDeUsuarios.add(new UsuarioResponseDTO(usuario));
//        }
        return listaDeUsuarios;
    }

    @PutMapping(value = "usuario/atualizar/{id}")
    public ResponseEntity<?> atualizarNomeUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioExistente = usuarioOptional.get();
            usuarioExistente.setName(usuario.getName());
            usuarioRepository.save(usuarioExistente);
            return ResponseEntity.ok("Nome do usuário atualizado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
    }
}