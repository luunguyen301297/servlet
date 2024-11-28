package org.example.demo.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "indexer")
    public class Indexer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "index_id")
    Integer indexId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "valueMin", nullable = false)
    Float valueMin;

    @Column(name = "valueMax", nullable = false)
    Float valueMax;

}
