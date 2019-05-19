package entity;

import java.util.List;
import java.util.Objects;

public class Game {
    private int id;
    private User player1;
    private User player2;
    private List<Card> deck;
    private List<Card> cards1;
    private List<Card> cards2;
    private int bank;
    private int rate1;
    private int rate2;
    private List<Card> onTable;
    private boolean isFirstMove;
    private boolean isSecondMove;
    private boolean isFirstWin;
    private boolean isSecondWin;
    private int firstRate;
    private int minRate;
    private boolean successMove;
    private int numOfMove = 0;
    private boolean isFirstOut;
    private DataForAnalysis data1;
    private DataForAnalysis data2;

    public Game() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getCards1() {
        return cards1;
    }

    public void setCards1(List<Card> cards1) {
        this.cards1 = cards1;
    }

    public List<Card> getCards2() {
        return cards2;
    }

    public void setCards2(List<Card> cards2) {
        this.cards2 = cards2;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getRate1() {
        return rate1;
    }

    public void setRate1(int rate1) {
        this.rate1 = rate1;
    }

    public int getRate2() {
        return rate2;
    }

    public void setRate2(int rate2) {
        this.rate2 = rate2;
    }

    public List<Card> getOnTable() {
        return onTable;
    }

    public void setOnTable(List<Card> onTable) {
        this.onTable = onTable;
    }

    public boolean isFirstMove() {
        return isFirstMove;
    }

    public void setFirstMove(boolean firstMove) {
        isFirstMove = firstMove;
    }

    public boolean isSecondMove() {
        return isSecondMove;
    }

    public void setSecondMove(boolean secondMove) {
        isSecondMove = secondMove;
    }

    public boolean isFirstWin() {
        return isFirstWin;
    }

    public void setFirstWin(boolean firstWin) {
        isFirstWin = firstWin;
    }

    public boolean isSecondWin() {
        return isSecondWin;
    }

    public void setSecondWin(boolean secondWin) {
        isSecondWin = secondWin;
    }

    public int getFirstRate() {
        return firstRate;
    }

    public void setFirstRate(int firstRate) {
        this.firstRate = firstRate;
    }

    public int getMinRate() {
        return minRate;
    }

    public void setMinRate(int minRate) {
        this.minRate = minRate;
    }

    public boolean isSuccessMove() {
        return successMove;
    }

    public void setSuccessMove(boolean successMove) {
        this.successMove = successMove;
    }

    public int getNumOfMove() {
        return numOfMove;
    }

    public void incrementNumOfMove() {
        numOfMove++;
    }

    public boolean isFirstOut() {
        return isFirstOut;
    }

    public void setNumOfMove(int numOfMove) {
        this.numOfMove = numOfMove;
    }

    public void setFirstOut(boolean firstOut) {
        isFirstOut = firstOut;
    }

    public DataForAnalysis getData1() {
        return data1;
    }

    public void setData1(DataForAnalysis data1) {
        this.data1 = data1;
    }

    public DataForAnalysis getData2() {
        return data2;
    }

    public void setData2(DataForAnalysis data2) {
        this.data2 = data2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != game.id) return false;
        if (bank != game.bank) return false;
        if (rate1 != game.rate1) return false;
        if (rate2 != game.rate2) return false;
        if (isFirstMove != game.isFirstMove) return false;
        if (isSecondMove != game.isSecondMove) return false;
        if (isFirstWin != game.isFirstWin) return false;
        if (isSecondWin != game.isSecondWin) return false;
        if (firstRate != game.firstRate) return false;
        if (minRate != game.minRate) return false;
        if (successMove != game.successMove) return false;
        if (numOfMove != game.numOfMove) return false;
        if (isFirstOut != game.isFirstOut) return false;
        if (!Objects.equals(player1, game.player1)) return false;
        if (!Objects.equals(player2, game.player2)) return false;
        if (!Objects.equals(deck, game.deck)) return false;
        if (!Objects.equals(cards1, game.cards1)) return false;
        if (!Objects.equals(cards2, game.cards2)) return false;
        if (!Objects.equals(onTable, game.onTable)) return false;
        if (!Objects.equals(data1, game.data1)) return false;
        return Objects.equals(data2, game.data2);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (player1 != null ? player1.hashCode() : 0);
        result = 31 * result + (player2 != null ? player2.hashCode() : 0);
        result = 31 * result + (deck != null ? deck.hashCode() : 0);
        result = 31 * result + (cards1 != null ? cards1.hashCode() : 0);
        result = 31 * result + (cards2 != null ? cards2.hashCode() : 0);
        result = 31 * result + bank;
        result = 31 * result + rate1;
        result = 31 * result + rate2;
        result = 31 * result + (onTable != null ? onTable.hashCode() : 0);
        result = 31 * result + (isFirstMove ? 1 : 0);
        result = 31 * result + (isSecondMove ? 1 : 0);
        result = 31 * result + (isFirstWin ? 1 : 0);
        result = 31 * result + (isSecondWin ? 1 : 0);
        result = 31 * result + firstRate;
        result = 31 * result + minRate;
        result = 31 * result + (successMove ? 1 : 0);
        result = 31 * result + numOfMove;
        result = 31 * result + (isFirstOut ? 1 : 0);
        result = 31 * result + (data1 != null ? data1.hashCode() : 0);
        result = 31 * result + (data2 != null ? data2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", deck=" + deck +
                ", cards1=" + cards1 +
                ", cards2=" + cards2 +
                ", bank=" + bank +
                ", rate1=" + rate1 +
                ", rate2=" + rate2 +
                ", onTable=" + onTable +
                ", isFirstMove=" + isFirstMove +
                ", isSecondMove=" + isSecondMove +
                ", isFirstWin=" + isFirstWin +
                ", isSecondWin=" + isSecondWin +
                ", firstRate=" + firstRate +
                ", minRate=" + minRate +
                ", successMove=" + successMove +
                ", numOfMove=" + numOfMove +
                ", isFirstOut=" + isFirstOut +
                ", data1=" + data1 +
                ", data2=" + data2 +
                '}';
    }
}
