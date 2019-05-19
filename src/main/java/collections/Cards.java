package collections;

import dao.CardDao;
import entity.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private static List<Card> cards;

    static {
        cards = new ArrayList<>(CardDao.getAllCards());
    }

    public static List<Card> getDeck() {
        List<Card> deck = new ArrayList<>(cards);
        Collections.shuffle(deck);
        return deck;
    }
}
