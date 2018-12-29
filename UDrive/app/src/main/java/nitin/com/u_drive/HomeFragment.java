package nitin.com.u_drive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class HomeFragment extends Fragment {
    ViewFlipper viewFlipper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewFlipper=(ViewFlipper)view.findViewById(R.id.viewFlipper);
        int images[]={R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e};
        for(int i : images)
        {
            imageFlipper(i);
        }
        return view;
    }

    private void imageFlipper(int image)
    {
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(3000);
        Log.e("error2","in image flipper");
        viewFlipper.setInAnimation(getContext() ,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);
        Log.e("error3","in last");
    }

}
