package com.sirius.threeminutescoding;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class QuestionRecommendationDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.dialog_question_recommendation, null);

        builder.setView(view);

        final Dialog dialog = builder.create();

        Spinner spinner = view.findViewById(R.id.spinner_level);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final EditText editText = view.findViewById(R.id.edit_expect_minutes);

        view.findViewById(R.id.btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expectMinutesInput = editText.getText().toString();
                if(expectMinutesInput.isEmpty()) {
                    Toast.makeText(getActivity(), "예상 소요 시간을 적어주세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    int expectMinutes = Integer.parseInt(editText.getText().toString());
                    Log.d("예상 소요 시간", expectMinutes+"");
                    dialog.dismiss();
                }

            }
        });

        view.findViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;

    }
}
