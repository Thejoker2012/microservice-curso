package br.com.iago.microservice.microservice2.controller;

import br.com.iago.microservice.microservice2.intercomm.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CursoController {

    @Autowired
    private UserClient userClient;

    //
}
