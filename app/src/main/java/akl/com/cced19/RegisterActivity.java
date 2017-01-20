package akl.com.cced19;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mIdTextView;
    private TextView mPasswordTextView;
    private EditText mRegisterCode;
    private Button mRegisterButton;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NumberFormatException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mProgressDialog = new ProgressDialog(this);
        mNameTextView = (TextView) findViewById(R.id.student_name);
        mEmailTextView = (TextView) findViewById(R.id.student_email);
        mIdTextView = (TextView) findViewById(R.id.student_id);
        mPasswordTextView = (TextView) findViewById(R.id.student_password);
        mRegisterButton = (Button) findViewById(R.id.student_register);
        mRegisterCode = (EditText) findViewById(R.id.student_code);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser(mNameTextView.getText().toString().trim(),mEmailTextView.getText().toString().trim(),mPasswordTextView.getText().toString().trim(),Integer.parseInt(mIdTextView.getText().toString().trim()),mRegisterCode.getText().toString().trim());
            }
        });
    }

    private void registerUser(String name,String email,String password,int id,String code){
        if(TextUtils.isEmpty(email)|| !isEmailValid(email)){
            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mIdTextView.getText().toString().trim())|| id > 5000)
        {
            Toast.makeText(getApplicationContext(),"Invalid ID",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(getApplicationContext(),"Invalid Name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(code) || !code.equals("wubba lubba dub dub")){
            Toast.makeText(getApplicationContext(),"Invalid Code",Toast.LENGTH_SHORT).show();
            return;
        }
        mProgressDialog.setMessage("Registering");
        mProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Successfully Registered!",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),SigninActivity.class));
                }
                else{
                    mProgressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_SHORT).show();
                }
            }
        });
        Student mStudent = new Student(id,name,email);
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}
