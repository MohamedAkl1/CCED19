package akl.com.cced19;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterFragment extends Fragment {

    private TextView mNameTextView;
    private TextView mEmailTextView;
    private TextView mIdTextView;
    private TextView mPasswordTextView;
    private EditText mRegisterCode;
    private Button mRegisterButton;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String name;
    String email;
    String code;
    String password;
    int id;

    Context registerButton;
    Context passData;



    @Override
    public void onCreate(Bundle savedInstanceState) throws NumberFormatException {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register,container,false);

        mNameTextView = (TextView) v.findViewById(R.id.student_name);
        name = mNameTextView.getText().toString().trim();
        mEmailTextView = (TextView) v.findViewById(R.id.student_email);
        email = mEmailTextView.getText().toString().trim();
        mIdTextView = (TextView) v.findViewById(R.id.student_id);
        id = Integer.parseInt(mIdTextView.getText().toString().trim());
        mPasswordTextView = (TextView) v.findViewById(R.id.student_password);
        password = mPasswordTextView.getText().toString().trim();
        mRegisterCode = (EditText) v.findViewById(R.id.student_code);
        code = mRegisterCode.getText().toString().trim();
        ((registerSendData) passData).getData(name,email,password,id,code);
        mRegisterButton = (Button) v.findViewById(R.id.student_register);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((registerSendData) registerButton).registerButtonClicked();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof registerSendData){
            this.registerButton = context;
            this.passData = context;
        }
    }

    public interface registerSendData{
        void registerButtonClicked();
        void getData(String registerName,String registerEmail,String registerPassword,int registerId,String registerCode);
    }
}
