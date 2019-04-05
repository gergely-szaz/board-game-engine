package com.github.gergelyszaz.bgs.state;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.gergelyszaz.bgl.bgl.Field;
import com.github.gergelyszaz.bgs.game.InternalManager;
import com.github.gergelyszaz.bgs.game.model.Card;
import com.github.gergelyszaz.bgs.game.model.Deck;
import com.github.gergelyszaz.bgs.game.model.Player;
import com.github.gergelyszaz.bgs.game.model.Token;
import com.google.common.collect.Lists;

/**
 * Created by mad on 2016. 10. 18..
 */
public class GameStateFactory {

	public static GameState createGameState(String gameName,
														 IDManager idManager,
														 int version,
														 Player player,
														 List<Player> players,
														 List<Field> fields,
														 List<Token> tokens,
														 List<Deck> decks,
														 List<Integer> selectables,
														 List<Integer> winners,
														 List<Integer> losers)
		 throws IllegalAccessException {

		List<PlayerState> plist = new ArrayList<>();
		for (Player p : players) {
			PlayerState ps = new PlayerState();
			ps.id = idManager.get(p);
			ps.name = p.getName();
			plist.add(ps);
		}
		List<FieldState> flist = new ArrayList<>();
		for (Field f : fields) {
			FieldState fs = new FieldState();
			fs.id = idManager.get(f);
			fs.name = f.getName();
			fs.neighbours.addAll(
				 f.getNeighbours().stream().map(n -> idManager.get(n))
					  .collect(Collectors.toList()));
			flist.add(fs);
		}
		List<TokenState> tlist = new ArrayList<>();
		for (Token t : tokens) {
			TokenState ts = new TokenState();
			ts.id = idManager.get(t);
			ts.field = idManager.get(t.getField());
			ts.owner = idManager.get(t.getOwner());
			ts.type = t.type;
			tlist.add(ts);
		}

		List<DeckState> deckstates = new ArrayList<>();
		for (Deck deck : decks) {
			DeckState deckState = new DeckState();
			switch (deck.visibility) {
				case "PUBLIC":
					deckState.visible = 2;
					break;
				case "PROTECTED":
					deckState.visible = 1;
					break;
				case "PRIVATE":
					deckState.visible = 0;
					break;
			}
			deckstates.add(deckState);
			deckState.id = idManager.get(deck);
			if (deck.owner != null) deckState.owner = idManager.get(deck.owner);
			for (Card c : deck.cards) {
				CardState cs = new CardState();
				cs.id=idManager.get(c);
				cs.type=c.getType();
				deckState.cards.add(cs);
			}
		}

		return new GameState(gameName, version,
			 idManager.get(player), plist, flist, tlist,
			 new ArrayList<>(selectables), winners, losers, deckstates, -1);
	}

	public static GameState createGameState(String name, IDManager idManager, int stateVersion, InternalManager internalManager) throws IllegalAccessException {
		return createGameState(
				name,
				idManager,
				stateVersion,
				internalManager.getCurrentPlayer(),
				internalManager.getPlayers(),
				internalManager.getFields(),
				internalManager.getTokens(),
				internalManager.getDecks(),
				internalManager.getSelectables(idManager),
				Lists.transform(internalManager.getWinners(), idManager::get),
				Lists.transform(internalManager.getLosers(), idManager::get));
	}
}
