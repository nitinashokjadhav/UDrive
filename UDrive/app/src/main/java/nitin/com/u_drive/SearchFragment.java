package nitin.com.u_drive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    private String[] moviewList;
    public static ArrayList<MovieNames> movieNamesArrayList = new ArrayList<MovieNames>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_search, container, false);
        // Generate sample data

        moviewList = new String[]{"titwala ganesh mandir","Talao Pali","Thane creek","Nariman point","Gateway of India","Marine Lines","Sidhi Vinayak mandir","Rajeev Gandhi national park"
        ,"Mahalaskmi","Juhu beach"};

        // Locate the ListView in listview_main.xml
        list = (ListView) layout.findViewById(R.id.listview);

        movieNamesArrayList = new ArrayList<>();

        for (int i = 0; i < moviewList.length; i++) {
            MovieNames movieNames = new MovieNames(moviewList[i]);
            // Binds all strings into an array
            movieNamesArrayList.add(movieNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(getActivity(),movieNamesArrayList);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) layout.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(SearchFragment.this, movieNamesArrayList.get(position).getAnimalName(), Toast.LENGTH_SHORT).show();
            }
        });
        return layout;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        if(movieNamesArrayList.contains(query))
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
