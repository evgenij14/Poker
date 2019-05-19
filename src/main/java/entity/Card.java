package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "poker_cards")
public class Card implements Serializable {
    private String suit;
    private String rank;
    @Id
    private String reference;
    @Column(name = "rate_of_card")
    private int rateOfCard;

    public Card() {
    }

    public Card(String suit, String rank, String reference, int rateOfCard) {
        this.suit = suit;
        this.rank = rank;
        this.reference = reference;
        this.rateOfCard = rateOfCard;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getRateOfCard() {
        return rateOfCard;
    }

    public void setRateOfCard(int rateOfCard) {
        this.rateOfCard = rateOfCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rateOfCard != card.rateOfCard) return false;
        if (suit != null ? !suit.equals(card.suit) : card.suit != null) return false;
        if (rank != null ? !rank.equals(card.rank) : card.rank != null) return false;
        return reference != null ? reference.equals(card.reference) : card.reference == null;

    }

    @Override
    public int hashCode() {
        int result = suit != null ? suit.hashCode() : 0;
        result = 31 * result + (rank != null ? rank.hashCode() : 0);
        result = 31 * result + (reference != null ? reference.hashCode() : 0);
        result = 31 * result + rateOfCard;
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", rank='" + rank + '\'' +
                ", reference='" + reference + '\'' +
                ", rateOfCard=" + rateOfCard +
                '}';
    }
}
