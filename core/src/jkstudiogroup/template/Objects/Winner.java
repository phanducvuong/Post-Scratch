package jkstudiogroup.template.Objects;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jkstudiogroup.template.Config.Card;
import jkstudiogroup.template.Config.Kind;
import jkstudiogroup.template.Config.Role;

public class Winner {

    private List<Tile> player;
    private List<Tile> bestBot;
    private List<List<Tile>> listBots;
    private List<List<Tile>> ListSpecialBot; //mảng listBots có cùng role
    private List<List<Tile>> ListSameRole;

    public Winner(List<Tile> player, List<Tile> ... listBots) {
        this.player = player;
        this.listBots = new ArrayList<List<Tile>>();
        this.ListSpecialBot = new ArrayList<List<Tile>>();

        if (listBots.length > 0)
            this.listBots.addAll(Arrays.asList(listBots));

        Board.SortCard(this.player);
        for (int i = 0; i < this.listBots.size(); i++)
            Board.SortCard(this.listBots.get(i));

        if (listBots.length > 0)
            GetListSpecialBot(this.listBots);
    }

    //TODO: GET WINNER -> SCREEN
    public void GetWinner() {
        List<Tile> BestBot = BestBotInSpecialBot();

        Gdx.app.log("BOT", BestBot.get(0).getCard() + " " + BestBotInSpecialBot().get(0).getKind());

        this.listBots.clear();
        this.ListSameRole.clear();
        this.ListSpecialBot.clear();

        this.listBots.add(this.player);
        this.listBots.add(BestBot);
        GetListSpecialBot(this.listBots);

        Gdx.app.log("WINNER", BestBotInSpecialBot().get(0).getCard() + " " + BestBotInSpecialBot().get(0).getKind());
    }


