package akl.com.cced19;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends Fragment {

    private Button mStudentSignin;
    private Button mStudentRegister;
    private Button mAdmin;

    private FirebaseAuth mFirebaseAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container,false);
        mFirebaseAuth = FirebaseAuth.getInstance();
        /*if(mFirebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getActivity(),MainAppContainer.class));
        }*/

        mStudentSignin = (Button) v.findViewById(R.id.student_signin);
        mStudentSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),SigninActivity.class));
            }
        });

        mStudentRegister = (Button) v.findViewById(R.id.student_register);
        mStudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),RegisterActivity.class));
            }
        });

        mAdmin = (Button) v.findViewById(R.id.admin);
        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),AdminActivity.class));
            }
        });
        return v;
    }
}
