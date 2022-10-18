package com.practice.on.textencryption;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    private TextView helpInstruction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        // set action bar color
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.blaze_orange));
        actionBar.setBackgroundDrawable(colorDrawable);
        // help instruction
        helpInstruction = (TextView) findViewById(R.id.help_instruction);
        helpInstruction.setText(
                "1. Enter Encryption or Decryption Key\n" +
                        "2. Enter Text to Encrypt or Decrypt\n" +
                        "3. Press the Encrypt or Decrypt Button\n" +
                        "4. Copy or Delete Text to Again Enable Encryption or Decryption Button"
        );
    }
}