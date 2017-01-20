package akl.com.cced19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SigninActivity extends AppCompatActivity {

    private TextView mEmailTextView;
    private TextView mPasswordTextview;
    private Button mSigninButton;

    private Student mStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mEmailTextView = (TextView) findViewById(R.id.signin_email);
        mPasswordTextview = (TextView) findViewById(R.id.signin_password);
        mSigninButton = (Button) findViewById(R.id.signin_button);

        mSigninButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }
}
