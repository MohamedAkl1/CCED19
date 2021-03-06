package akl.com.cced19;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    private TextView mEmailTextView;
    private TextView mPasswordTextview;
    private Button mSigninButton;

    private Student mStudent;
    FirebaseAuth mFirebaseAuth;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mFirebaseAuth = FirebaseAuth.getInstance();
        mEmailTextView = (TextView) findViewById(R.id.signin_email);
        mProgressDialog = new ProgressDialog(this);
        mPasswordTextview = (TextView) findViewById(R.id.signin_password);
        mSigninButton = (Button) findViewById(R.id.signin_button);
        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.setMessage("Signing in");
                mProgressDialog.show();
                mFirebaseAuth.signInWithEmailAndPassword(mEmailTextView.getText().toString().trim(),mPasswordTextview.getText().toString().trim()).addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if(task.isSuccessful()){
                            Intent intent = new Intent(getApplicationContext(),MainAppContainer.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            mProgressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Error Occured! please try again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
