package question.exemple.nico.questionboard;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, TextWatcher {

    Spinner spinner;
    Button button;
    Switch uiSwitch;
    GridView gridView;
    EditText editText;
    private QuestionBoardDAO datasource;
    private List<QuestionBoard> questionsList;
    private int selectedItemToDeleted = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        questionsList = new ArrayList<>();
        datasource = new QuestionBoardDAO(this);
        datasource.open();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner3);
        button = (Button) findViewById(R.id.button2);
        uiSwitch = (Switch) findViewById(R.id.switch2);
        gridView = (GridView) findViewById(R.id.gridview);
        editText = (EditText) findViewById(R.id.rechercheView);

        button.setOnClickListener(this);
        uiSwitch.setOnClickListener(this);
        gridView.setOnItemClickListener(this);
        gridView.setOnItemLongClickListener(this);
        editText.addTextChangedListener(this);

        final String [] itemArray = QuestionBoardDAO.allCategories;
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,itemArray);


        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        gridView.setAdapter(arrayAdapterFromCategory(uiSwitch.isChecked(),""));
    }

    @Override
    protected void onResume() {
        super.onResume();
        gridView.setAdapter(arrayAdapterFromCategory(uiSwitch.isChecked(),""));
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
                gridView.setAdapter(arrayAdapterFromCategory(uiSwitch.isChecked(),""));
                break;
            }

            case R.id.gridview: {

            }
        }
    }

    private ArrayAdapter arrayAdapterFromCategory(boolean isResponse, String search) {
        CharSequence text = spinner.getSelectedItem().toString();
        List<QuestionBoard> questions = datasource.getAllQuestionsByCategory((String)text);
        questionsList.clear();
        List<String> gridViewArray = new ArrayList<>();
        for (QuestionBoard question : questions) {
            if(search != "") {
                if(!question.getQuestion().contains(search)) {
                    continue;
                }
            }
            if(!isResponse) {
                gridViewArray.add(question.getQuestion());
            } else {
                gridViewArray.add(question.getQuestion() + " // " + question.getResponse());
            }
            questionsList.add(question);
        }

        ArrayAdapter <String> gridAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,gridViewArray);
        return gridAdapter;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Context context = getApplicationContext();
        CharSequence text = spinner.getSelectedItem().toString();
        int duration = Toast.LENGTH_SHORT;

        gridView.setAdapter(arrayAdapterFromCategory(uiSwitch.isChecked(),""));

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Context context = getApplicationContext();
        AppCompatTextView textView = (AppCompatTextView)view;
        CharSequence text = textView.getText();
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setMessage("Voulez vous supprimer cette question ?").setPositiveButton("Oui", dialogClickListener)
                .setNegativeButton("Non", dialogClickListener).show();
        selectedItemToDeleted = position;
        return true;
    }

    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    QuestionBoard question = questionsList.get(selectedItemToDeleted);
                    datasource.deleteQuestion(question);
                    gridView.setAdapter(arrayAdapterFromCategory(uiSwitch.isChecked(),""));
                    break;
            }
        }
    };

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        gridView.setAdapter(arrayAdapterFromCategory(uiSwitch.isChecked(),editText.getText().toString()));
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
