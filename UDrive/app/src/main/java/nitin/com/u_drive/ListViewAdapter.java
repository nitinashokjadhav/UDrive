package nitin.com.u_drive;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    SearchFragment mContext;
    LayoutInflater inflater;
    private ArrayList<Place> arraylist;

    public ListViewAdapter(Context context ,ArrayList<Place> placeArrayList) {

       inflater = LayoutInflater.from(context);
       arraylist= new ArrayList<Place>();
       arraylist.addAll(SearchFragment.placeArrayList);
    }

    public class ViewHolder {
        TextView name;
       TextView id;
    }

    @Override
    public int getCount() {
        return SearchFragment.placeArrayList.size();
    }

    @Override
    public Place getItem(int position) {
        return SearchFragment.placeArrayList.get(position);
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
            holder.id   = (TextView) view.findViewById(R.id.id);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(SearchFragment.placeArrayList.get(position).getpName());
        holder.id.setText(SearchFragment.placeIdArrayList.get(position).getpId());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        SearchFragment.placeArrayList.clear();
        if (charText.length() == 0) {
            SearchFragment.placeArrayList.addAll(arraylist);
            Log.i("FILTER","Not selected");
        } else {
            for (Place wp : arraylist) {
                if (wp.getpName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    SearchFragment.placeArrayList.add(wp);
                    Log.i("FILTER",charText);
                }
            }
        }
        notifyDataSetChanged();
    }

}