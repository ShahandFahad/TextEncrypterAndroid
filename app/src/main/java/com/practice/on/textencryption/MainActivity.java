package com.practice.on.textencryption;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayoutMediator;
import com.practice.on.textencryption.databinding.ActivityMainBinding;
import com.practice.on.textencryption.fragments.DecryptFragment;
import com.practice.on.textencryption.fragments.EncryptFragment;
import com.practice.on.textencryption.storageutils.CustomStorageUtils;

import androidx.databinding.DataBindingUtil;

public class MainActivity extends AppCompatActivity {
    private MainActivity mainActivity;
    private ActivityMainBinding binding;
    private ViewPagerAdapter adapter;
    private EditText edKeyText;
    private String encryptionKey = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set action bar color
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.blaze_orange));
        actionBar.setBackgroundDrawable(colorDrawable);
        // Init views
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivity = this;
        edKeyText = (EditText) findViewById(R.id.edKeyText);
        // Initialize views
        initViews();
        // get key and store it in static variables
         enterEncryptionKey();
    }


    public void initViews(){
        setupViewPager(binding.viewPager);
//        binding.tabLayout.setupWithViewPager(binding.viewPager);


        new TabLayoutMediator(binding.tabLayout, binding.viewPager,
                (tab, position)-> {tab.setText(adapter.getFragmentTitle(position));
//                tab.setCustomView(R.layout.custom_tab);
                }).attach();

        for (int i = 0; i < binding.tabLayout.getTabCount(); i++){

            TextView tv = (TextView) LayoutInflater.from(mainActivity)
                    .inflate(R.layout.custom_title_layout, null);
            // Set text this as it was not displaying any text without adding this line
            tv.setText(adapter.getFragmentTitle(i));
            binding.tabLayout.getTabAt(i).setCustomView(tv);
            // This will also add text
//            binding.tabLayout.getTabAt(i).setText(adapter.getFragmentTitle(i));
        }
    }


//  set view pager
    private void setupViewPager(ViewPager2 viewPager) {
        adapter = new ViewPagerAdapter(mainActivity.getSupportFragmentManager(), mainActivity.getLifecycle());
        adapter.addFragment(new EncryptFragment(), "Encrypt");
        adapter.addFragment(new DecryptFragment(), "Decrypt");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
    }

    // Store key from edit text
//  public String getEncryptionKey(){
    public void enterEncryptionKey(){
        edKeyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CustomStorageUtils.encryption_key = edKeyText.getText().toString();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
           case R.id.share:
               Toast.makeText(mainActivity, "Share", Toast.LENGTH_SHORT).show();
               // Set share intent
               Intent shareIntent = new Intent(Intent.ACTION_SEND);
               shareIntent.setType("text/plain");
               shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Text Encryption");
               String shareMessage = "\nShare With Friends\n\n";
               shareMessage += shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
               shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
               startActivity(Intent.createChooser(shareIntent, "Choose One"));
               return true;
           case R.id.about:
               appInfo();
               return true;
           case R.id.help:
               startActivity(new Intent(MainActivity.this, HelpActivity.class));
               return true;
           case R.id.quit:
               finish();
               System.exit(0);
               return true;
           default:
               return super.onOptionsItemSelected(item);

       }
    }
    private void appInfo(){
        PackageInfo packageInfo = null;
        try{
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }
        int versionNumber = packageInfo.versionCode;
        String versionName = packageInfo.versionName;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("About");
        builder.setMessage(
                "Version Name: " + versionName + "\n"
                + "Version Number: " + versionNumber);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}