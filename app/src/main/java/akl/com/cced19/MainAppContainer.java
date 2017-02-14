package akl.com.cced19;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mohamed Akl on 1/24/2017.
 */

public class MainAppContainer extends AppCompatActivity {

    public static Student getmStudent() {
        return mStudent;
    }

    private static Student mStudent = new Student();

    FirebaseAuth mAuth;
    DatabaseReference mReference;


    @Override
    protected void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        setContentView(R.layout.main_app_container);

        mAuth = FirebaseAuth.getInstance();
        mReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser mUser = mAuth.getCurrentUser();
        String id = mUser.getUid();
        mReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    mStudent = dataSnapshot.getValue(Student.class);

                    if (mStudent.getTable() == null) {
                        startActivity(new Intent(getApplicationContext(), NewTableActivity.class));
                        finish();
                    } else {
                        FragmentManager fm = getFragmentManager();
                        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
                        if (fragment == null) {
                            fragment = new MainAppFragment();
                            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "error reading from database", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


