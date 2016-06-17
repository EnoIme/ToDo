package wecan.com.todo;

import android.provider.BaseColumns;

/**
 * Created by owner on 4/6/2016.
 */

final class ToDoEntryContract {

    public ToDoEntryContract(){}

    static final String COLUMN_TITLE = "title";
    static final String COLUMN_DETAILS = "details";
    static final String COLUMN_PCOLOR_NAME = "priority_color";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_TIME = "time";

    static abstract class ToDoEntry implements BaseColumns{
        static final String TABLE_NAME = "saved_todos";
    }

    static abstract class ToDoDelete implements BaseColumns {
        static final String TABLE_NAME = "deleted_todos";
    }
}
