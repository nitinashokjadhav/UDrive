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
import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView  = findViewById(R.id.btmNav);
        disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        mAuth = FirebaseAuth.getInstance();

        //check whether the user is already registered and navigate according to their roles
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null)
                {
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
                                Fragment fragment = new HomeFragment();
                                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                                finish();
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
        String user= intent.getStringExtra("user_type");

        if(user!=null) {
            switch (user) {
                case "customer":
                    Log.e("main", user);
                    Fragment fragment = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    break;
                case "owner":
                    Log.e("main", user);
                    startActivity(new Intent(MainActivity.this, CarOnwer.class));
                    break;
                default:
                     user = "empty";
                    Log.e("main", user);
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
