package com.trantan.tasknoteaapp;

import android.database.Cursor;

import java.util.Date;

public class TaskNote {
    private long noteId = -1;
    private String content;
    private Boolean isImportant;
    private Date createDate;

    public TaskNote(long noteId, String content, Boolean isImportant, Date createDate) {
        this.noteId = noteId;
        this.content = content;
        this.isImportant = isImportant;
        this.createDate = createDate;
    }

    public TaskNote() {
        content = "";
        isImportant = false;
        createDate = new Date();
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getImportant() {
        return isImportant;
    }

    public void setImportant(Boolean important) {
        isImportant = important;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public static TaskNote getNoteFromCursor(Cursor cursor){
        try{
            String content = cursor.getString(cursor.getColumnIndexOrThrow(TaskNoteTable.NoteEntry.COLLUMN_CONTENT));
            Boolean isImportant = cursor.getInt(cursor.getColumnIndexOrThrow(TaskNoteTable.NoteEntry.COLLUMN_IS_IMPORTANT)) > 0;
            long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow(TaskNoteTable.NoteEntry.COLLUMN_CREATE_DATE));
            long noteId = cursor.getLong(cursor.getColumnIndexOrThrow(TaskNoteTable.NoteEntry._ID));
            Date createdDate = new Date(dateLong);

            return new TaskNote(noteId, content, isImportant, createdDate);
        }catch (Exception e){
            System.out.println("Cannot create TaskNote. Erro: " + e.toString());
            return null;
        }
    }
}
