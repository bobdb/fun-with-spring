package net.bobdb.funwithspring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Person(String name) {
        this.name = name;
    }

}



