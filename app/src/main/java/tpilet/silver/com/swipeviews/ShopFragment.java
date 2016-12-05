package tpilet.silver.com.swipeviews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by silver.hein on 05.12.2016.
 */

public class ShopFragment extends Fragment {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView shopList;
    Button shopButton;
    EditText shopText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.shop_layout, container, false);

        shopButton = (Button) rootView.findViewById(R.id.shopButton);
        shopText = (EditText) rootView.findViewById(R.id.shopText);
        shopList = (ListView) rootView.findViewById(R.id.shopList);

        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        shopList.setAdapter(itemsAdapter);

        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemText = shopText.getText().toString();
                itemsAdapter.add(itemText);
                shopText.setText("");
                writeItems();
            }
        });

        shopList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, int pos, long id) {
                //remove the item within array at position
                items.remove(pos);
                //refresh the adapter
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                //return true consumes the long click event(marks it handled)
                return true;
            }
        });

        return rootView;
    }

    private void readItems() {
        File filesDir = getActivity().getFilesDir();
        File todoFile = new File(filesDir, "shop.txt");
        try {
            items = new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }

    private void writeItems() {
        File filesDir = getActivity().getFilesDir();
        File todoFile = new File(filesDir, "shop.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
