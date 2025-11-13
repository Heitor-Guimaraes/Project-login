package com.login.exemplo.Service;

import com.login.exemplo.Entity.Produto;
import com.login.exemplo.dto.ProdutoRequestDTO;
import com.login.exemplo.dto.ProdutoResponseDTO;
import com.login.exemplo.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    
    private final ProdutoRepository produtoRepository;
    
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    public ProdutoResponseDTO buscarId(int id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            return new ProdutoResponseDTO(produto.get());
        }
        throw new RuntimeException("Produto não encontrado");
    }


    public List<ProdutoResponseDTO> buscarTodos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }


    public void criarProduto(ProdutoRequestDTO produto) {
        Produto novo = new Produto(
                produto.getName(), produto.getPrice(), produto.getQuantity());
        produtoRepository.save(novo);
    }


    public void deletar(int id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto Não Encontrado");
        }
        produtoRepository.deleteById(id);
    }


    public ProdutoResponseDTO alterarProduto(int id, ProdutoRequestDTO produto) {
        Optional<Produto> novo = produtoRepository.findById(id);
        if (novo.isPresent()) {
            Produto produto1 = novo.get();
            produto1.setName(produto.getName());
            produto1.setPrice(produto.getPrice());
            produto1.setQuantity(produto.getQuantity());
            produtoRepository.save(produto1);
            return new ProdutoResponseDTO(produto1);
        }
        throw new RuntimeException("Produto não encontrado");
    }


    public List<ProdutoResponseDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(ProdutoResponseDTO::new)
                .toList();
    }
}
