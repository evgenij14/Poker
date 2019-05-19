package service.game;

import collections.Games;
import entity.Card;
import entity.Combinations;
import entity.DataForAnalysis;
import entity.Game;
import org.springframework.stereotype.Component;
import service.UserService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class AnalysisOfCombinationsService {
    private final UserService userService;

    public AnalysisOfCombinationsService(UserService userService) {
        this.userService = userService;
    }

    void analysis() throws NullPointerException {
        Optional<Game> optionalGame = Optional.of(Games.getGame().get(userService.getUser().getGameId()));
        Game g = optionalGame.orElseThrow(NullPointerException::new);

        List<Card> cards1 = listOfCards(g.getOnTable(), g.getCards1());
        List<Card> cards2 = listOfCards(g.getOnTable(), g.getCards2());
        g.setData1(dataForAnalysis(convertedCollection(cards1), mapForAnalysisFlush(cards1), cards1));
        g.setData2(dataForAnalysis(convertedCollection(cards2), mapForAnalysisFlush(cards2), cards2));
        if (g.getData1().getCombinations().getRate() > g.getData2().getCombinations().getRate()) {
            g.setFirstWin(true);
        } else if (g.getData1().getCombinations().getRate() < g.getData2().getCombinations().getRate()) {
            g.setSecondWin(true);
        } else if (g.getData1().getCombinations().getRate() == g.getData2().getCombinations().getRate()) {
            if (g.getData1().getHighCard() > g.getData2().getHighCard()) {
                g.setFirstWin(true);
            } else if (g.getData1().getHighCard() < g.getData2().getHighCard()) {
                g.setSecondWin(true);
            } else if (g.getData1().getHighCard() == g.getData2().getHighCard()) {
                if (comparingOfKicker(g.getData1(), g.getData2()) == 1) {
                    g.setFirstWin(true);
                } else if (comparingOfKicker(g.getData1(), g.getData2()) == 2) {
                    g.setSecondWin(true);
                } else {
                    g.setFirstWin(true);
                    g.setSecondWin(true);
                }
            }
        }
    }


    // method return List of all cards( onTable + playerCards)
    private List<Card> listOfCards(List<Card> cardOnTable, List<Card> playerCards) {
        List<Card> list = new CopyOnWriteArrayList<>(cardOnTable);
        list.addAll(playerCards);
        return list;
    }

    // method return Map<rateOfCard, amountOfCards>
    private Map<Integer, Integer> convertedCollection(List<Card> list) {
        List<Integer> rangsOfCard = new LinkedList<>();
        for (Card card : list) {
            rangsOfCard.add(card.getRateOfCard());
        }
        Integer am;
        Map<Integer, Integer> out = new ConcurrentHashMap<>();
        for (Integer i : rangsOfCard) {
            am = out.get(i);
            out.put(i, am == null ? 1 : am + 1);
        }
        return out;
    }

    // method return Map<suit, amountOfCards>
    private Map<String, Integer> mapForAnalysisFlush(List<Card> listOfCards) {
        String suit;
        Map<String, Integer> numOfSuits = new ConcurrentHashMap<>();
        for (Card card : listOfCards) {
            suit = card.getSuit();
            Integer value = numOfSuits.get(suit);
            numOfSuits.put(suit, value == null ? 1 : value + 1);
        }
        return numOfSuits;
    }

    private DataForAnalysis flush(Map<String, Integer> forAnalysisFlush,
                                  List<Card> listOfCards) {
        if (forAnalysisFlush.values().stream().max(Comparator.comparingInt(o -> o)).get() == 5) {
            for (String str : forAnalysisFlush.keySet()) {
                if (forAnalysisFlush.get(str) == 5) {
                    int highCard = listOfCards.stream().filter(card -> card.getSuit().equals(str))
                            .max(Comparator.comparingInt(Card::getRateOfCard)).get().getRateOfCard();
                    return new DataForAnalysis(Combinations.FLUSH, highCard);
                }
            }
        }
        return null;
    }

    private DataForAnalysis twoPairs(Map<Integer, Integer> map) {
        int valueOfLastPairs = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i).equals(2)) {
                valueOfLastPairs = i;
            }
            map.remove(i);
        }

        return new DataForAnalysis(Combinations.TWO_PAIRS, valueOfLastPairs, map);
    }

    private DataForAnalysis straight(Map<Integer, Integer> map) {
        int firstRate = map.keySet().stream().min(Comparator.comparingInt(o -> o)).get();
        int secondRate = map.keySet().stream().skip(1).min(Comparator.comparingInt(o -> o)).get();
        int thirdRate = map.keySet().stream().skip(2).min(Comparator.comparingInt(o -> o)).get();
        boolean b = true, a = true, c = true;
        for (int i = 0; i < 5; i++) {
            if (!map.keySet().contains(firstRate + i)) {
                b = false;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (!map.keySet().contains(secondRate + i)) {
                a = false;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (!map.keySet().contains(thirdRate + i)) {
                c = false;
            }
        }
        if (c) {
            return new DataForAnalysis(Combinations.STRAIGHT, thirdRate + 4);
        } else if (a) {
            return new DataForAnalysis(Combinations.STRAIGHT, secondRate + 4);
        } else if (b) {
            return new DataForAnalysis(Combinations.STRAIGHT, firstRate + 4);
        }
        return null;
    }

    private DataForAnalysis dataForAnalysis(Map<Integer, Integer> map, Map<String, Integer> forAnalysisFlush,
                                            List<Card> listCards) {
        //ROYAL_FLUSH
        if (flush(forAnalysisFlush, listCards) != null && straight(map) != null && straight(map).getHighCard() == 13) {
            return new DataForAnalysis(Combinations.ROYAL_FLUSH);
        }
        //STRAIGHT_FLASH
        else if (flush(forAnalysisFlush, listCards) != null && straight(map) != null) {
            return new DataForAnalysis(Combinations.STRAIGHT_FLUSH, straight(map).getHighCard());
        }

        // FLUSH
        if (flush(forAnalysisFlush, listCards) != null) {
            return flush(forAnalysisFlush, listCards);
        }

        // STRAIGHT
        if (straight(map) != null) {
            return straight(map);
        }

        switch (map.size()) {
            case 4:

                // FOUR_OF_A_KIND
                if (map.values().stream().max(Comparator.comparingInt(o -> o)).get() == 4) {
                    int highHand = 0;
                    for (Integer i : map.keySet()) {
                        if (map.get(i).equals(4)) {
                            highHand = i;
                            map.remove(i);
                            break;
                        }
                    }
                    return new DataForAnalysis(Combinations.FOUR_OF_A_KIND, highHand, map);
                }
                //FULL_HOUSE
                if (map.values().stream().max(Comparator.comparingInt(o -> o)).get() == 3 && map.values().contains(2)) {
                    Map<Integer, Integer> out = new HashMap<>();
                    int highCard = 0;
                    for (Integer i : map.keySet()) {
                        if (map.get(i).equals(3)) {
                            highCard = i;
                        }
                        if (map.get(i).equals(2)) {
                            out.put(i, map.get(i));
                        }
                    }
                    return new DataForAnalysis(Combinations.FULL_HOUSE, highCard, out);
                }

                // TWO_PAIRS
                if (map.values().stream().filter(p -> p == 2).count() == 3) {
                    return twoPairs(map);
                }
                break;
            case 7:

                //HIGH_HAND
                return new DataForAnalysis(Combinations.HIGH_HAND, map.keySet().stream().max(Comparator.comparingInt(o -> o)).get(), map);
            case 6:

                //ONE_PAIR
                int valueOfPair = 0;
                for (Integer i : map.keySet()) {
                    if (map.get(i).equals(2)) {
                        valueOfPair = i;
                        map.remove(i);
                    }
                }
                return new DataForAnalysis(Combinations.ONE_PAIR, valueOfPair, map);
            case 5:

                //THREE_OF_A_KIND
                if (map.values().stream().max(Comparator.comparingInt(o -> o)).get() == 3) {
                    int valueOfSet = 0;
                    for (Integer i : map.keySet()) {
                        if (map.get(i).equals(3)) {
                            valueOfSet = i;
                            map.remove(i);
                        }
                    }
                    return new DataForAnalysis(Combinations.THREE_OF_A_KIND, valueOfSet, map);

                    //TWO_PAIRS
                } else if (map.values().stream().filter(o -> o.equals(2)).count() == 2) {
                    return twoPairs(map);
                }

        }
        return null;
    }

    // method show who wins between players
    private int comparingOfKicker(DataForAnalysis data1, DataForAnalysis data2) {
        Map<Integer, Integer> player1 = data1.getMap();
        Map<Integer, Integer> player2 = data2.getMap();
        if (data1.getCombinations() == Combinations.ONE_PAIR || data1.getCombinations() == Combinations.HIGH_HAND || data1.getCombinations() == Combinations.THREE_OF_A_KIND) {
            int p1;
            int p2;
            while (player1 != null && player2 != null) {
                p1 = player1.values().stream().max(Comparator.comparingInt(o -> o)).get();
                p2 = player2.values().stream().max(Comparator.comparingInt(o -> o)).get();
                if (p1 > p2) {
                    return 1;
                } else if (p1 < p2) {
                    return 2;
                }
                for (Integer i : player1.keySet()) {
                    if (i.equals(p1)) {
                        player1.remove(i);
                    }
                }
                for (Integer i : player2.keySet()) {
                    if (i.equals(p2)) {
                        player2.remove(i);
                    }
                }
            }
        } else if (data1.getCombinations() == Combinations.FULL_HOUSE || data1.getCombinations() == Combinations.TWO_PAIRS) {
            int p1 = 0;
            int p2 = 0;

            for (Integer i : player1.keySet()) {
                if (player1.get(i).equals(2)) {
                    p1 = i;
                }
            }
            for (Integer i : player2.keySet()) {
                if (player2.get(i).equals(2)) {
                    p2 = i;
                }
            }
            if (p1 < p2) {
                return 2;
            } else if (p1 > p2) {
                return 1;
            }
        }
        return 0;
    }
}


