package com.afzaalahmadzeeshan.mylocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EditNumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_numbers);

        Button button = (Button) findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.numbers_edittext);
                String[] numbers = text.getText().toString().split(",");
                ContentManager manager = new ContentManager(getApplicationContext());
                for (String number : numbers) {
                    manager.addNumber("", number, true);
                }
            }
        });
        calculate();
    }

    public void calculate() {
        ArrayList<Number> numbers = new ContentManager(this).getNumbers();

        if(numbers != null && numbers.size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (Number number : numbers) {
                builder.append(number.getNumber()).append(", \n");
            }
            String value = builder.toString();

            EditText text = (EditText)findViewById(R.id.numbers_edittext);
            text.setText(value);

            TextView textView = (TextView) findViewById(R.id.no_number_selected);
            if(numbers.size() == 1) {
                textView.setText("1 contact is added.");
            } else {
                textView.setText(numbers.size() + " contacts are added.");
            }
        } else {
            TextView textView = (TextView) findViewById(R.id.no_number_selected);
            textView.setText(R.string.no_contacts_added);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    // Other classes
    private class NumbersListAdapter extends BaseAdapter {
        List<Number> numbers;

        public NumbersListAdapter(ArrayList<Number> numberList) {
            this.numbers = numberList;
        }

        @Override
        public int getCount() {
            return numbers.size();
        }

        @Override
        public Object getItem(int position) {
            return numbers.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.numbers_list_layout, null);
            CheckBox enabled = (CheckBox)convertView.findViewById(R.id.enabled);
            TextView title = (TextView)convertView.findViewById(R.id.name);

            String titleStr = numbers.get(position).getName() + " (" + numbers.get(position).getNumber() + ")";
            title.setText(titleStr);
            enabled.setChecked(numbers.get(position).isActive());
            return convertView;
        }
    }
}
