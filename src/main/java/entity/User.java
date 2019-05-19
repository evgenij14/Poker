package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "poker_user")
public class User implements Serializable {
    @Id
    private String login;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private int games;
    private int wins;
    private int loses;
    @Column(name = "win_rate")
    private int winRate;
    private int money;
    private transient int moneyInGame;
    private transient int gameId;

    public User() {
    }

    public User(String login, String password, String firstName, String lastName, String email, int games, int wins, int loses, int winRate, int money) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.games = games;
        this.wins = wins;
        this.loses = loses;
        this.winRate = winRate;
        this.money = money;
    }

    public User(String login, String password, String firstName, String lastName, String email,
                int games, int wins, int loses, int winRate, int money, int moneyInGame, int gameId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.games = games;
        this.wins = wins;
        this.loses = loses;
        this.winRate = winRate;
        this.money = money;
        this.moneyInGame = moneyInGame;
        this.gameId = gameId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoneyInGame() {
        return moneyInGame;
    }

    public void setMoneyInGame(int moneyInGame) {
        this.moneyInGame = moneyInGame;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (games != user.games) return false;
        if (wins != user.wins) return false;
        if (loses != user.loses) return false;
        if (winRate != user.winRate) return false;
        if (money != user.money) return false;
        if (moneyInGame != user.moneyInGame) return false;
        if (gameId != user.gameId) return false;
        if (!Objects.equals(login, user.login)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        return !Objects.equals(email, user.email);

    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + games;
        result = 31 * result + wins;
        result = 31 * result + loses;
        result = 31 * result + winRate;
        result = 31 * result + money;
        result = 31 * result + moneyInGame;
        result = 31 * result + gameId;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", games=" + games +
                ", wins=" + wins +
                ", loses=" + loses +
                ", winRate=" + winRate +
                ", money=" + money +
                ", moneyInGame=" + moneyInGame +
                ", gameId=" + gameId +
                '}';
    }
}
