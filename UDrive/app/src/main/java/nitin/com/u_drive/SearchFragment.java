package nitin.com.u_drive;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener  {

    // Declare Variables
    private ListView list;
    private ListViewAdapter adapter;
    private SearchView editsearch;
    private String[] placeList;
    public static ArrayList<Place> placeArrayList = new ArrayList<Place>();
    private String[] pIds;
    public static ArrayList<Place> placeIdArrayList = new ArrayList<Place>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);

        // Generate sample data
        String pl="";
        String ids="";
        Cursor cursor;
        DbHelper dbHelper = new DbHelper(getContext());
        cursor=dbHelper.getAllPlace();
        cursor.moveToFirst();

        int j=0;
        do
        {
            pl  +=  cursor.getString(1)+"_";
            ids +=  cursor.getInt(0)+"_";
            Log.i("Inside for", cursor.getString(0));
            j++;
      }while (cursor.moveToNext());

//      moviewList = new String[]{"titwala ganesh mandir","Talao Pali","Thane creek","Nariman point","Gateway of India","Marine Lines","Sidhi Vinayak mandir","Rajeev Gandhi national park"
//        ,"Mahalaskmi","Juhu beach"};


        placeList  = pl.split("_");
        pIds        = ids.split("_");
        // Locate the ListView in listview_main.xml
        list        = (ListView) layout.findViewById(R.id.listview);

        placeArrayList      = new ArrayList<>();
        placeIdArrayList    = new ArrayList<>();

        for (int i = 0; i < placeList.length; i++)
        {
            Place place = new Place(placeList[i]);
            Place place1= new Place(0,pIds[i]);
            // Binds all strings into an array
            placeArrayList.add(place);
            placeIdArrayList.add(place1);
            Log.i("search ", String.valueOf(place));
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getActivity(), placeArrayList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) layout.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), placeIdArrayList.get(position).getpId(), Toast.LENGTH_SHORT).show();
                String pName = placeIdArrayList.get(position).getpId();
                startActivity(new Intent(getContext(),Places.class).putExtra("id",pName));
            }
        });
        return layout;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        if(placeArrayList.contains(query))
        {
            adapter.filter(query);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
