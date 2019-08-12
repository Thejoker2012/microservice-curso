package br.com.iago.microservice.microservice2.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "titulo")
    private String titulo;
    @Column(name= "autor")
    private String autor;
    @Column(name= "categoria")
    private String categoria;
    @Column(name= "publish_date")
    private LocalDate publishDate;

}
