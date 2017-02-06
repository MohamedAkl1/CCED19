package akl.com.cced19;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    private EditText adminEditText;
    private Button adminButton;
    private String adminCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adminEditText = (EditText) findViewById(R.id.admin_code);
        adminCode = adminEditText.getText().toString().trim().toLowerCase();

        adminButton = (Button) findViewById(R.id.admin_button);
        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adminCode.equals("msh wubba lubba dub dub")){
                    //do something
                }else{
                    Toast.makeText(getApplicationContext(),"invalid code",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
