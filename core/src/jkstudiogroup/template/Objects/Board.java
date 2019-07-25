package jkstudiogroup.template.Objects;

import com.badlogic.gdx.Gdx;

import java.util.Collections;
import java.util.List;
import jkstudiogroup.template.Config.Card;
import jkstudiogroup.template.Config.Kind;

public class Board {

    //Sorting List Card
    public static void SortCard(List<Tile> player) {
        if (player != null)
            for (int i = 0; i <player.size(); i++)
                for (int j = i + 1; j < player.size(); j++) {
                    if (player.get(i).getCard().ordinal() > player.get(j).getCard().ordinal()) {
                        Tile temp = player.get(i);
                        player.set(i, player.get(j));
                        player.set(j, temp);
                    }
                }
    }

    //Show card
    public static void Show(List<Tile> player) {
        for (Tile tile : player)
            Gdx.app.log("BBB", tile.getCard() + "");
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    //Check Sap
    public static boolean CheckSap(List<Tile> player) {
        return (player.get(0).getCard() == player.get(1).getCard() && player.get(1).getCard() == player.get(2).getCard());
    }

    //Check Lieng
    public static boolean CheckLieng(List<Tile> player) {
        if (player.get(0).getCard() == Card.Ace && player.get(1).getCard() == Card.Queen
                && player.get(2).getCard() == Card.King
                && player.get(1).getKind() == player.get(0).getKind()
                && player.get(2).getKind() == player.get(0).getKind())
            return true;
        else return player.get(0).getCard().ordinal() == (player.get(1).getCard().ordinal() - 1)
                && player.get(1).getCard().ordinal() == (player.get(2).getCard().ordinal() - 1)
                && player.get(1).getKind() == player.get(0).getKind()
                && player.get(2).getKind() == player.get(0).getKind();
    }

    //Check Anh
    public static boolean CheckAnh(List<Tile> player) {
        return (player.get(0).getCard().ordinal() > 10 && player.get(1).getCard().ordinal() > 10 && player.get(2).getCard().ordinal() > 10);
    }

    //Check Point
    public static int CheckPoint(List<Tile> player) {
        int sum = 0;
        for (Tile tile : player)
            if (tile.getCard().ordinal() < 10)
                sum += tile.getCard().ordinal();

        return (sum % 10);
    }
}
