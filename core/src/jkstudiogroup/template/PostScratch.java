package jkstudiogroup.template;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jkstudiogroup.template.scenes.GameScene;

public class PostScratch extends Game {

	public static AssetManager assetManager;
	public static float SW = 540;
	public static float SH = 960;

	@Override
	public void create () {
		assetManager = new AssetManager();
		LoadAssets();
		assetManager.finishLoading();
		setScreen(new GameScene(this));
	}

	private void LoadAssets() {
		assetManager.load("badlogic.jpg", Texture.class);
	}
}
