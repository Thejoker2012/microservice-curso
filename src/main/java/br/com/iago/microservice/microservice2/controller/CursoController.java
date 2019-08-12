package br.com.iago.microservice.microservice2.controller;

import br.com.iago.microservice.microservice2.intercomm.UserClient;
import br.com.iago.microservice.microservice2.model.Transaction;
import br.com.iago.microservice.microservice2.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CursoController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private Environment env;

    @Value("$spring.application.name")
    private String serviceId;

    @GetMapping("/service/port")
    public String getPort(){
        return "Serviço está funcionando na porta : " +env.getProperty("local.server.port");
    }

    @GetMapping("/services/instances")
    public ResponseEntity<?> getInstances(){
        return ResponseEntity.ok(discoveryClient.getInstances(serviceId));
    }

    @GetMapping("/service/user/{userId}")
    public ResponseEntity<?> findTransactionOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cursoService.findTransactionOfUser(userId));
    }

    @GetMapping("/service/all")
    public ResponseEntity<?> findAllCursos() {
        return ResponseEntity.ok(cursoService.allCursos());
    }

    @GetMapping("/service/enroll")
    public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
        transaction.setDateOfIssue(LocalDateTime.now());
        transaction.setCurso(cursoService.findCursoById(transaction.getCurso().getId()));
        return new ResponseEntity<>(cursoService.saveTransaction(transaction), HttpStatus.CREATED);
    }

    @GetMapping("/service/curse/{curseId}")
    public ResponseEntity<?> findStudantsOfCurso(@PathVariable Long cursoId) {
        List<Transaction> transactions = cursoService.findTransactionOfCurso(cursoId);
        if (CollectionUtils.isEmpty(transactions)) {
            return ResponseEntity.notFound().build();
        }
        List<Long> userIdList = transactions.parallelStream().map(transaction -> transaction.getUserId()).collect(Collectors.toList());
        List<String> students = userClient.getUserNames(userIdList);
        return ResponseEntity.ok(students);

    }
}
