package akl.com.cced19;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

public class SigninFragment extends Fragment {

    private TextView mEmailTextView;
    private TextView mPasswordTextview;
    private Button mSigninButton;

    private Student mStudent;
    FirebaseAuth mFirebaseAuth;
    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_signin,container,false);

        final Transaction transaction = new Transaction();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mEmailTextView = (TextView) v.findViewById(R.id.signin_email);
        mProgressDialog = new ProgressDialog(getActivity());
        mPasswordTextview = (TextView) v.findViewById(R.id.signin_password);
        mSigninButton = (Button) v.findViewById(R.id.signin_button);
        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.setMessage("Signing in");
                mProgressDialog.show();
                mFirebaseAuth.signInWithEmailAndPassword(mEmailTextView.getText().toString().trim(),mPasswordTextview.getText().toString().trim()).addOnCompleteListener((Executor) SigninFragment.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if(task.isSuccessful()){
                            transaction.addStack();
                            transaction.replace(R.id.fragment_container,new MainAppFragment());
                        }
                        else{
                            mProgressDialog.dismiss();
                            Toast.makeText(getActivity(),"Error Occured! please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return v;
    }
}
