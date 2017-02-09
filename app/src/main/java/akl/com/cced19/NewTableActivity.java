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
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    Button saveTable;
    Student mStudent;
    Table studentSubjectTable = new Table();

    Subject gSubject;

    DatabaseReference mDatabaseReference;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_table);

        subjectToolbar = (Toolbar) findViewById(R.id.table_toolbar);
        setSupportActionBar(subjectToolbar);
        subjectToolbar.setTitle("Choose Subjects");
        subjectsRecyclerView = (RecyclerView) findViewById(R.id.subjects_recycler_view);
        subjectsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();

        saveTable = (Button) findViewById(R.id.table_save);
        saveTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentSubjectTable.setSubjects(mSubjects);

                FirebaseUser mUser = mFirebaseAuth.getCurrentUser();
                mDatabaseReference.child(mUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mStudent = dataSnapshot.getValue(Student.class);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                mStudent.setTable(studentSubjectTable);

                mDatabaseReference.child(mUser.getUid()).setValue(mStudent);
            }
        });

        mGroupArrayAdapter = ArrayAdapter.createFromResource(this,R.array.groups_array,android.R.layout.simple_spinner_item);
        mGroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        updateUI();
    }

    private class SubjectHolder extends RecyclerView.ViewHolder{

        SubjectHolder(View itemView) {
            super(itemView);

            int group;
            int section;



            subjectTextView = (TextView) itemView.findViewById(R.id.subject_text_view);
            subjectGroupSpinner = (Spinner) itemView.findViewById(R.id.subject_group_spinner);
            sectionRg = (RadioGroup) itemView.findViewById(R.id.sectionrg);

            group = subjectGroupSpinner.getSelectedItemPosition()+1;
            if(sectionRg.getCheckedRadioButtonId() == R.id.section1rb){
                section = 1;
            }else if(sectionRg.getCheckedRadioButtonId() == R.id.section2rb){
                section = 2;
            }else{
                section = -1;
            }
            gSubject = setGroup(section,group);
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
            subjectTextView.setText(mSubject.getName());
            subjectGroupSpinner.setAdapter(mGroupArrayAdapter);
            mSubjects.set(position,gSubject);
        }

        @Override
        public SubjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View v = layoutInflater.inflate(R.layout.list_item_subject,parent,false);

            return new SubjectHolder(v);
        }
    }

    public void updateUI(){
        mSubjectLab = SubjectLab.get(getApplicationContext());
        mSubjects = mSubjectLab.getSubjects();
        mSubjectAdapter = new SubjectAdapter(mSubjects);
        subjectsRecyclerView.setAdapter(mSubjectAdapter);
    }

    public Subject setGroup(int section,int group){
        Subject subject = new Subject();

        if(section == -1){
            subject.setRegistered(false);
        }else if(section == 1){
            subject.setRegistered(true);
            subject.setSection(1);
            subject.setGroup(group);
        }else if(section == 2){
            subject.setRegistered(true);
            subject.setSection(2);
            subject.setGroup(group);
        }
        return subject;
    }
}
