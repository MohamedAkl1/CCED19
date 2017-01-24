package akl.com.cced19;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class StartFragment extends Fragment {

    private Button mStudentSignin;
    private Button mStudentRegister;
    private Button mAdmin;
    private Transaction transaction = new Transaction();
    private Context mActivity;

    private FirebaseAuth mFirebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_start,container,false);
        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mFirebaseAuth.getCurrentUser() != null){
            transaction.addStack();
            transaction.replace(R.id.fragment_container,new MainAppFragment());
        }

        mStudentSignin = (Button) v.findViewById(R.id.student_signin);
        mStudentSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.add(R.id.fragment_container,new SigninFragment());
            }
        });

        mStudentRegister = (Button) v.findViewById(R.id.student_register);
        mStudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.add(R.id.fragment_container,new RegisterFragment());
            }
        });

        mAdmin = (Button) v.findViewById(R.id.admin);
        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.add(R.id.fragment_container,new AdminFragment());
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = context;
    }



}
