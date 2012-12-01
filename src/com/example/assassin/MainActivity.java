package com.example.assassin;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public String AppName = "Assassin";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        
        Button registerButton, changePicButton;
 
        registerButton = (Button) findViewById(R.id.register);
        registerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        EditText et = (EditText) findViewById(R.id.name);
				Editable name = et.getText();
				
			}
		} );
        
        changePicButton = (Button) findViewById(R.id.change_pic);
        changePicButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);		
			}
		} );
    }

    protected void onActivityResult(int requestCode, int resultCode, 
    	       Intent imageReturnedIntent) {
    	    super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }




    
   


}
