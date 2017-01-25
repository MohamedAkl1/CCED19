package akl.com.cced19;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mohamed Akl on 1/25/2017.
 */

public class StartContainerActivity extends AppCompatActivity implements MainFragment.openingFragments,SigninFragment.signinButtonFragment,RegisterFragment.registerSendData,AdminFragment.sendAdminData{

    FragmentManager fragmentManager = getFragmentManager();
    ProgressDialog mProgressDialogSignin;
    ProgressDialog mProgressDialogRegister;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference mDatabaseReference;

    String adminCode;

    private String registerEmail;
    private String registerPassword;
    private int registerId;
    private String registerName;
    private String registerCode;

    private String signinEmail;
    private String signinPassword;

    @Override
    protected void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.container_start);

        mProgressDialogSignin = new ProgressDialog(getApplicationContext());
        mProgressDialogRegister = new ProgressDialog(getApplicationContext());
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        fragmentManager.beginTransaction().add(R.id.fragment_container,new AfterSigninActivity()).commit();
    }

    @Override
    public void signinFragment(){
        fragmentManager.beginTransaction().replace(R.id.fragment_container,new SigninFragment()).commit();
    }

    @Override
    public void registerFragment(){
        fragmentManager.beginTransaction().replace(R.id.fragment_container,new RegisterFragment()).commit();
    }

    @Override
    public void adminFragment(){
        fragmentManager.beginTransaction().replace(R.id.fragment_container,new AdminFragment()).commit();
    }

    @Override
    public void userSignedin(){
        startActivity(new Intent(getApplicationContext(),AfterSigninActivity.class));
    }

    @Override
    public void signinButton(){
        mProgressDialogSignin.setMessage("Signing in");
        mProgressDialogSignin.show();

        //check 'this' in addOnCompleteListener
        mFirebaseAuth.signInWithEmailAndPassword(signinEmail, signinPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mProgressDialogSignin.dismiss();
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),AfterSigninActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else{
                    mProgressDialogSignin.dismiss();
                    Toast.makeText(getApplicationContext(),"Error Occured! please try again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void getEmailPassword(String importedEmail,String importedPassword){
        this.signinEmail = importedEmail;
        this.signinPassword = importedPassword;

    }

    @Override
    public void registerButtonClicked(){
        registerUser(registerName,registerEmail,registerPassword,registerId,registerCode);
    }

    @Override
    public void getData(String name,String email,String password,int id,String code){
        this.registerName = name;
        this.registerEmail = email;
        this.registerPassword = password;
        this.registerId = id;
        this.registerCode = code;
    }

    @Override
    public void adminCode(String code) {
        this.adminCode = code;
    }

    @Override
    public void adminButtonClicked() {
        if(adminCode.equals("msh wubba lubba dub dub"))
        {
            startActivity(new Intent(getApplicationContext(),AdminAfterLoggingActivity.class));
        }
        else{
            Toast.makeText(getApplicationContext(),"invalid code",Toast.LENGTH_SHORT).show();
        }
    }

    //Methods

    private void registerUser(final String name, final String email, final String password, final int id, final String code){

        //checking all the user input
        if(TextUtils.isEmpty(email)|| !isEmailValid(email)){
            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(),"Invalid Password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(Integer.toString(id))|| id > 5000)
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

        mProgressDialogRegister.setMessage("Registering");
        mProgressDialogRegister.show();
        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mProgressDialogRegister.dismiss();

                    //Saving user Data on Firebase
                    Student mStudent = new Student(id,name,email);
                    FirebaseUser mUser = mFirebaseAuth.getCurrentUser();
                    mDatabaseReference.child(mUser.getUid()).setValue(mStudent);

                    Toast.makeText(getApplicationContext(),"Successfully Registered!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),SigninFragment.class));
                    finish();
                }
                else{
                    mProgressDialogRegister.dismiss();
                    Toast.makeText(getApplicationContext(),"Error Occured",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
