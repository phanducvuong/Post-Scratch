package jkstudiogroup.template.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import jkstudiogroup.template.PostScratch;

public class BaseScene implements Screen {

    private Game game;
    protected Stage stage;

    public BaseScene(Game game) {
        this.game = game;
        PostScratch.SH = PostScratch.SW * Gdx.graphics.getHeight() / Gdx.graphics.getWidth();
        stage = new Stage();
        stage.setViewport(new ExtendViewport(PostScratch.SW, PostScratch.SH));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1/255f, 78/255f, 121/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
