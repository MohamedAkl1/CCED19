package akl.com.cced19;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AdminFragment extends Fragment {

    EditText madminEditText;
    Button madminButton;

    String madminCode;

    Context adminSignCode;
    Context adminButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_admin,container,false);

        madminEditText = (EditText) v.findViewById(R.id.admin_code_edittext);
        madminCode = madminEditText.getText().toString().trim();
        ((sendAdminData) adminSignCode).adminCode(madminCode);
        madminButton = (Button) v.findViewById(R.id.admin_button);
        madminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((sendAdminData) adminButton).adminButtonClicked();
            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof sendAdminData){
            this.adminSignCode = context;
            this.adminButton = context;
        }
    }

    public interface sendAdminData{
        void adminButtonClicked();
        void adminCode(String code);
    }
}
