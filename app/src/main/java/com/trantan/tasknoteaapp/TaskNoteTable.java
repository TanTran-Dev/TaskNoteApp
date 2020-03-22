package com.trantan.tasknoteaapp;

import android.provider.BaseColumns;

public class TaskNoteTable {
    static class NoteEntry implements BaseColumns {
        static final String TABLE_NAME = "tblTaskNote";
        static final String COLLUMN_CONTENT = "content";
        static final String COLLUMN_IS_IMPORTANT = "isImportant";
        static final String COLLUMN_CREATE_DATE = "createDate";
        static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + NoteEntry.TABLE_NAME + " (" +
                        NoteEntry._ID + " INTEGER PRIMARY KEY," +
                        NoteEntry.COLLUMN_CONTENT + " TEXT," +
                        NoteEntry.COLLUMN_CREATE_DATE + " TEXT," +
                        NoteEntry.COLLUMN_IS_IMPORTANT + " INT)";

        static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + NoteEntry.TABLE_NAME;
    }
}
