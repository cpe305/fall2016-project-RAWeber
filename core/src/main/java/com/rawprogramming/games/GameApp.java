package com.rawprogramming.games;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rawprogramming.games.Screens.TitleScreen;

public class GameApp extends Game {
	public SpriteBatch batch;
	public AssetManager manager = new AssetManager();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager.load("Tile.png", Texture.class);
		//manager.load("roughday.ttf", BitmapFont.class);
		
		this.setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		super.render();
		manager.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		manager.dispose();
	}
}
