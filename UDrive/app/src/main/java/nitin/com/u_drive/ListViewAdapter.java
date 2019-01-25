package nitin.com.u_drive;
import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    SearchFragment mContext;
    LayoutInflater inflater;
    private ArrayList<MovieNames> arraylist;

    public ListViewAdapter(Context context ,ArrayList<MovieNames> movieNamesArrayList) {

       inflater = LayoutInflater.from(context);
       arraylist= new ArrayList<MovieNames>();
       arraylist.addAll(SearchFragment.movieNamesArrayList);
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return SearchFragment.movieNamesArrayList.size();
    }

    @Override
    public MovieNames getItem(int position) {
        return SearchFragment.movieNamesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item,null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(SearchFragment.movieNamesArrayList.get(position).getAnimalName());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        SearchFragment.movieNamesArrayList.clear();
        if (charText.length() == 0) {
            SearchFragment.movieNamesArrayList.addAll(arraylist);
            Log.i("FILTER","Not selected");
        } else {
            for (MovieNames wp : arraylist) {
                if (wp.getAnimalName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    SearchFragment.movieNamesArrayList.add(wp);
                    Log.i("FILTER","any selected");
                }
            }
        }
        Log.i("FILTER","Nothing");
        notifyDataSetChanged();
    }

}