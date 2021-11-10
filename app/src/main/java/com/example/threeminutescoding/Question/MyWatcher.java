package com.sirius.threeminutescoding.Question;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.sirius.threeminutescoding.Submit;

public class MyWatcher implements TextWatcher {

    private EditText edit;
    private Submit item;
    public MyWatcher(EditText edit) {
        this.edit = edit;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("TAG", "onTextChanged: " + s);
        this.item = (Submit) edit.getTag();
        if (item != null) {
            item.edtAnswer = s.toString();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
