package akl.com.cced19;

import java.util.List;

/**
 * Created by Mohamed Akl on 2/3/2017.
 */

public class Table {
    List<Subject> mSubjects;

    public Table(List<Subject> subjects) {
        mSubjects = subjects;
    }

    public Table(){

    }

    public List<Subject> getSubjects() {
        return mSubjects;
    }

    public void setSubjects(List<Subject> subjects) {
        mSubjects = subjects;
    }
}
