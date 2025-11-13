package com.login.exemplo.Controller;

import com.login.exemplo.Entity.Produto;
import com.login.exemplo.Entity.Usuario;
import com.login.exemplo.Service.ProdutoService;
import com.login.exemplo.dto.ProdutoRequestDTO;
import com.login.exemplo.dto.ProdutoResponseDTO;
import com.login.exemplo.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //Busca por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable int id){
        return ResponseEntity.ok(produtoService.buscarId(id));
    }

    //Busca todos
    @GetMapping("/listar/all")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(produtoService.buscarTodos());
    }

    //criar produto
    @PostMapping("/criar")
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@Valid @RequestBody ProdutoRequestDTO produto) {
        produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        produtoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarProduto(@PathVariable int id, @Valid @RequestBody ProdutoRequestDTO produto) {
        return ResponseEntity.ok(produtoService.alterarProduto(id, produto));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        return ResponseEntity.ok(produtoService.listarProdutos());
    }
    
}
