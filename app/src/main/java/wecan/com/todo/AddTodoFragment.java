package wecan.com.todo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Todo Fragment
 */

public class AddTodoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Spinner spinner;
    EditText edTitle, edDetails;
    Button btDate, btTime, btSave;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_add_todo, container, false);
        spinner = (Spinner) view.findViewById(R.id.spPriority);
        edTitle = (EditText) view.findViewById(R.id.edTitle);
        edDetails = (EditText) view.findViewById(R.id.edDetails);
        btDate = (Button) view.findViewById(R.id.btDate);
        btTime = (Button) view.findViewById(R.id.btTime);
        btSave = (Button) view.findViewById(R.id.btSave);
        btDate.setOnClickListener(this);

        List<TagEnum> tagEnum = new ArrayList<>();
        tagEnum.add(TagEnum.BLACK);
        tagEnum.add(TagEnum.BLUE);
        tagEnum.add(TagEnum.RED);
        tagEnum.add(TagEnum.GREEN);
        tagEnum.add(TagEnum.YELLOW);
        SpinnerAdapter adapter = new SpinnerAdapter(getActivity(),R.layout.spinner_item, tagEnum);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btSave:

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
