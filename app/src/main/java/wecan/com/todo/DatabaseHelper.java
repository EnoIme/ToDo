package wecan.com.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Database helper for all database operations
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "todo.db";

    private static final String CREATE_SAVED_TODOS =
            "CREATE TABLE " + ToDoEntryContract.ToDoEntry.TABLE_NAME + " (" +
                    ToDoEntryContract.ToDoEntry._ID + " INTEGER PRIMARY KEY," +
                    ToDoEntryContract.COLUMN_TITLE + " TEXT" +
                    ToDoEntryContract.COLUMN_DETAILS + " TEXT" +
                    ToDoEntryContract.COLUMN_PCOLOR_NAME + " TEXT"+
                    ToDoEntryContract.COLUMN_TIME + " TEXT" +
                    ToDoEntryContract.COLUMN_DATE +
                    " )";

    private static final String CREATE_DELETED_TODOS =
            "CREATE TABLE " + ToDoEntryContract.ToDoDelete.TABLE_NAME + " (" +
                    ToDoEntryContract.ToDoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    ToDoEntryContract.COLUMN_TITLE + " TEXT" +
                    ToDoEntryContract.COLUMN_DETAILS + " TEXT" +
                    ToDoEntryContract.COLUMN_PCOLOR_NAME + " TEXT"+
                    ToDoEntryContract.COLUMN_TIME + " TEXT" +
                    ToDoEntryContract.COLUMN_DATE +
            " )";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SAVED_TODOS);
        sqLiteDatabase.execSQL(CREATE_DELETED_TODOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    private void saveToDoDetails(ToDo toDo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ToDoEntryContract.COLUMN_TITLE, toDo.getTitle());
        contentValues.put(ToDoEntryContract.COLUMN_DETAILS, toDo.getDetails());
        contentValues.put(ToDoEntryContract.COLUMN_PCOLOR_NAME, toDo.getPriorityColor());
        contentValues.put(ToDoEntryContract.COLUMN_DATE, toDo.getDate());
        contentValues.put(ToDoEntryContract.COLUMN_TIME, toDo.getTime());
        sqLiteDatabase.insert(ToDoEntryContract.ToDoEntry.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    private ToDo getToDo(int id){
        ToDo toDo = new ToDo();

        return toDo;
    }

    private void updateToDo(ToDo toDo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ToDoEntryContract.COLUMN_TITLE, toDo.getTitle());
    }

    private List<RefsSignUps> getRefSignUp(int refId){
        List<RefsSignUps> refsSignUpsList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT first_name, last_name, store_name, phone_number, no_of_sales" +
                " FROM table_sign_ups WHERE ref_id = " + refId;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                RefsSignUps refsSignUps = new RefsSignUps();
                refsSignUps.setFirstName(cursor.getString(0));
                refsSignUps.setLastName(cursor.getString(1));
                refsSignUps.setStoreName(cursor.getString(2));
                refsSignUps.setPhoneNumber(cursor.getString(3));
                if(cursor.getInt(4) > 0)
                    refsSignUps.setMadeSales(true);
                refsSignUpsList.add(refsSignUps);
            } while (cursor.moveToNext());
        }
        db.close();
        cursor.close();

        return refsSignUpsList;
    }

    private void deleteToDo(ToDo toDo){
        //Delete ToDo from ToDoEntry table
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.delete(ToDoEntryContract.ToDoEntry.TABLE_NAME,
                ToDoEntryContract.ToDoEntry._ID + " = ?",
                new String[]{String.valueOf(toDo.getId())});
        sqLiteDatabase.close();

        //Insert deleted todo into ToDoDeleted table
        sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ToDoEntryContract.COLUMN_TITLE, toDo.getTitle());
        contentValues.put(ToDoEntryContract.COLUMN_DETAILS, toDo.getDetails());
        contentValues.put(ToDoEntryContract.COLUMN_TIME, toDo.getTime());
        contentValues.put(ToDoEntryContract.COLUMN_DATE, toDo.getDate());
        contentValues.put(ToDoEntryContract.COLUMN_PCOLOR_NAME, toDo.getPriorityColor());

        sqLiteDatabase.insert(ToDoEntryContract.ToDoDelete.TABLE_NAME,null,contentValues);

        sqLiteDatabase.close();
    }

    private void deleteDeletedToDo(int id){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.delete(ToDoEntryContract.ToDoDelete.TABLE_NAME,
                ToDoEntryContract.ToDoDelete._ID + " = ?",
                new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    private List<ToDo> getSavedToDos(){
        List<ToDo> toDoList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String [] projection = {
                ToDoEntryContract.COLUMN_TITLE,
                ToDoEntryContract.COLUMN_DETAILS,
                ToDoEntryContract.COLUMN_PCOLOR_NAME,
                ToDoEntryContract.COLUMN_DATE,
                ToDoEntryContract.COLUMN_TIME
        };

        Cursor cursor = sqLiteDatabase.query(
                ToDoEntryContract.ToDoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        if(cursor.moveToFirst()){
            do{
                ToDo toDo = new ToDo();
                toDo.setTitle(cursor.getString(0));
                toDo.setDetails(cursor.getString(1));
                toDo.setPriorityColor(cursor.getString(2));
                toDo.setDate(cursor.getString(3));
                toDo.setTime(cursor.getString(4));

                toDoList.add(toDo);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return toDoList;
    }

    private List<ToDo> getDeletedToDos(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<ToDo> toDoList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + ToDoEntryContract.ToDoDelete.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do{
                ToDo toDo = new ToDo();
                toDo.setTitle(cursor.getString(0));
                toDo.setDetails(cursor.getString(1));
                toDo.setPriorityColor(cursor.getString(2));
                toDo.setDate(cursor.getString(3));
                toDo.setTime(cursor.getString(4));

                toDoList.add(toDo);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return toDoList;
    }

    private List<ToDo> getSavedToDoByPriority(String priority){
        List<ToDo> toDoList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + ToDoEntryContract.ToDoEntry.TABLE_NAME +
                "WHERE " + ToDoEntryContract.COLUMN_PCOLOR_NAME + " = " + priority;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                ToDo toDo = new ToDo();

                toDo.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_TITLE)));
                toDo.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_DETAILS)));
                toDo.setDate(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_DATE)));
                toDo.setTime(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_TIME)));
                toDo.setPriorityColor(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_PCOLOR_NAME)));

                toDoList.add(toDo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return toDoList;
    }

    private List<ToDo> getDeletedToDoByPriority(String priority){
        List<ToDo> toDoList = new ArrayList<>();
        String [] coloumns = {
                ToDoEntryContract.COLUMN_PCOLOR_NAME,
                ToDoEntryContract.COLUMN_TIME,
                ToDoEntryContract.COLUMN_DATE,
                ToDoEntryContract.COLUMN_DETAILS,
                ToDoEntryContract.COLUMN_TITLE
        };
        String selection = ToDoEntryContract.COLUMN_PCOLOR_NAME +"=?";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(
                ToDoEntryContract.ToDoDelete.TABLE_NAME,
                coloumns,
                selection,
                new String[]{priority},
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){
            do{
                ToDo toDo = new ToDo();
                toDo.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_TITLE)));
                toDo.setDetails(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_DETAILS)));
                toDo.setTime(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_TITLE)));
                toDo.setDate(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_DATE)));
                toDo.setPriorityColor(cursor.getString(cursor.getColumnIndexOrThrow(ToDoEntryContract.COLUMN_PCOLOR_NAME)));

                toDoList.add(toDo);
            } while (cursor.moveToNext());
        }

        cursor.close();
        sqLiteDatabase.close();

        return toDoList;
    }

    private void deletedToDo(ToDo toDo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ToDoEntryContract.COLUMN_TITLE, toDo.getTitle());
        contentValues.put(ToDoEntryContract.COLUMN_DETAILS, toDo.getDetails());
        contentValues.put(ToDoEntryContract.COLUMN_PCOLOR_NAME, toDo.getPriorityColor());
        contentValues.put(ToDoEntryContract.COLUMN_DATE, toDo.getDate());
        contentValues.put(ToDoEntryContract.COLUMN_TIME, toDo.getTime());

        sqLiteDatabase.insert(ToDoEntryContract.ToDoDelete.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    private void emptyTrash(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ToDoEntryContract.ToDoDelete.TABLE_NAME);
        sqLiteDatabase.close();
    }

}
