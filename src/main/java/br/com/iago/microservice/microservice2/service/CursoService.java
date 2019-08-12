package br.com.iago.microservice.microservice2.service;

import br.com.iago.microservice.microservice2.model.Curso;
import br.com.iago.microservice.microservice2.model.Transaction;

import java.util.List;

public interface CursoService {
    List<Curso> allCursos();

    Curso findCursoById(Long cursoId);

    List<Transaction> findTransactionOfUser(Long userId);

    List<Transaction> findTransactionOfCurso(Long cursoId);

    Transaction saveTransaction(Transaction transaction);
}
