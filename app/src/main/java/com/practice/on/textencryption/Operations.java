package com.practice.on.textencryption;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.practice.on.textencryption.decryption.Decryption;
import com.practice.on.textencryption.encryption.Encryption;

import org.w3c.dom.Text;

public class Operations {
    private boolean isDeleteText = false;
    private Activity activity;
    private String inputText;
    private Encryption encryption = new Encryption();
    private Decryption decryption = new Decryption();

    public Operations(Activity activity){
        this.activity = activity;
    }
    // Copy Text to Clip Board
    public void copyTextToClipBoard(String title, String text){
        if(TextUtils.isEmpty(text)){
            Toast.makeText(activity.getApplicationContext(), "Nothing to copy!", Toast.LENGTH_SHORT).show();
        } else {
            // copy text to clipboard
            ClipboardManager clipboardManager = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(title, text);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(activity.getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }
    // TODO : make class by each name and work on it
    public String encryptText(String text, int key){
        if(TextUtils.isEmpty(text)){
            Toast.makeText(activity.getApplicationContext(), "No Text", Toast.LENGTH_SHORT).show();
            return "";
        } else {

            Toast.makeText(activity.getApplicationContext(), "Encrypted", Toast.LENGTH_SHORT).show();
//           Return encrypted text
            return encryption.encryptPlainText(text, key);
        }
    }
    public String decryptText(String text, int key){
        if(TextUtils.isEmpty(text)){
            Toast.makeText(activity.getApplicationContext(), "No Text", Toast.LENGTH_SHORT).show();
            return "";
        } else {
            Toast.makeText(activity.getApplicationContext(), "Decrypted", Toast.LENGTH_SHORT).show();
            return decryption.decryptPlainText(text, key);
        }
    }
}
