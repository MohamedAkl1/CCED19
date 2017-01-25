package akl.com.cced19;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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
    Context userSignedin;

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
            ((openingFragments) userSignedin).userSignedin();
        }

        mStudentSignin = (Button) v.findViewById(R.id.student_signin);
        mStudentSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((openingFragments) signin).signinFragment();
            }
        });

        mStudentRegister = (Button) v.findViewById(R.id.student_register);
        mStudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((openingFragments) register).registerFragment();
            }
        });

        mAdmin = (Button) v.findViewById(R.id.admin);
        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((openingFragments) admin).adminFragment();
            }
        });

        return v;
    }

    public interface openingFragments{
        void signinFragment();
        void registerFragment();
        void adminFragment();
        void userSignedin();

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        //check if its correct on running
        if(context instanceof openingFragments){
            this.signin = context;
            this.register = context;
            this.admin = context;
            this.userSignedin = context;
        }else
            throw new RuntimeException("interface not implemented");
    }
}
