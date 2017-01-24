package akl.com.cced19;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mohamed Akl on 1/24/2017.
 */

public class MainAppContainer extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.container);

        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new MainAppFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }
    }
}
