package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Kunakbaev Artem
 */
public class Respondent {
    private String name;
    private List<Respondent> like;
    private List<Respondent> dislike;

    public Respondent(String name) {
        this.name = name;
        like = new ArrayList<Respondent>();
        dislike = new ArrayList<Respondent>();
    }

    public void setLike(Respondent r) {
        like.add(r);
    }

    public void setDislike(Respondent r) {
        dislike.add(r);
    }

    public String getName() {
        return name;
    }

    public List<Respondent> getLike() {
        return like;
    }

    public List<Respondent> getDislike() {
        return dislike;
    }

    @Override
    public String toString() {
        return "Respondent{" +
                "name='" + name + '\'' +
                '}';
    }
}
