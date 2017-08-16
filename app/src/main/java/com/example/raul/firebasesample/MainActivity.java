package com.example.raul.firebasesample;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;


import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView2);
        textView.setTextIsSelectable(true);


        textView.setText(FirebaseInstanceId.getInstance().getToken());


    }


    public boolean onCreateActionMode(ActionMode mode, Menu menu) {

    menu.removeItem(android.R.id.shareText);
    menu.removeItem(android.R.id.cut);

    MenuInflater inflater = mode.getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
    }


    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
    switch (item.getItemId()) {
    case R.id.action_copy:
        copyText();
        mode.finish();
            return true;
        case R.id.action_paste:
        pasteText();
            mode.finish();
            return true;
        default:
            return false;
    }


    }

    private void copyText() {
    ClipboardManager clipboardManager = (ClipboardManager)
    getSystemService(Context.CLIPBOARD_SERVICE);

    CharSequence selectedTxt = textView.getText().subSequence(textView.getSelectionStart(), textView.getSelectionEnd());
    ClipData clipData = ClipData.newPlainText("zoftino text view", selectedTxt);
    clipboardManager.setPrimaryClip(clipData);
    }

    private void pasteText() {
    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

    if(clipboardManager.hasPrimaryClip()) {
    ClipData.Item item = clipboardManager.getPrimaryClip().getItemAt(0);

    CharSequence ptext = item.getText();
    //pastetv.setText(ptext);
    }
    }
}
