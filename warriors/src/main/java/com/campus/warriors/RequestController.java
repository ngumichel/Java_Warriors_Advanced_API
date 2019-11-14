package com.campus.warriors;

import com.campus.warriors.contracts.*;
import com.campus.warriors.engine.Game;
import com.campus.warriors.engine.Warriors;
import com.campus.warriors.model.Warrior;
import io.vavr.control.Option;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestController {

    private final static WarriorsAPI app = new Warriors();

    @RequestMapping("/heroes")
    public Iterable<Hero> heroes() {
        return app.availableHeroes();
    }

    @RequestMapping("/maps")
    public Iterable<Map> maps() {
        return app.availableMaps();
    }

    @GetMapping("/games")
    public Iterable<Game> games() {
        return app.availableGames();
    }

    @PostMapping("/games")
    public GameState createGame(@RequestBody java.util.Map<String, Object> payload) {
        System.out.println(payload);
        System.out.println("GAMES");
        Iterable<Hero> heroList = app.availableHeroes();
        List<Hero> heroesList = new ArrayList<>();
        heroList.forEach(heroesList::add);
        Integer heroIndex = (Integer) payload.get("hero");
        Hero hero = heroesList.get(heroIndex);
        Iterable<Map> mapList = app.availableMaps();
        List<Map> mapsList = new ArrayList<>();
        mapList.forEach(mapsList::add);
        Integer mapIndex = (Integer) payload.get("map");
        Map map = mapsList.get(mapIndex);
        return app.createGame((String) payload.get("playerName"), hero, map);
    }

    @PostMapping("/games/{id}/turns")
    Option<GameState> gameStateId(@PathVariable String id) {
        System.out.println("TURN");
        GameId gameId = GameId.parse(id);
        return app.nextTurn(gameId);
    }


}
