package akl.com.cced19;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
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
    private FragmentManager mFragmentManager;
    private FirebaseAuth mFirebaseAuth;
    Context mSigninButton;
    Context mRegisterButton;
    Context mAdminButton;
    View.OnClickListener mOnClickListener;

    public interface OnClickListener1{
        public void onSigninPressed();
        public void onRegisterPressed();
        public void onAdminPressed();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.mRegisterButton = context;
        this.mAdminButton = context;
        this.mSigninButton = context;
    }

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
                    ((OnClickListener1) mSigninButton).onSigninPressed();
            }
        });

        mStudentRegister = (Button) v.findViewById(R.id.student_register);
        mStudentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OnClickListener1) mRegisterButton).onRegisterPressed();
            }
        });

        mAdmin = (Button) v.findViewById(R.id.admin);
        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OnClickListener1) mAdminButton).onAdminPressed();
            }
        });
        return v;
    }
}
