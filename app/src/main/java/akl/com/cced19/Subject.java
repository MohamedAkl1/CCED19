package akl.com.cced19;

/**
 * Created by Mohamed Akl on 1/20/2017.
 */

public class Subject {
    private int group = 1;
    private String name;
    private int lectureDuration;
    private int tutDuration;
    private int labDuration;


    public int getGroup() {
        return group;
    }

    public int getLabDuration() {
        return labDuration;
    }

    public void setLabDuration(int labDuration) {
        this.labDuration = labDuration;
    }

    public int getLectureDuration() {
        return lectureDuration;
    }

    public void setLectureDuration(int lectureDuration) {
        this.lectureDuration = lectureDuration;
    }

    public int getTutDuration() {
        return tutDuration;
    }

    public void setTutDuration(int tutDuration) {
        this.tutDuration = tutDuration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
