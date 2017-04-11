package question.exemple.nico.questionboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by nico on 4/11/17.
 */

public class AddQuestionActivity extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addquestion_activity);

        spinner = (Spinner) findViewById(R.id.addquestion_spinner);

        final String [] itemArray = {"Catégorie 1", "Catégorie 2","Catégorie 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,itemArray);

        spinner.setAdapter(adapter);


    }

    protected void createToast() {

    }
}
