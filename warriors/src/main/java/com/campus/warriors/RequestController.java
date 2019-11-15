package com.campus.warriors;

import com.campus.warriors.contracts.*;
import com.campus.warriors.engine.Game;
import com.campus.warriors.engine.Warriors;
import com.google.common.collect.Iterables;
import io.vavr.control.Option;
import org.springframework.web.bind.annotation.*;


@RestController
public class RequestController {

    private final static WarriorsAPI app = new Warriors();

    @GetMapping("/heroes")
    public Iterable<Hero> heroes() {
        return app.availableHeroes();
    }

    @GetMapping("/maps")
    public Iterable<Map> maps() {
        return app.availableMaps();
    }

    @PostMapping("/games")
    public GameState create(@RequestBody java.util.Map<String, Object> payload) {
        String playerName = (String) payload.get("playerName");
        int heroIndex = (int) payload.get("hero");
        int mapIndex = (int) payload.get("map");

        Iterable<Hero> heroes = app.availableHeroes();
        Iterable<Map> maps = app.availableMaps();
        Hero hero = Iterables.get(heroes, heroIndex);
        Map map = Iterables.get(maps, mapIndex);

        return app.createGame(playerName, hero, map);
    }

    @GetMapping("/games")
    public Iterable<Game> games() {
        return app.availableGames();
    }

    @PostMapping("/games/{id}/turns")
    public Option<GameState> turns(@PathVariable String id) {
        GameId gameId = GameId.parse(id);
        return app.nextTurn(gameId);
    }

}
