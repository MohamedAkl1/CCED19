package akl.com.cced19;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainFragment extends Fragment {

    private Button mStudentSignin;
    private Button mStudentRegister;
    private Button mAdmin;

    Context signin;
    Context register;
    Context admin;

    private FirebaseAuth mFirebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);

        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mFirebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainAppContainer.class));
        }

        mStudentSignin = (Button) findViewById(R.id.student_signin);
        mStudentSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SigninActivity.class));
            }
        });

        mStudentRegister = (Button) findViewById(R.id.student_register);
        mStudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        mAdmin = (Button) findViewById(R.id.admin);
        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminActivity.class));
            }
        });

        return v;
    }
}
