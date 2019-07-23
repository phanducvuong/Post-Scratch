package jkstudiogroup.template.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

import jkstudiogroup.template.Config.Card;
import jkstudiogroup.template.Objects.Board;
import jkstudiogroup.template.PostScratch;

public class GameScene extends BaseScene {

    private Texture badlogic;
    private SpriteBatch batch;
    private Board boardGame;

    public GameScene(Game game) {
        super(game);
        badlogic = PostScratch.assetManager.get("badlogic.jpg", Texture.class);

        List<Card> cards = new ArrayList<Card>();

        cards.add(Card.Jack);
        cards.add(Card.Ten);
        cards.add(Card.Queen);

        boardGame = new Board(cards);
//        boardGame.show();

        Gdx.app.log("BBB", boardGame.CheckAnh() + "");
    }


    @Override
    public void render(float delta) {
        super.render(delta);
        batch = new SpriteBatch();
        batch.begin();
        batch.draw(badlogic, 0, 0);
        batch.end();
    }
}
