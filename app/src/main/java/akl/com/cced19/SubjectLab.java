package akl.com.cced19;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Akl on 2/6/2017.
 */

public class SubjectLab {

    private static SubjectLab sSubjectLab;

    private List<Subject> mSubjects;

    public static SubjectLab get(Context context){
        if(sSubjectLab == null){
            sSubjectLab = new SubjectLab(context);
        }
        return sSubjectLab;
    }

    private SubjectLab(Context context){
        mSubjects = new ArrayList<>();
        for(int i = 0;i < 5;i++){
            Subject subject = new Subject();
            subject.setName("subject #"+i);
            mSubjects.add(subject);
        }
    }

    public List<Subject> getSubjects(){
        return mSubjects;
    }
}
