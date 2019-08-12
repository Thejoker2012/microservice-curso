package br.com.iago.microservice.microservice2.repository;

import br.com.iago.microservice.microservice2.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUserId(Long userId);

    List<Transaction> findAllByCursoId(Long cursoId);
}
