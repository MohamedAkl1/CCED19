package akl.com.cced19;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mohamed Akl on 1/25/2017.
 */

public class AppContainer extends AppCompatActivity implements MainActivity.OnClickListener1{

    @Override
    protected void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.container);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragment_container,new MainActivity()).commit();
    }

    @Override
    public void onSigninPressed(){
        startActivity(new Intent(getApplicationContext(),SigninActivity.class));
    }

    @Override
    public void onRegisterPressed(){
        startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
    }
    @Override
    public void onAdminPressed(){
        startActivity(new Intent(getApplicationContext(),AdminActivity.class));
    }
}
