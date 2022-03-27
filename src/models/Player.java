package models;

import java.util.Date;
import java.util.Objects;

public class Player {
    private Integer id;
    private String name;
    private Integer score;
    private Long time;
    private Date date;
    private static Integer idAutoIncrement = 0;

    public Player() {
        this.id = idAutoIncrement++;
        this.date = new Date();
    }

    public Player(String name, Integer score, Long time) {
        this.id = idAutoIncrement++;
        this.name = name;
        this.score = score;
        this.time = time;
        this.date = new Date();
    }

    public Player(Integer id, String name, Integer score, Long time) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.time = time;
        this.date = new Date();
    }

    public Player(Integer id, String name, Integer score, Long time, Date date) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.time = time;
        this.date = date;
    }

    public static int getIdAutoIncrement(){
        return idAutoIncrement;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Player date(Date date) {
        setDate(date);
        return this;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Player id(Integer id) {
        setId(id);
        return this;
    }

    public Player name(String name) {
        setName(name);
        return this;
    }

    public Player score(Integer score) {
        setScore(score);
        return this;
    }

    public Player time(Long time) {
        setTime(time);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Player)) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(id, player.id) && Objects.equals(name, player.name) && Objects.equals(score, player.score)
                && Objects.equals(time, player.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, score, time);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", score='" + getScore() + "'" +
                ", time='" + getTime() + "'" +
                "}";
    }

}
