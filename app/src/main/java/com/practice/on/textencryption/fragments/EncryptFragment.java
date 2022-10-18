package com.practice.on.textencryption.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.practice.on.textencryption.MainActivity;
import com.practice.on.textencryption.Operations;
import com.practice.on.textencryption.R;
import com.practice.on.textencryption.storageutils.CustomStorageUtils;


public class EncryptFragment extends Fragment {
    private View view;
    private ImageButton btnDelete;
    private ImageButton btnCopyToClipBoard;
    private FloatingActionButton fabEncryptText;
    private TextInputEditText edPlainText;
    private Operations operations;
    private String inputText;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_encrypt, container, false);

        // Init views
        btnDelete = (ImageButton) view.findViewById(R.id.btnDelete);
        btnCopyToClipBoard = (ImageButton) view.findViewById(R.id.btnCopy);
        fabEncryptText = (FloatingActionButton) view.findViewById(R.id.fabEncrypt);
        edPlainText = (TextInputEditText) view.findViewById(R.id.edPlainText);
        // Main app operation class
        operations = new Operations(getActivity());
        // Delete button
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User text from text area
                inputText = edPlainText.getText().toString();
                deleteText();
            }
        });

        // Button encrypt text
        fabEncryptText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User text from text area
                String keyText = CustomStorageUtils.getEncryption_key();
                inputText = edPlainText.getText().toString();
                // TODO: Communicate with main activity through fragments
                // TODO: Work on decryption
                if(TextUtils.isEmpty(CustomStorageUtils.getEncryption_key())){
                    Toast.makeText(getActivity(), "Enter key" + keyText, Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(inputText)){
                    Toast.makeText(getActivity(), "Enter text", Toast.LENGTH_SHORT).show();
                } else {
                    // Convert String to integers
                    int edTextEncryptKey = Integer.parseInt(keyText);
                    CustomStorageUtils.encryption_key = "";
//                    Toast.makeText(getActivity(), "key :: " + keyText, Toast.LENGTH_SHORT).show();
                    inputText = operations.encryptText(inputText, edTextEncryptKey);
                    edPlainText.setText(inputText);
                    fabEncryptText.setEnabled(false);
                }
            }
        });

        // Button copy text to clip board
        btnCopyToClipBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // User text from text area
                inputText = edPlainText.getText().toString();
                // Copy to clip board
//              copyDataToClipBoard();
                operations.copyTextToClipBoard("Plain Text", inputText);
                edPlainText.setText("");
                fabEncryptText.setEnabled(true);
            }
        });



        return view;
    }
//     Delete text
    private void deleteText() {
        if(TextUtils.isEmpty(inputText)){
            Toast.makeText(getActivity(), "Nothing to delete", Toast.LENGTH_SHORT).show();
        } else {
//             Show Alert dialogue for deleting text
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Delete text");
            builder.setMessage("Text will be deleted");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Set text to null
                    edPlainText.setText(null);
                    Toast.makeText(getActivity(), "Deleted!", Toast.LENGTH_SHORT).show();
                    fabEncryptText.setEnabled(true);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // if press no then cancel the dialogue box
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }
}