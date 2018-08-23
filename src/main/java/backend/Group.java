package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Kunakbaev Artem
 */
public class Group {
    private List<Respondent> group;
    private String nameGroup;
    private int groupSize;

    public Group(String nameGroup) {
        group = new ArrayList<Respondent>();
        this.nameGroup = nameGroup;
        groupSize = 0;
    }

    public void addRespondent(Respondent r) {
        group.add(r);
        groupSize++;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public List getRespondents(){
        return  group;
    }

    public Respondent getRespondent(int index){
        return group.get(index);
    }

    public Respondent getRespondent(String name){
        for (Respondent r :
                group) {
            if (r.getName().equals(name)) return r;
        }
        return null;
    }

    @Override
    public String toString() {
        return "Group{" +
                "group=" + group +
                ", nameGroup='" + nameGroup + '\'' +
                '}';
    }
}
