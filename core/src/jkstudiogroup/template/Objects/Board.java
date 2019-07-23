package jkstudiogroup.template.Objects;

import com.badlogic.gdx.Gdx;

import java.util.Collections;
import java.util.List;
import jkstudiogroup.template.Config.Card;

public class Board {

    private List<Card> listCard;

    public Board(List<Card> card1) {
        this.listCard = card1;
        SortListCard();
    }

    //Sorting List Card
    private void SortListCard() {
        if (this.listCard != null)
            Collections.sort(listCard);
    }

    public void show() {
        for (Card card : listCard)
            Gdx.app.log("BBB", card + "");
    }

    //Check Lieng
    public boolean CheckLieng() {
        if (listCard.get(0) == Card.Ace && listCard.get(1) == Card.Queen && listCard.get(2) == Card.King)
            return true;
        else
            return listCard.get(0).ordinal() == (listCard.get(1).ordinal() - 1) && listCard.get(1).ordinal() == (listCard.get(2).ordinal() - 1);
    }
    
    //Check Sap
    public boolean CheckSap() {
        return (listCard.get(0) == listCard.get(1) && listCard.get(1) == listCard.get(2));
    }

    //Check Anh
    public boolean CheckAnh() {
        return (listCard.get(0).ordinal() >= 10 && listCard.get(1).ordinal() >= 10 && listCard.get(2).ordinal() >= 10);
    }

}
