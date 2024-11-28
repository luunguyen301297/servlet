package org.example.demo.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.dto.PlayerDto;
import org.example.demo.entity.Indexer;
import org.example.demo.entity.Player;
import org.example.demo.entity.PlayerIndex;
import org.example.demo.service.IndexerService;
import org.example.demo.service.PlayerIndexService;
import org.example.demo.service.PlayerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/player")
public class PlayerController extends HttpServlet {

    private PlayerService playerService;
    private IndexerService indexerService;
    private PlayerIndexService playerIndexService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.playerService = new PlayerService();
        this.indexerService = new IndexerService();
        this.playerIndexService = new PlayerIndexService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<Player> listPlayer = playerService.getAll();
            List<Indexer> listIndex = indexerService.getAll();
            List<PlayerDto> dto = new ArrayList<>();
            for (Player player : listPlayer) {
                PlayerDto playerDto = new PlayerDto();
                playerDto.setId(player.getPlayerId());
                playerDto.setName(player.getName());
                playerDto.setAge(player.getAge());
                playerDto.setIndexName(player.getIndexer().getName());
                PlayerIndex playerIndex = playerIndexService.getPlayerIndexByPlayerAndIndexer(player, player.getIndexer());
                playerDto.setValue(playerIndex.getValue());
                dto.add(playerDto);
            }

            req.setAttribute("viewListPlayerList", dto);
            req.setAttribute("indexer", listIndex);
            RequestDispatcher dispatcher = req.getRequestDispatcher("player.jsp");
            dispatcher.forward(req, resp);
        }catch (Exception e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            // Lấy dữ liệu từ form
            String playerName = req.getParameter("name");
            String playerAge = String.valueOf(req.getParameter("age"));
            int indexId = Integer.parseInt(req.getParameter("indexId"));
            float value = Float.parseFloat(req.getParameter("value"));

            // Tạo đối tượng Player
            Indexer indexer = indexerService.getIndexerById(indexId);

            Player player = new Player();
            player.setName(playerName);
            player.setFullName(playerName);
            player.setAge(playerAge);
            player.setIndexer(indexer);

            playerService.savePlayer(player);

            PlayerIndex playerIndex = new PlayerIndex();
            playerIndex.setPlayer(player);
            playerIndex.setIndexer(indexer);
            playerIndex.setValue(value);
            playerIndexService.savePlayerIndex(playerIndex);
            resp.sendRedirect(req.getContextPath() + "/player");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Error: " + e.getMessage());
        }
    }
}
