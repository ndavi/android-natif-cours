package question.exemple.nico.questionboard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_QUESTIONBOARD = "QuestionBoard";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_RESPONSE = "response";
    public static final String COLUMN_CATEGORY = "category";


    private static final String DATABASE_NAME = "QuestionBoard.db";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_QUESTIONBOARD + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_QUESTION
            + " text not null, " + COLUMN_RESPONSE
            + " text not null, " + COLUMN_CATEGORY
            + " text not null);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONBOARD);
        onCreate(db);
    }
}
