package backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @autor Kunakbaev Artem
 */
public class Respondent {
    private int id;
    private String dateResearch;
    private String groupName;
    private String motiv;
    private String name;
    private String likes;
    private String dislikes;
    Date date;
    private SimpleDateFormat dateFormat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateResearch() {
        return dateResearch;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getMotiv() {
        return motiv;
    }

    public String getName() {
        return name;
    }

    public String getLikes() {
        return likes;
    }

    public String getDislikes() {
        return dislikes;
    }



    public Respondent(int id, String dateResearch, String groupName, String motiv, String name, String likes, String dislikes) {
        this.id = id;
        this.dateResearch = dateResearch;
        this.groupName = groupName;
        this.motiv = motiv;
        this.name = name;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public Respondent(String name) {
        this.id = 0;
        dateFormat = new SimpleDateFormat("dd.MM.yy, HH:mm");
        this.dateResearch = dateFormat.format(new Date());
        this.groupName = "Не указано";
        this.motiv = "Не указано";
        this.name = name;
        this.likes = "Не указано";
        this.dislikes = "Не указано";
    }

    //    private List<Respondent> like;
//    private List<Respondent> dislike;
//
//    public Respondent(String name) {
//        this.name = name;
//        like = new ArrayList<>();
//        dislike = new ArrayList<>();
//    }
//
//    public void setLike(Respondent r) {
//        like.add(r);
//    }
//
//    public void setDislike(Respondent r) {
//        dislike.add(r);
//    }
//
//    public List<Respondent> getLike() {
//        return like;
//    }
//
//    public List<Respondent> getDislike() {
//        return dislike;
//    }

    @Override
    public String toString() {
        return "Респондент{" +
                "\nid=" + id +
                "\nДата='" + dateResearch + '\'' +
                "\nГруппа='" + groupName + '\'' +
                "\nМотив='" + motiv + '\'' +
                "\nИмя='" + name + '\'' +
                "\nВыборы='" + likes + '\'' +
                "\nОтклонения='" + dislikes + '\'' +
                '}' +
                "\n\n";
    }
}
