package entity;

public enum Combinations {
    HIGH_HAND(0),
    ONE_PAIR(1),
    TWO_PAIRS(2),
    THREE_OF_A_KIND(3),
    STRAIGHT(4),
    FLUSH(5),
    FULL_HOUSE(6),
    FOUR_OF_A_KIND(7),
    STRAIGHT_FLUSH(8),
    ROYAL_FLUSH(9);

    private int rate;

    Combinations(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }
}
