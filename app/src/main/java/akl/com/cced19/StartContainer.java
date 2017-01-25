package akl.com.cced19;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mohamed Akl on 1/25/2017.
 */

public class StartContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle onSavedInstanceState){
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.container_start);

        FragmentManager fragmentManager = getFragmentManager();

    }
}
