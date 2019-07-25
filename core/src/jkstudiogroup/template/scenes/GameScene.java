package jkstudiogroup.template.scenes;

import com.badlogic.gdx.Game;
import java.util.ArrayList;
import java.util.List;
import jkstudiogroup.template.Config.Card;
import jkstudiogroup.template.Config.Kind;
import jkstudiogroup.template.Objects.Tile;
import jkstudiogroup.template.Objects.Winner;

public class GameScene extends BaseScene {

    public GameScene(Game game) {
        super(game);

        //TODO: khi phát bài -> khởi tạo số array tương ứng với số bot trong đó player được mặc định id = 0

        Tile Ace = new Tile(Card.Ten, Kind.Spade);
        Tile Seven = new Tile(Card.Nine, Kind.Heart);
        Tile Ten = new Tile(Card.Jack, Kind.Diamond);

        List<Tile> player = new ArrayList<Tile>();
        player.add(Ace);
        player.add(Seven);
        player.add(Ten);

        Tile tile1 = new Tile(Card.Ten, Kind.Diamond);
        Tile tile2 = new Tile(Card.King, Kind.Heart);
        Tile tile3 = new Tile(Card.Jack, Kind.Club);
        List<Tile> bot1 = new ArrayList<Tile>();
        bot1.add(tile1);
        bot1.add(tile2);
        bot1.add(tile3);

        Tile tile4 = new Tile(Card.Six, Kind.Heart);
        Tile tile5 = new Tile(Card.Ace, Kind.Club);
        Tile tile6 = new Tile(Card.Six, Kind.Heart);
        List<Tile> bot2 = new ArrayList<Tile>();
        bot2.add(tile4);
        bot2.add(tile5);
        bot2.add(tile6);

        Tile tile7 = new Tile(Card.Jack, Kind.Spade);
        Tile tile8 = new Tile(Card.Ace, Kind.Heart);
        Tile tile9 = new Tile(Card.Jack, Kind.Club);
        List<Tile> bot3 = new ArrayList<Tile>();
        bot3.add(tile7);
        bot3.add(tile8);
        bot3.add(tile9);

        Tile tile10 = new Tile(Card.Queen, Kind.Spade);
        Tile tile11 = new Tile(Card.Nine, Kind.Club);
        Tile tile12 = new Tile(Card.Jack, Kind.Spade);
        List<Tile> bot4 = new ArrayList<Tile>();
        bot4.add(tile10);
        bot4.add(tile11);
        bot4.add(tile12);

        Winner winner = new Winner(player, bot1, bot2, bot3, bot4);
        winner.GetWinner();

//        Gdx.app.log("BBB", winner.getListSameRole().size() + "");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
