package br.com.iago.microservice.microservice2.service;

import br.com.iago.microservice.microservice2.model.Curso;
import br.com.iago.microservice.microservice2.model.Transaction;
import br.com.iago.microservice.microservice2.repository.CursoRespository;
import br.com.iago.microservice.microservice2.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRespository cursoRespository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public List<Curso> allCursos(){
        return cursoRespository.findAll();
    }

    @Override
    public Curso findCursoById(Long cursoId){
        return cursoRespository.findById(cursoId).orElse(null);

    }

    @Override
    public List<Transaction> findTransactionOfUser(Long userId){
        return transactionRepository.findAllByUserId(userId);
    }

    @Override
    public List<Transaction> findTransactionOfCurso(Long cursoId){
        return transactionRepository.findAllByCursoId(cursoId);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

}