    //todo: tìm listBots có bài cao nhất
    private List<Tile> BestBotInSpecialBot() {
        if (this.ListSpecialBot.size() == 1)
            return this.ListSpecialBot.get(0);
        else {
            switch (CheckRole(this.ListSpecialBot.get(0))) {
            case SAP:
                //TODO: nếu trong ListSpecialBot có một Bot có chứa Ace thì return
                //TODO: nếu không có kiểm tra con bài lớn nhất -> return Tile
                this.ListSameRole = new ArrayList<List<Tile>>();
                for (int i = 0; i < this.ListSpecialBot.size(); i++) {
                    if (this.ListSpecialBot.get(i).get(0).getCard() == Card.Ace) {
                        this.ListSameRole.add(this.ListSpecialBot.get(i));
                        break;
                    }
                }

                if (this.ListSameRole == null || this.ListSameRole.size() <= 0) {
                    Tile bestTile = this.ListSpecialBot.get(0).get(0);
                    this.ListSameRole.add(this.ListSpecialBot.get(0));

                    for (int i = 0; i < this.ListSpecialBot.size(); i++)
                        if (bestTile.getCard().ordinal() < this.ListSpecialBot.get(i).get(0).getCard().ordinal()) {
                            bestTile = this.ListSpecialBot.get(i).get(0);
                            this.ListSameRole.remove(0);
                            this.ListSameRole.add(this.ListSpecialBot.get(i));
                        }
                }
                break;

            case LIENG:
                List<List<Tile>> LiengExistAce = new ArrayList<List<Tile>>();
                for (int i = 0; i < this.ListSpecialBot.size(); i++)
                    if (FindAceInTile(this.ListSpecialBot.get(i))) {
                        LiengExistAce.add(this.ListSpecialBot.get(i)); //todo: tìm liêng có chứa Ace
                    }

                //todo: liêng có chứa bài tây -> if() or else()
                if (LiengExistAce.size() > 0) { //todo: liêng có chứa Ace
                    if (LiengExistAce.size() == 1) this.ListSameRole = LiengExistAce;
                    else {
                        this.ListSameRole = new ArrayList<List<Tile>>();
                        List<Tile> tempTile = LiengExistAce.get(0);
                        for (int i = 0; i < LiengExistAce.size(); i++)
                            if (tempTile.get(0).getKind().ordinal() < LiengExistAce.get(i).get(0).getKind().ordinal())
                                tempTile = LiengExistAce.get(i);

                        this.ListSameRole.add(tempTile);
                    }
                }
                else {  //todo: liêng có chứa bài tây không chứa Ace
                    Tile bestTile = GetBestCardInTile(this.ListSpecialBot.get(0));
                    List<Tile> tiles = new ArrayList<Tile>();
                    this.ListSameRole = new ArrayList<List<Tile>>();

                    for (int i = 0; i < this.ListSpecialBot.size(); i++) {
                        Tile tempTile = GetBestCardInTile(this.ListSpecialBot.get(i));
                        if (bestTile.getCard().ordinal() < tempTile.getCard().ordinal()) {
                            bestTile = tempTile;
                        }
                    }

                    for (int i = 0; i < this.ListSpecialBot.size(); i++) {
                        Tile tempTile = GetBestCardInTile(this.ListSpecialBot.get(i));

                        if (bestTile.getCard().ordinal() == tempTile.getCard().ordinal()
                            && bestTile.getKind().ordinal() <= tempTile.getKind().ordinal()) {
                            bestTile = tempTile;
                            tiles = this.ListSpecialBot.get(i);
                        }
                    }
                    this.ListSameRole.add(tiles);
                }
                 break;

            case ANH:
                this.ListSameRole = new ArrayList<List<Tile>>();
                List<List<Tile>> tempTile = new ArrayList<List<Tile>>();

                //todo: Tìm lá bài có chất cao nhất trong bộ bài
                Tile bestKindInTile = GetBestKindInTile(this.ListSpecialBot.get(0));
                tempTile.add(this.ListSpecialBot.get(0));
                for (int i = 0; i < this.ListSpecialBot.size(); i++) {
                    Tile tempBestTile = GetBestKindInTile(this.ListSpecialBot.get(i));
                    if (bestKindInTile.getKind().ordinal() < tempBestTile.getKind().ordinal()) {
                        bestKindInTile = tempBestTile;
                        tempTile.remove(0);
                        tempTile.add(this.ListSpecialBot.get(i)); //todo: thêm những bộ bài có chất ngang nhau
                    }
                }

                for (int i = 0; i < this.ListSpecialBot.size(); i++)
                    if (!CheckTheSameTile(this.ListSpecialBot.get(i), bestKindInTile)
                        && FindKindInTile(this.ListSpecialBot.get(i), bestKindInTile))
                        tempTile.add(this.ListSpecialBot.get(i));

                if (tempTile.size() == 1)
                    this.ListSameRole = tempTile;
                else {
                    Tile bestCard = GetBestCardSameKind(tempTile.get(0), bestKindInTile.getKind());
                    this.ListSameRole.add(tempTile.get(0));

                    for (int i = 0; i < tempTile.size(); i++) {
                        Tile tempBestCard = GetBestCardSameKind(tempTile.get(i), bestKindInTile.getKind());
                        if (bestCard.getCard().ordinal() < tempBestCard.getCard().ordinal()) {
                            bestCard = tempBestCard;
                            this.ListSameRole.remove(0);
                            this.ListSameRole.add(tempTile.get(i));
                        }
                    }
                }
                break;

            case DIEM:
                List<Tile> bestTile = this.ListSpecialBot.get(0);
                List<List<Tile>> tempListBestPoint = new ArrayList<List<Tile>>();

                for (int i = 0; i < this.ListSpecialBot.size(); i++)
                    if (Board.CheckPoint(bestTile) < Board.CheckPoint(this.ListSpecialBot.get(i)))
                        bestTile = this.ListSpecialBot.get(i); //todo: tìm bài có điểm lớn nhất

                //todo: bài có cùng điểm lớn nhất
                for (int i = 0; i < this.ListSpecialBot.size(); i++)
                    if (Board.CheckPoint(bestTile) == Board.CheckPoint(this.ListSpecialBot.get(i)))
                        tempListBestPoint.add(this.ListSpecialBot.get(i));

                if (tempListBestPoint.size() == 1)
                    this.ListSameRole = tempListBestPoint;
                else { //todo: Tìm bài có chất lớn nhất

                    List<List<Tile>> tempListTheSameKind = new ArrayList<List<Tile>>(); //todo: mảng có cùng chất (chất lớn nhất)
                    Tile tempTileBestKind = GetBestKindInTile(tempListBestPoint.get(0));
                    tempListTheSameKind.add(tempListBestPoint.get(0));

                    //todo: tìm bài có chất cao nhât & lưu lại bộ bài đó
                    for (int i = 0; i < tempListBestPoint.size(); i++) {
                        Tile tempKind = GetBestKindInTile(tempListBestPoint.get(i));
                        if (tempTileBestKind.getKind().ordinal() < tempKind.getKind().ordinal()) {
                            tempTileBestKind = tempKind;
                            tempListTheSameKind.remove(0);
                            tempListTheSameKind.add(tempListBestPoint.get(i));
                        }
                    }

                    //todo: kiểm tra các bộ còn lại có cùng tempBestKind không. Yes ? save : nothing to do
                    for (int i = 0; i < tempListBestPoint.size(); i++)
                        if (!CheckTheSameTile(tempListBestPoint.get(i), tempTileBestKind)
                            && FindKindInTile(tempListBestPoint.get(i), tempTileBestKind))
                            tempListTheSameKind.add(tempListBestPoint.get(i));

                    if (tempListTheSameKind.size() == 1) this.ListSameRole = tempListTheSameKind;
                    else {
                        this.ListSameRole = new ArrayList<List<Tile>>();
                        Tile bestCard = GetBestCardInTile(tempListTheSameKind.get(0));
                        this.ListSameRole.add(tempListTheSameKind.get(0));

                        //todo: tìm bài có giá trị lớn nhất
                        for (int i = 0; i < tempListTheSameKind.size(); i++) {
                            Tile tempBestTile = GetBestCardInTile(tempListTheSameKind.get(i));
                            if (bestCard.getCard().ordinal() < tempBestTile.getCard().ordinal()) {
                                bestCard = tempBestTile;
                                this.ListSameRole.remove(0);
                                this.ListSameRole.add(tempListTheSameKind.get(i));
                            }
                        }
                    }
                }
                break;
            }
            return this.ListSameRole.get(0);
        }
    }

