package com.example.assassin;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.*;

import edu.gatech.Assassins.model.Request;
import edu.gatech.Assassins.model.User;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static final int PICK_IMAGE = 100;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        Button registerButton, changePicButton;
        
        registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
		        EditText et = (EditText) findViewById(R.id.name);
				String name = et.getText().toString();
				String pic = ImageToString((ImageView) findViewById(R.id.user_image));
				
				Socket sock = null;
		        PrintWriter out = null;
		        BufferedReader in = null;  
		        
		        try {
					sock = new Socket("10.150.22.225", 1337);
					out = new PrintWriter(sock.getOutputStream(), true);
		            in = new BufferedReader(new InputStreamReader(
		                                        sock.getInputStream()));
		        } catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
		        
				
				JSONObject obj = new JSONObject();
				try {
					obj.put("name", name);
					obj.put("pic", pic);
					out.print(obj);
					String json = in.readLine();
					
					obj = new JSONObject(json);
					if (obj.getString("type").equals(Request.ack))
						startActivity(new Intent(v.getContext(), PreGameActivity.class));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} );
        
        changePicButton = (Button) findViewById(R.id.change_pic);
        changePicButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(Intent.ACTION_PICK, Images.Media.EXTERNAL_CONTENT_URI), PICK_IMAGE);		
			}
		} );
    }

    protected void onActivityResult(int requestCode, int resultCode, 
    	       Intent imageReturnedIntent) {
    	super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 
        
    	if (resultCode == RESULT_OK) {

        	Uri selectedImage = imageReturnedIntent.getData();
        	String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(
                               selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();


            Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
            ImageView iv = (ImageView) findViewById(R.id.user_image);
            iv.setImageBitmap(yourSelectedImage);
 
    	}
    }
    
    public String ImageToString(ImageView i) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Bitmap bm = Bitmap.createBitmap(i.getWidth(),i.getHeight(),Bitmap.Config.ARGB_8888);
		bm.compress(CompressFormat.PNG, 1, baos);
		return baos.toString();
	}

}
