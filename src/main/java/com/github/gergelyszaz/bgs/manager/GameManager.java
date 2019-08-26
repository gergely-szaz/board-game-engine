package com.github.gergelyszaz.bgs.manager;

import com.github.gergelyszaz.bgs.game.Game;
import com.github.gergelyszaz.bgs.view.View;

import com.github.gergelyszaz.bgl.manager.ModelManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.github.gergelyszaz.bgl.bgl.*;

public class GameManager implements Runnable {
	private Object monitor = new Object();
	public ModelManager modelManager;
	public Map<String, Game> availableGames = new HashMap<>();
	public Map<View, Game> playerConnections = new HashMap<>();
	public List<Game> runningGames = new ArrayList<>();
	private GameFactory gameFactory;

	public GameManager(GameFactory gameFactory, ModelManager modelManager) {
		this.gameFactory = gameFactory;
		this.modelManager = modelManager;
	}

	public void JoinGame(View view, String gameName) throws Exception {
		synchronized (monitor) {

			if (playerConnections.containsKey(view))
				return;

			if (availableGames.get(gameName) == null) {
				Model model = modelManager.Get(gameName);
				if (model == null)
					throw new RuntimeException("Game " + gameName + " not found!");
				availableGames.put(gameName, gameFactory.CreateGame(model));
			}

			Game game = availableGames.get(gameName);

			game.Join(view);
			playerConnections.put(view, game);

			if (game.IsFull()) {
				availableGames.remove(gameName);
				game.Start();
				runningGames.add(game);
			}

			Wake();
		}
	}

	@Override
	public void run() {
		while (true) {
			Collection<Game> rg = new ArrayList<>();
			rg = runningGames.stream().filter(Game::IsNotWaitingForInput).collect(Collectors.toList());

			if (rg.isEmpty()) {
				Wait();
			} else {
				for (Game g : rg) {
					try {
						g.Step();
						if (g.IsFinished())
							runningGames.remove(g);
					} catch (Exception e) {
						e.printStackTrace();
						runningGames.remove(g);
					}
				}
			}
		}
	}

	private void Wait() {
		synchronized (monitor) {
			try {
				monitor.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void Wake() {
		synchronized (monitor) {
			monitor.notify();
		}
	}
}
