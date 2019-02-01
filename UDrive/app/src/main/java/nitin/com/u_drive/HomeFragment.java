package nitin.com.u_drive;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements View.OnClickListener {
   Button button1,button2,button3,button4,button5,button6,button7,button8;
   public Intent intent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        button1 =   (Button)view.findViewById(R.id.btn1);
        button2 =   (Button)view.findViewById(R.id.btn2);
        button3 =   (Button)view.findViewById(R.id.btn3);
        button4 =   (Button)view.findViewById(R.id.btn4);
        button5 =   (Button)view.findViewById(R.id.btn5);
        button6 =   (Button)view.findViewById(R.id.btn6);
        button7 =   (Button)view.findViewById(R.id.btn7);
        button8 =   (Button)view.findViewById(R.id.btn8);
        intent = new Intent(getContext(),Places.class);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn1:
                intent.putExtra("id","1");
                startActivity(intent);
                break;
            case R.id.btn2:
                intent.putExtra("id","2");
                startActivity(intent);
                break;
            case R.id.btn3:
                intent.putExtra("id","3");
                startActivity(intent);
                break;
            case R.id.btn4:
                intent.putExtra("id","4");
                startActivity(intent);
                break;
            case R.id.btn5:
                intent.putExtra("id","5");
                startActivity(intent);
                break;
            case R.id.btn6:
                intent.putExtra("id","6");
                startActivity(intent);
                break;
            case R.id.btn7:
                intent.putExtra("id","7");
                startActivity(intent);
                break;
            case R.id.btn8:
                intent.putExtra("id","8");
                startActivity(intent);
                break;
            default:

        }

    }
}
