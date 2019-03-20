package nitin.com.u_drive;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView  = findViewById(R.id.btmNav);
        disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        String to = getIntent().getStringExtra("from");
        Log.e("to",""+to);
        if(to=="CAR")
        {
            Fragment fragment= new BookFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
        }

        mAuth = FirebaseAuth.getInstance();
        Log.e("MainActivity","Inside MainActivity");
        //check whether the user is already registered and navigate according to their roles
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
                //    mRef = FirebaseDatabase.getInstance().getReference(user.getProviders().toString());
//                    Log.e("Ref",""+mRef);
                    String email = user.getEmail();
                    Log.e("email", email);
                    String typ = null;
                    DbHelper dbHelper = new DbHelper(getApplicationContext());
                    Cursor cursor = dbHelper.getUser(email);
                    if (cursor != null && cursor.moveToFirst())
                    {
                        if (cursor != null && cursor.moveToFirst()) {
                            typ = cursor.getString(2);
                            Log.e("User", typ);
                            cursor.close();
                        }

                        if (!typ.isEmpty()) {
                            if (typ.contains("customer")) {
                                Log.e("check", "customer");
                                Log.e("MainActivity","Inside MainActivity");
                               Fragment fragment = new HomeFragment();
                               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
                            } else if (typ.contains("owner")) {
                                Log.e("check", "owner");
                                startActivity(new Intent(getApplicationContext(), CarOnwer.class));
                                finish();
                            } else {
                                Log.e("check", "fail");
                            }
                        }

                    }

                }
            }
        };




        //Changing the interface after registration
        Intent intent = getIntent();
        String type= intent.getStringExtra("user_type");

        if(type!=null) {


            switch (type) {
                case "customer":
                    Log.e("main", type);
                    Fragment fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    break;
                case "owner":
                    Log.e("main", type);
                    startActivity(new Intent(MainActivity.this, CarOnwer.class));
                    break;
                default:
                     type = "empty";
                    Log.e("main", type);
            }
        }
        else {

            Fragment fragment = new HomeFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch(item.getItemId())
            {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_search:
                    selectedFragment = new SearchFragment();
                    break;
                case R.id.nav_food:
                    selectedFragment = new FoodFragment();
                    break;
                case R.id.nav_book:
                    selectedFragment = new BookFragment();
                    break;
                case R.id.nav_account:
                    selectedFragment = new AccountFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("Luan", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("Luan", "Unable to change value of shift mode");
        }
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}
