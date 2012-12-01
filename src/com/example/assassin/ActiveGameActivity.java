package com.example.assassin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ActiveGameActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.active_game);    
        
        
        
        
        Button killedButton = (Button) findViewById(R.id.dead_button);
        killedButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Tell server
				
				startActivity(new Intent(v.getContext(), LossGameActivity.class));
			}
		} );
    }
    
    public Bitmap StringToImage(String s) {
		return BitmapFactory.decodeByteArray(s.getBytes(), 0, s.length());
	}
     
}
