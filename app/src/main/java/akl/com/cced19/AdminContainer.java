package akl.com.cced19;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mohamed Akl on 2/9/2017.
 */

public class AdminContainer extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container_admin);

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.admin_fragment_container);
        if(fragment == null){
            fragment = new AdminChoicesFragment();
            fragmentManager.beginTransaction().add(R.id.admin_fragment_container,fragment).commit();
        }

    }
}
