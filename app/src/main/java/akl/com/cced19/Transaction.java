package akl.com.cced19;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Mohamed Akl on 1/24/2017.
 */

public class Transaction extends Fragment {

    FragmentManager mFragmentManager = getFragmentManager();
    FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

    public void add(int id,Fragment fragment){
        mFragmentTransaction.add(id,fragment).commit();
    }
    
    public void addStack(){
        mFragmentTransaction.addToBackStack(null).commit();
    }

    public void replace(int id,Fragment fragment){
        mFragmentTransaction.replace(id,fragment).commit();
    }

    public void remove(Fragment fragment){
        mFragmentTransaction.remove(fragment).commit();
    }
}