    /////////////////////////////////////BOARD//////////////////////////////////////////////////////

    //Tìm card lớn nhất trong tile
    private Tile GetBestCardInTile(List<Tile> listTile) {
        Tile bestCard = listTile.get(0);
        for (int i = 0; i < listTile.size(); i++)
            for (int j = i+1; j < listTile.size(); j++)
                if (listTile.get(i).getCard().ordinal() < listTile.get(j).getCard().ordinal())
                    bestCard = listTile.get(j);

        return bestCard;
    }

    //Tìm lá bài lớn nhất cùng kind
    private Tile GetBestCardSameKind(List<Tile> tiles, Kind kind) {
        List<Tile> tempListTile = new ArrayList<Tile>();
        for (Tile tile : tiles)
            if (tile.getKind() == kind) {
                tempListTile.add(tile);
            }

        Tile bestTile = tempListTile.get(0);
        for (Tile tile : tempListTile)
            if (bestTile.getCard().ordinal() < tile.getCard().ordinal())
                bestTile = tile;

        return bestTile;
    }

    //Tìm kind lớn nhất trong tile
    private Tile GetBestKindInTile(List<Tile> listTile) {
        Tile bestCard = listTile.get(0);
        for (int i = 0; i < listTile.size(); i++)
            for (int j = i+1; j < listTile.size(); j++)
                if (listTile.get(i).getKind().ordinal() < listTile.get(j).getKind().ordinal())
                    bestCard = listTile.get(j);

        return bestCard;
    }

    //Tìm Ace in tile
    private boolean FindAceInTile(List<Tile> tiles) { return tiles.get(0).getCard() == Card.Ace && Board.CheckPoint(tiles) == 1; }

    //Tìm bài giống nhau
    private boolean CheckTheSameTile(List<Tile> tiles, Tile tilee) {
        for (Tile tile : tiles)
            if (tile.getCard() == tilee.getCard() && tile.getKind() == tilee.getKind())
                return true;
        return false;
    }

    //Tìm kind trong tile
    private boolean FindKindInTile(List<Tile> tiles, Tile tile) {
        for (int i = 0; i < tiles.size(); i++)
            if (tiles.get(i).getKind() == tile.getKind())
                return true;
        return false;
    }

    //Tìm Ten in tile
    private boolean FindTenInTile(List<Tile> tiles) { return (tiles.get(0).getCard() == Card.Ten && Board.CheckPoint(tiles) == 0); }

    //Tìm bài có tổng bằng 0 -> liêng
    private boolean GetTileIsSumEqualZero(List<Tile> listTile) { return (Board.CheckPoint(listTile) == 0); }

    //TODO:Find best listBots in array listBots. Add the same listBots to array ListSpecialBot
    private List<Tile> BestBotAboutRole() {
        List<Tile> bestBot = this.listBots.get(0);
        for (int i = 0; i < this.listBots.size(); i++)
            if (CheckRole(bestBot).ordinal() < CheckRole(this.listBots.get(i)).ordinal())
                bestBot = this.listBots.get(i);
        return bestBot;
    }

    //TODO: tìm listBots có role lớn nhất -> tìm các listBots còn lại có cùng role với Winner
    private void GetListSpecialBot(List<List<Tile>> listsSpecial) {
        this.bestBot = BestBotAboutRole();
        for (int i = 0; i < listsSpecial.size(); i++)
            if (CheckRole(this.bestBot).ordinal() == CheckRole(listsSpecial.get(i)).ordinal()) {
                this.ListSpecialBot.add(this.listBots.get(i));
            }
    }

    /////////////////////////////////////BOARD//////////////////////////////////////////////////////

    private Role CheckRole(List<Tile> tiles) {
        if (Board.CheckSap(tiles))
            return Role.SAP;
        else if (Board.CheckLieng(tiles))
            return Role.LIENG;
        else if (Board.CheckAnh(tiles))
            return Role.ANH;
        else return Role.DIEM;
    }

    public List<List<Tile>> getListSameRole() {
        return ListSameRole;
    }
}
