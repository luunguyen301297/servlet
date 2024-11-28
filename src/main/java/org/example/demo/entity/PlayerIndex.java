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
@Table(name = "player_index")
public class PlayerIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "player_id", nullable = false)
    Player player;

    @ManyToOne
    @JoinColumn(name = "index_id", referencedColumnName = "index_id", nullable = false)
    Indexer indexer;

    @Column(name = "value", nullable = false)
    Float value;

}
