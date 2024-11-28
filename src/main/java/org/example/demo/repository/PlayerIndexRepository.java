package org.example.demo.repository;

import org.example.demo.entity.Indexer;
import org.example.demo.entity.Player;
import org.example.demo.entity.PlayerIndex;

import java.util.List;

public interface PlayerIndexRepository {

    List<PlayerIndex> findAll();

    PlayerIndex getPlayerIndexByPlayerAndIndexer(Player player, Indexer indexer);

    void save(PlayerIndex playerIndex);

    PlayerIndex findById(int id);

    void update(PlayerIndex playerIndex);

    void delete(int id);

}
