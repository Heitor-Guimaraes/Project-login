package com.login.exemplo.repositories;

import com.login.exemplo.Entity.Produto;
import com.login.exemplo.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{


}
