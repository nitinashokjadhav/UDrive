package nitin.com.u_drive;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Iterator;

public class BookFragment extends Fragment implements Bookings.OnFragmentInteractionListener,History.OnFragmentInteractionListener{
    DatabaseReference rRef;
    private TextView carName;
    private ImageView carImage;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
//        TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tablayout);
//        tabLayout.addTab(tabLayout.newTab().setText("Bookings"));
//        tabLayout.addTab(tabLayout.newTab().setText("History"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        rRef = FirebaseDatabase.getInstance().getReference("booking");
//
//        final ViewPager viewPager = (ViewPager)view.findViewById(R.id.pager);
//        final PageAdapter adapter = new PageAdapter(getChildFragmentManager(),tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
        final String uId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        carName=view.findViewById(R.id.carName);
        carImage=view.findViewById(R.id.carImage);
        rRef = FirebaseDatabase.getInstance().getReference("booking").child(uId);

        rRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 HashMap<String,String> data = new HashMap<String, String>();
                  data= (HashMap<String, String>) dataSnapshot.getValue();
                Log.e("data",""+data);
                DatabaseReference cRef = FirebaseDatabase.getInstance().getReference("cars").child(data.get("cID"));
                cRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Car car = dataSnapshot.getValue(Car.class);
                        carName.setText(car.getcName());
                        Picasso.with(getContext()).load(car.getcImageUrl()).centerCrop().fit().into(carImage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
