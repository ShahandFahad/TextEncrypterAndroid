package com.practice.on.textencryption.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.practice.on.textencryption.Operations;
import com.practice.on.textencryption.R;
import com.practice.on.textencryption.storageutils.CustomStorageUtils;

public class DecryptFragment extends Fragment {
    private View view;
    private ImageButton btnDeleteText;
    private ImageButton btnCopyText;
    private TextInputEditText edEncryptedText;
    private FloatingActionButton fabDecryptedText;
    private Operations operations;
    private String inputText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_decrypt, container, false);
        // Init views
        btnDeleteText = (ImageButton) view.findViewById(R.id.btnDelete);
        btnCopyText = (ImageButton) view.findViewById(R.id.btnCopy);
        edEncryptedText = (TextInputEditText) view.findViewById(R.id.encryptedText);
        fabDecryptedText = (FloatingActionButton) view.findViewById(R.id.fabDecrypt);
        // Operation class
        operations = new Operations(getActivity());

        // Button delete
        btnDeleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get user text
                inputText = edEncryptedText.getText().toString();
                // delete it
                deleteText();
                // TODO: Work on en ability of fab button once encrypted or decrypted
                fabDecryptedText.setEnabled(true);
            }
        });

        // Button decrypt text
        fabDecryptedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = edEncryptedText.getText().toString();
                String decryptedKey = CustomStorageUtils.getEncryption_key();
                if(TextUtils.isEmpty(decryptedKey)){
                    Toast.makeText(getActivity(), "Enter Key", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(inputText)){
                    Toast.makeText(getActivity(), "Enter Text", Toast.LENGTH_SHORT).show();
                } else{
                    int key = Integer.parseInt(decryptedKey);
                    inputText = operations.decryptText(inputText, key);
                    edEncryptedText.setText(inputText);
                    fabDecryptedText.setEnabled(false);
                }


            }
        });

        // Button copy text to clip board
        btnCopyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get user input
                inputText = edEncryptedText.getText().toString();
                // copy to clip board
                operations.copyTextToClipBoard("Decrypted Text", inputText);
                fabDecryptedText.setEnabled(true);
                edEncryptedText.setText("");
            }
        });


        return view;
    }

    // Delete text
    private void deleteText(){
        if(TextUtils.isEmpty(inputText)){
            Toast.makeText(getActivity(), "Nothing to delete", Toast.LENGTH_SHORT).show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Delete Text");
            builder.setMessage("Do you want to delete text?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    edEncryptedText.setText(null);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }
}