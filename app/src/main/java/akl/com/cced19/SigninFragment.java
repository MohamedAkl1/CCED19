package akl.com.cced19;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SigninFragment extends Fragment {

    private TextView mEmailTextView;
    private TextView mPasswordTextview;
    private Button mSigninButton;

    private Student mStudent;
    FirebaseAuth mFirebaseAuth;

    Context signinButton;
    Context passEmailPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signin,container,false);

        mEmailTextView = (TextView) v.findViewById(R.id.signin_email);
        String email = mEmailTextView.getText().toString().trim();
        //check after run
        mPasswordTextview = (TextView) v.findViewById(R.id.signin_password);
        String password = mPasswordTextview.getText().toString().trim();
        ((signinButtonFragment) passEmailPassword).getEmailPassword(email,password);
        mSigninButton = (Button) v.findViewById(R.id.signin_button);
        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((signinButtonFragment) signinButton).signinButton();
            }
        });
        
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof signinButtonFragment)
        {
            this.signinButton = context;
            this.passEmailPassword = context;
        }else
            throw new RuntimeException("interface not implemented");
    }

    public interface signinButtonFragment{
        void signinButton();
        void getEmailPassword(String email,String password);
    }

}
