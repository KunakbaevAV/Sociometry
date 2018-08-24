package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Kunakbaev Artem
 */
public class Respondent {
    private String name;
    private String groupName;
    private String motiv;
    private String timeResearch;
    private String likes;
    private String dislikes;

    private List<Respondent> like;
    private List<Respondent> dislike;

    public Respondent(String name) {
        this.name = name;
        like = new ArrayList<>();
        dislike = new ArrayList<>();
    }

    Respondent(String timeResearch,String groupName, String name,  String motiv,  String likes, String dislikes) {
        this.name = name;
        this.groupName = groupName;
        this.motiv = motiv;
        this.timeResearch = timeResearch;
        this.likes = likes;
        this.dislikes = dislikes;
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
