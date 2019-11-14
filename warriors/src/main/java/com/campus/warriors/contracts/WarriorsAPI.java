package com.campus.warriors.contracts;

import com.campus.warriors.engine.Game;
import io.vavr.control.Option;

public interface WarriorsAPI {
	
	Iterable<Hero> availableHeroes();
	
	Iterable<Map> availableMaps();

	Iterable<Game> availableGames();
	
	GameState createGame(String playerName, Hero hero, Map map);
	
	Option<GameState> nextTurn(GameId gameId);

}
