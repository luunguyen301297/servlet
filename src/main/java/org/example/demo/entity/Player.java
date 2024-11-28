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
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    Integer playerId;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "full_name", nullable = false)
    String fullName;

    @Column(name = "age", nullable = false)
    String age;

    @ManyToOne
    @JoinColumn(name = "index_id", referencedColumnName = "index_id", nullable = false)
    Indexer indexer;


}
