package com.amrozek.kmicic;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.view.View.*;
import android.widget.ArrayAdapter;
import android.util.Log;

import android.app.AlertDialog;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.List;

public class activity1 extends Activity implements OnClickListener
{
	private MyDataHelper1 dh;
	public static final String TAG = "[Kmicic] ** ";
	public static final String WELCOME = "Prosty słownik języka szwedzkiego - Zapraszam!\nProszę wpisać zapytanie u góry.";
	EditText tad, ted;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
		// Interface handling
        super.onCreate(savedInstanceState);
        
		setContentView(R.layout.main);
		Button btn = (Button)findViewById(R.id.szukaj);
		btn.setOnClickListener (this);

		// editbox
		tad = (EditText)findViewById(R.id.wynik);
		ted = (EditText)findViewById(R.id.zapytka);

		// Checking Installation
		
		MyDownloadHelper mdh = new MyDownloadHelper (this);
		if (mdh.checkInstall()) {
			tad.setText (WELCOME);
		}
		else {
			tad.setText ("Finalizowanie instalacji...");
			if (mdh.downloadDictionary())
				tad.setText ("Instalacja zakończona powodzeniem!\n" + WELCOME);
			else
				tad.setText ("Brak słownika w pliku /sdcard/slownik.db!");
		}
		this.dh = new MyDataHelper1 (this);

		Log.v(TAG, "Starting.");
    }

	@Override
	public void onClick (View w) {
		Log.v (TAG, "onClickEvent");

		// hide keyboard
		//getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN); (nie dziala)

		// database handling
		List<String> names = this.dh.select(ted.getText().toString());
		StringBuilder sb = new StringBuilder();
		sb.append ("Wynik wyszukiwania:\n");
		for (String name : names) {
			sb.append (name + "\n");
		}
		tad.setText(sb.toString());
	}
}
