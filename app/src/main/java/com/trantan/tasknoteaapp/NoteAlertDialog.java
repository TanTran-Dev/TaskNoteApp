package com.trantan.tasknoteaapp;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import java.util.Date;

public class NoteAlertDialog extends AlertDialog {
    private View view;
    static int INSERT = 0;
    static int UPDATE = 1;
    int DATA_TYPE = INSERT;

    private Button btnCommit;
    private Button btnCancel;

    private CheckBox checkBoxImportant;
    private EditText edtContent;
    private TaskNote taskNote = new TaskNote();

    protected NoteAlertDialog(@NonNull Context context) {
        super(context);
        setupUI();
    }

    protected NoteAlertDialog(@NonNull Context context, TaskNote taskNote, int dataType) {
        super(context);
        this.taskNote = taskNote;
        this.DATA_TYPE = dataType;
        setupUI();
    }

    private void setupUI() {
        view = this.getLayoutInflater().inflate(R.layout.note_alert_dialog, null);
        btnCommit = view.findViewById(R.id.btnCommit);
        btnCancel = view.findViewById(R.id.btnCancel);
        checkBoxImportant = view.findViewById(R.id.checkboxImportant);
        edtContent = view.findViewById(R.id.edtContent);

        btnCancel.setOnClickListener(v -> {
            this.dismiss();
        });

        btnCommit.setOnClickListener(v -> {
            String content = edtContent.getText().toString().trim();
            Boolean isImportant = checkBoxImportant.isChecked();
            if (content.equals("")) {
                this.dismiss();
                return;
            }

            if (this.DATA_TYPE == NoteAlertDialog.INSERT) {
                TaskNote newTaskNote = new TaskNote(-1, content, isImportant, new Date());
                NoteModify.getInstance(this.getContext()).insertNote(newTaskNote);
            } else if (this.DATA_TYPE == NoteAlertDialog.UPDATE) {
                taskNote.setContent(content);
                taskNote.setImportant(isImportant);
                NoteModify.getInstance(this.getContext()).updateNote(taskNote.getNoteId(), taskNote);
            }
            //refresh MainActivity
            MainActivity mainActivity = (MainActivity) ((ContextWrapper) getContext()).getBaseContext();
            mainActivity.refreshListView();
            this.dismiss();
        });
        if (this.DATA_TYPE == NoteAlertDialog.UPDATE){
            fetchDateToUI();
        }
        this.setView(view);
    }

    private void fetchDateToUI(){
        if (taskNote.getContent().trim().length() > 0){
            edtContent.setText(taskNote.getContent());
            checkBoxImportant.setChecked(taskNote.getImportant());
        }
    }
}
