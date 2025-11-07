package com.login.exemplo.Controller;

import com.login.exemplo.Entity.Produto;
import com.login.exemplo.Entity.Usuario;
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
    ProdutoRepository produtoRepository;

    //Busca por id
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarId(@PathVariable int id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()){
            return ResponseEntity.ok(new ProdutoResponseDTO(produto.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    //Busca todos
    @GetMapping("/listar/all")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> listaDeProdutos = produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(listaDeProdutos);
    }

    //criar produto
    @PostMapping("/criar")
    public ResponseEntity<?> criarProduto(@Valid @RequestBody ProdutoRequestDTO produto){
        Produto novo = new Produto(
                produto.getName(),produto.getPrice(),produto.getQuantity());
        produtoRepository.save(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Criado com sucesso");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable int id){
        boolean exists = produtoRepository.existsById(id);
        if(exists){
            produtoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto Não Encontrado");
        }

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> alterarProduto(@PathVariable int id, @Valid @RequestBody ProdutoRequestDTO produto) {
        Optional<Produto> novo = produtoRepository.findById(id);
        if (novo.isPresent()) {
            Produto produto1 = novo.get();
            produto1.setName(produto.getName());
            produto1.setPrice(produto.getPrice());
            produto1.setQuantity(produto.getQuantity());
            produtoRepository.save(produto1);
            return ResponseEntity.ok(new ProdutoResponseDTO(produto1));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoResponseDTO> listaDeProdutos = produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
        return ResponseEntity.ok(listaDeProdutos);
    }
    
}
