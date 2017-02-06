package akl.com.cced19;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Akl on 2/4/2017.
 */

public class NewTableActivity extends AppCompatActivity {

    RecyclerView subjectsRecyclerView;
    List<Subject> mSubjects = new ArrayList<>();
    Toolbar subjectToolbar;
    Spinner subjectGroupSpinner;
    TextView subjectTextView;
    RadioGroup sectionRg;
    SubjectAdapter mSubjectAdapter;
    SubjectLab mSubjectLab;
    ArrayAdapter<CharSequence> mGroupArrayAdapter;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_table);

        subjectToolbar = (Toolbar) findViewById(R.id.table_toolbar);
        setSupportActionBar(subjectToolbar);
        subjectToolbar.setTitle("Choose Subjects");
        subjectsRecyclerView = (RecyclerView) findViewById(R.id.subjects_recycler_view);
        subjectsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mGroupArrayAdapter = ArrayAdapter.createFromResource(this,R.array.groups_array,android.R.layout.simple_spinner_item);
        mGroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        updateUI();
    }

    private class SubjectHolder extends RecyclerView.ViewHolder{

        SubjectHolder(View itemView) {
            super(itemView);

            subjectTextView = (TextView) itemView.findViewById(R.id.subject_text_view);
            subjectGroupSpinner = (Spinner) itemView.findViewById(R.id.subject_group_spinner);
            sectionRg = (RadioGroup) itemView.findViewById(R.id.sectionrg);


        }
    }

    private class SubjectAdapter extends RecyclerView.Adapter<SubjectHolder>{

        private List<Subject> adapterSubjects;

        SubjectAdapter(List<Subject> subjects){
            adapterSubjects = subjects;
        }

        @Override
        public int getItemCount() {
            return adapterSubjects.size();
        }

        @Override
        public void onBindViewHolder(SubjectHolder holder, int position) {
            Subject mSubject = mSubjects.get(position);

            bindSubject(mSubject);
        }

        @Override
        public SubjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View v = layoutInflater.inflate(R.layout.list_item_subject,parent,false);

            return new SubjectHolder(v);
        }

        public void bindSubject(Subject subject){
            subjectTextView.setText(subject.getName());
            subjectGroupSpinner.setAdapter(mGroupArrayAdapter);
        }
    }

    public void updateUI(){
        mSubjectLab = SubjectLab.get(getApplicationContext());
        mSubjects = mSubjectLab.getSubjects();
        mSubjectAdapter = new SubjectAdapter(mSubjects);
        subjectsRecyclerView.setAdapter(mSubjectAdapter);
    }
}
