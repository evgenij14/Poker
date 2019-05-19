package entity;

import java.util.Map;

public class DataForAnalysis {
    private Combinations combinations;
    private int highCard;
    private Map<Integer, Integer> map;

    public DataForAnalysis() {
    }

    public DataForAnalysis(Combinations combinations, int highCard, Map<Integer, Integer> map) {
        this.combinations = combinations;
        this.highCard = highCard;
        this.map = map;
    }

    public DataForAnalysis(Combinations combinations, int highCard) {
        this.combinations = combinations;
        this.highCard = highCard;
    }

    public DataForAnalysis(Combinations combinations) {
        this.combinations = combinations;
    }

    public Combinations getCombinations() {
        return combinations;
    }

    public void setCombinations(Combinations combinations) {
        this.combinations = combinations;
    }

    public int getHighCard() {
        return highCard;
    }

    public void setHighCard(int highCard) {
        this.highCard = highCard;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataForAnalysis that = (DataForAnalysis) o;

        if (highCard != that.highCard) return false;
        if (combinations != that.combinations) return false;
        return map != null ? map.equals(that.map) : that.map == null;

    }

    @Override
    public int hashCode() {
        int result = combinations != null ? combinations.hashCode() : 0;
        result = 31 * result + highCard;
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
    }

    @Override
    public String
    toString() {
        return "DataForAnalysis{" +
                "combinations=" + combinations +
                ", highCard=" + highCard +
                ", map=" + map +
                '}';
    }
}
