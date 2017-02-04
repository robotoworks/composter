package com.robotoworks.composter_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.robotoworks.composter.RecyclerViewAdapter;
import com.robotoworks.composter.dataset.ListRecyclerDataSet;
import com.robotoworks.composter.mediators.BinderManager;
import com.robotoworks.composter_example.adapter.ExampleBinderRegistrar;
import com.robotoworks.composter_example.model.ImageListItem;
import com.robotoworks.composter_example.model.ListItem;
import com.robotoworks.composter_example.model.StringListItem;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private RecyclerViewAdapter<ListItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter<>(new BinderManager(), new ExampleBinderRegistrar());
        recyclerView.setAdapter(adapter);
        adapter.setSource(buildExampleDataSource());
        adapter.notifyDataSetChanged();
    }

    private ListRecyclerDataSet<ListItem> buildExampleDataSource() {

        ArrayList<ListItem> listItems = new ArrayList<>();
        listItems.add(new StringListItem("This is a text cell: 1"));
        listItems.add(new StringListItem("This is a text cell: 2"));
        listItems.add(new StringListItem("This is a text cell: 3"));
        listItems.add(new StringListItem("This is a text cell: 4"));
        listItems.add(new ImageListItem("http://www.camsam.com/wp-content/uploads/2015/04/Android_Logo_2014.svg_.png"));
        listItems.add(new StringListItem("This is a text cell: 5"));
        listItems.add(new StringListItem("This is a text cell: 6"));
        listItems.add(new StringListItem("This is a text cell: 7"));
        listItems.add(new ImageListItem("https://c1.staticflickr.com/5/4077/4903897300_c6eef5b115_b.jpg"));
        listItems.add(new StringListItem("This is a text cell: 8"));
        listItems.add(new StringListItem("This is a text cell: 9"));
        listItems.add(new StringListItem("This is a text cell: 10"));
        listItems.add(new StringListItem("This is a text cell: 11"));
        listItems.add(new StringListItem("This is a text cell: 12"));
        listItems.add(new StringListItem("This is a text cell: 13"));

        return new ListRecyclerDataSet<ListItem>(listItems) {
            @Override
            public int getItemViewType(int position) {

                return getItem(position).getViewType();
            }
        };
    }
}
