package org.example.demo.service;

import org.example.demo.entity.Player;
import org.example.demo.repository.PlayerRepository;
import org.example.demo.repository.impl.PlayerRepositoryImpl;

import java.util.List;

public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService() {
        this.playerRepository = new PlayerRepositoryImpl();
    }

    public List<Player> getAll() throws Exception {
        return playerRepository.findAll();
    }

    public Player getPlayerById(int id) throws Exception {
        Player player = playerRepository.findById(id);
        if(player == null){
            throw new Exception("Indexer not found!");
        }
        return player;
    }

    public void savePlayer(Player player){
        if(player.getName().isEmpty() || player.getName().length()<2){
            throw new IllegalArgumentException("Please input name");
        }
        playerRepository.save(player);
    }

    public void deletePlayer(int id){
        playerRepository.delete(id);
    }
}
