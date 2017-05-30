package question.exemple.nico.questionboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by nico on 4/11/17.
 */

public class AddQuestionActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner;
    Button creation;
    EditText textQuestion;
    EditText textResponse;
    QuestionBoardDAO datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addquestion_activity);

        spinner = (Spinner) findViewById(R.id.createCategory);
        creation = (Button) findViewById(R.id.creationBtn);
        creation.setOnClickListener(this);
        textQuestion = (EditText) findViewById(R.id.createQuestion);
        textResponse = (EditText) findViewById(R.id.createResponse);

        datasource = new QuestionBoardDAO(this);
        datasource.open();

        final String [] itemArray = QuestionBoardDAO.allCategories;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,itemArray);



        spinner.setAdapter(adapter);
    }

    protected void createToast() {

    }

    @Override
    public void onClick(View v) {
        datasource.createQuestion(textQuestion.getText().toString(),
                textResponse.getText().toString(), spinner.getSelectedItem().toString());
        finish();

    }
}
