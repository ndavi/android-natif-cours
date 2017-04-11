package question.exemple.nico.questionboard;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, View.OnLongClickListener, AdapterView.OnItemClickListener {

    Spinner spinner;
    Button button;
    Switch uiSwitch;
    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner3);
        button = (Button) findViewById(R.id.button2);
        uiSwitch = (Switch) findViewById(R.id.switch2);
        gridView = (GridView) findViewById(R.id.gridview);

        button.setOnClickListener(this);
        uiSwitch.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
        gridView.setOnLongClickListener(this);

        final String [] itemArray = {"Catégorie 1", "Catégorie 2","Catégorie 3"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,itemArray);

        final String [] gridViewArray = {"Catégorie 1", "Catégorie 2","Catégorie 3"};
        ArrayAdapter <String> gridAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,itemArray);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        gridView.setAdapter(gridAdapter);



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case  R.id.button2: {
                Intent intentMain = new Intent(MainActivity.this ,
                        AddQuestionActivity.class);
                MainActivity.this.startActivity(intentMain);
                break;
            }

            case R.id.switch2: {
                if(uiSwitch.isChecked()) {
                    uiSwitch.setText("Question / Réponse");
                } else {
                    uiSwitch.setText("Question");
                }
                break;
            }

            case R.id.gridview: {

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Context context = getApplicationContext();
        CharSequence text = spinner.getSelectedItem().toString();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
