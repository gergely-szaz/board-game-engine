package com.github.gergelyszaz.bgs.view;

import com.github.gergelyszaz.bgs.state.GameState;

public interface Controller {

	boolean setSelected(String playerID, int ID);

	void addView(View v);
	
	void removeView(View v);

	GameState getCurrentState(String playerID);

}
