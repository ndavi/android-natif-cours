package question.exemple.nico.questionboard;

/**
 * Created by nico on 5/2/17.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class QuestionDAO {

    // Champs de la base de données
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_QUESTION, MySQLiteHelper.COLUMN_RESPONSE, MySQLiteHelper.COLUMN_CATEGORY };

    public static final String[] allCategories = {"Informatique", "Santé","Politique"};

    public QuestionDAO(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Question createQuestion(String question, String response, String category) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_QUESTION, question);
        values.put(MySQLiteHelper.COLUMN_RESPONSE, response);
        values.put(MySQLiteHelper.COLUMN_CATEGORY, category);
        long insertId = database.insert(MySQLiteHelper.TABLE_QUESTIONBOARD, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_QUESTIONBOARD,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Question questionBoard = cursorToComment(cursor);
        cursor.close();
        return questionBoard;
    }

    public void deleteQuestion(Question comment) {
        long id = comment.getId();
        System.out.println("Question deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_QUESTIONBOARD, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Question> getAllQuestions() {
        List<Question> comments = new ArrayList<Question>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_QUESTIONBOARD,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Question comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        List<Question> comments = new ArrayList<Question>();

        String selection = "category =?";
        String[] selectionArgs = {category};
        Cursor cursor = database.query(MySQLiteHelper.TABLE_QUESTIONBOARD,
                allColumns, selection, selectionArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Question comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        cursor.close();
        return comments;
    }

    private Question cursorToComment(Cursor cursor) {
        Question question = new Question();
        question.setId(cursor.getLong(0));
        question.setQuestion(cursor.getString(1));
        question.setResponse(cursor.getString(2));
        question.setCategory(cursor.getString(3));
        return question;
    }
}
