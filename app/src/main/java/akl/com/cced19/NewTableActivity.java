package akl.com.cced19;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Akl on 2/4/2017.
 */


interface RecyclerListener{
    void updateStudentSection(int pos,int id);
    void updateStudentGroup(int pos,String sp);
}

public class NewTableActivity extends AppCompatActivity implements RecyclerListener {

    int holderPosition = 0;

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
    Table studentSubjectTable = new Table();
    RadioButton rb1;
    RadioButton rb2;

    ProgressDialog pd;

    SubjectHolder holder1;

    Student importedStudent = new Student();


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

        mGroupArrayAdapter = ArrayAdapter.createFromResource(this, R.array.groups_array, android.R.layout.simple_spinner_item);
        mGroupArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        pd = new ProgressDialog(this);

        updateUI();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();



        saveTable = (Button) findViewById(R.id.table_save);
        saveTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importedStudent = MainAppContainer.getmStudent();

                pd.setMessage("Adding Subjects");
                pd.show();

                for(int i = 0;i < mSubjects.size();i++){
                    if(mSubjects.get(i).getSection() == 0){
                        mSubjects.get(i).setRegistered(false);
                    }
                }

                studentSubjectTable.setSubjects(mSubjects);

                importedStudent.setTable(studentSubjectTable);

                FirebaseUser mUser = mFirebaseAuth.getCurrentUser();
                if (mUser != null) {
                    mDatabaseReference.child(mUser.getUid()).setValue(importedStudent).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                pd.dismiss();
                                Intent intent = new Intent(getApplicationContext(),MainAppContainer.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }

            }
        });
    }

    private class SubjectHolder extends RecyclerView.ViewHolder{

        SubjectHolder(View itemView) {
            super(itemView);

            subjectTextView = (TextView) itemView.findViewById(R.id.subject_text_view);
            subjectGroupSpinner = (Spinner) itemView.findViewById(R.id.subject_group_spinner);
            sectionRg = (RadioGroup) itemView.findViewById(R.id.sectionrg);
            rb1 = (RadioButton) itemView.findViewById(R.id.section1rb);
            rb2 = (RadioButton) itemView.findViewById(R.id.section2rb);


            subjectGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String group = (String) adapterView.getSelectedItem();
                    updateStudentGroup(getAdapterPosition(),group);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            sectionRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    updateStudentSection(getAdapterPosition(),radioGroup.getCheckedRadioButtonId());
                }
            });
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
        public void onBindViewHolder(final SubjectHolder holder, int position) {
            Subject mSubject = mSubjects.get(position);
            holder1 = holder;

            subjectTextView.setText(mSubject.getName());
            subjectGroupSpinner.setAdapter(mGroupArrayAdapter);
        }


        @Override
        public SubjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View v = layoutInflater.inflate(R.layout.list_item_subject,parent,false);

            return new SubjectHolder(v);
        }
    }

    @Override
    public void updateStudentSection(int pos, int id) {
        Subject subject = mSubjects.get(pos);

        if(id == R.id.section1rb){
            subject.setSection(1);
        }else if(id == R.id.section2rb){
            subject.setSection(2);
        }else{
            subject.setRegistered(false);
        }
        mSubjects.set(pos,subject);
    }

    @Override
    public void updateStudentGroup(int pos,String sp) {
        Subject subject = mSubjects.get(pos);

        if(sp.equals("Group 1")){
            subject.setRegistered(true);
            subject.setGroup(1);
        }
        else if(sp.equals("Group 2")){
            subject.setRegistered(true);
            subject.setGroup(2);
        }
        else if(sp.equals("Group 3")){
            subject.setRegistered(true);
            subject.setGroup(3);
        }else if(sp.equals("Select Group")){
            subject.setRegistered(false);
        }

        mSubjects.set(pos,subject);
    }

    public void updateUI(){
        mSubjectLab = SubjectLab.get(getApplicationContext());
        mSubjects = mSubjectLab.getSubjects();
        mSubjectAdapter = new SubjectAdapter(mSubjects);
        subjectsRecyclerView.setAdapter(mSubjectAdapter);
    }



    public Subject setGroup(int section,int group){
        Subject subject = new Subject();


        if(section == 1){
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
