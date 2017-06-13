package question.exemple.nico.questionboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by nico on 4/11/17.
 */

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner;
    Button creation;
    EditText textQuestion;
    EditText textResponse;
    QuestionDAO DAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addquestion_activity);

        spinner = (Spinner) findViewById(R.id.createCategory);
        creation = (Button) findViewById(R.id.creationBtn);
        creation.setOnClickListener(this);
        textQuestion = (EditText) findViewById(R.id.createQuestion);
        textResponse = (EditText) findViewById(R.id.createResponse);

        DAO = new QuestionDAO(this);
        DAO.open();

        final String [] itemArray = QuestionDAO.allCategories;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,itemArray);



        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        DAO.createQuestion(textQuestion.getText().toString(),
                textResponse.getText().toString(), spinner.getSelectedItem().toString());

        Intent intentMain = new Intent(QuestionActivity.this ,
                MainActivity.class);
        QuestionActivity.this.startActivity(intentMain);

    }
}
