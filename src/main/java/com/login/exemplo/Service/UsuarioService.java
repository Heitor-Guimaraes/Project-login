package com.login.exemplo.Service;

import com.login.exemplo.dto.UsuarioResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.login.exemplo.Entity.Usuario;
import com.login.exemplo.dto.UsuarioRequestDTO;
import com.login.exemplo.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository ;
    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public UsuarioResponseDTO saveUser(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = new Usuario(usuarioRequestDTO.getName(), usuarioRequestDTO.getEmail(), usuarioRequestDTO.getPassword());
        usuarioRepository.save(usuario);
        return new UsuarioResponseDTO(usuario);
    }


    public String findUser(UsuarioRequestDTO user) {
        Usuario findUser = usuarioRepository.findByEmail(user.getEmail());
        if (findUser == null) {
            return ("Usuário não encontrado");
        }
        if (!findUser.getPassword().equals(user.getPassword())) {
            return ("Senha incorreta");
        }
        return findUser.getName();
    }


    public String usuarioPorId(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ("Usuário não encontrado");
        }
        return usuario.get().getName();
    }


    public String deletarUsuario(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return "Usuário não encontrado";
        }
        else {
            usuarioRepository.deleteById(id);
            return "Usuário deletado com sucesso";
        }
        
    }


  
    public List<UsuarioResponseDTO> listarUsuarios1(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponseDTO> listaDeUsuarios = usuarios.stream().map(UsuarioResponseDTO::new).toList();
        return listaDeUsuarios;
    }


    public UsuarioResponseDTO atualizarNomeUsuario(int id, UsuarioRequestDTO usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return null;
        }
        Usuario usuarioExistente = usuarioOptional.get();
        usuarioExistente.setName(usuario.getName());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioRepository.save(usuarioExistente);
        return new UsuarioResponseDTO(usuarioExistente);
    }


}
