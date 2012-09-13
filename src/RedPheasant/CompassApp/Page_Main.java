package RedPheasant.CompassApp;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Page_Main extends Activity implements SensorEventListener
{
 TextView readingAzimuth;
 SensorManager sensorManager;
 private Sensor sensorAccelerometer;
 private Sensor sensorMagneticField;
 
 private float[] valuesAccelerometer;
 private float[] valuesMagneticField;
 
 private float[] matrixR;
 private float[] matrixI;
 private float[] matrixValues;
 
 
 //TextView readingAzimuth, readingPitch, readingRoll;
 Adapter_Compass_View myCompass;
 
  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
      super.onCreate(savedInstanceState);
      overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
      setContentView(R.layout.main);
      
      //Maak seker die screen bly aan.
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
      
      readingAzimuth = (TextView)findViewById(R.id.azimuth);
      //readingPitch = (TextView)findViewById(R.id.pitch);
      //readingRoll = (TextView)findViewById(R.id.roll);
    
      myCompass = (Adapter_Compass_View)findViewById(R.id.mycompass);
    
      sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
      sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
      sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    
   valuesAccelerometer = new float[3];
   valuesMagneticField = new float[3];

   matrixR = new float[9];
   matrixI = new float[9];
   matrixValues = new float[3];
   
    //First , disable the scrollbar (Because it looks ugly.)
        HorizontalScrollView hsView = (HorizontalScrollView) findViewById(R.id.horizontalscrollbar_mainscreen);
        hsView.setHorizontalScrollBarEnabled(false);


        //Home Button
        ImageButton imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
        imageButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Intent myIntent = new Intent(v.getContext(), Page_Main.class);
                //startActivityForResult(myIntent, 0);
                //Toast.makeText(Page_Main.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });




        //Settings Button
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(v.getContext(), Page_Settings.class);
                startActivityForResult(myIntent, 0);
                overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
            }
        });

 


        //Friends Button
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                 Toast.makeText(Page_Main.this, "Under Construction...", Toast.LENGTH_LONG).show();
                 //Intent myIntent = new Intent(v.getContext(), Page_Sensors.class);
                 //startActivityForResult(myIntent, 0);
                 //overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
            }
        });




        //Favorites Button
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(v.getContext(), Page_Favorites.class);
                startActivityForResult(myIntent, 0);
                overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
            }
        });




        //Help Button
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.imageButton5);
        imageButton5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(v.getContext(), Page_Help.class);
                startActivityForResult(myIntent, 0);
                overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
            }
        });
    
    
  }

 @Override
 protected void onResume()
 {

  sensorManager.registerListener(this,
    sensorAccelerometer,
    SensorManager.SENSOR_DELAY_FASTEST);
  sensorManager.registerListener(this,
    sensorMagneticField,
    SensorManager.SENSOR_DELAY_FASTEST);
  super.onResume();
 }

 @Override
 protected void onPause()
 {

  sensorManager.unregisterListener(this,
    sensorAccelerometer);
  sensorManager.unregisterListener(this,
    sensorMagneticField);
  super.onPause();
 }

 @Override
 public void onAccuracyChanged(Sensor arg0, int arg1) {
  // TODO Auto-generated method stub
  
 }

 @Override
 public void onSensorChanged(SensorEvent event)
 {
  // TODO Auto-generated method stub
  switch(event.sensor.getType())
  {
  case Sensor.TYPE_ACCELEROMETER:
   for(int i =0; i < 3; i++)
   {
    valuesAccelerometer[i] = event.values[i];
   }
   break;
  
  case Sensor.TYPE_MAGNETIC_FIELD:
   for(int i =0; i < 3; i++)
   {
    valuesMagneticField[i] = event.values[i];
   }
   break;
  }
  
  boolean success = SensorManager.getRotationMatrix(
       matrixR,
       matrixI,
       valuesAccelerometer,
       valuesMagneticField);
  
  if(success)
  {
   SensorManager.getOrientation(matrixR, matrixValues);
   
   double azimuth = Math.toDegrees(matrixValues[0]);
   //double pitch = Math.toDegrees(matrixValues[1]);
   //double roll = Math.toDegrees(matrixValues[2]);
   
   String azimuthS = String.valueOf(azimuth);
   String temp = azimuthS.substring(0, azimuthS.indexOf("."));
   String Degrees = temp;
   
   if(azimuth > 0 && azimuth < 90)
   {
       if(azimuth < 1)
         readingAzimuth.setText(Degrees+"° N");
       else
       readingAzimuth.setText(Degrees+"° NW");
   }
   
   if(azimuth > 90 && azimuth < 180)
   {
       if(azimuth < 91)
    readingAzimuth.setText(Degrees+"° W");
       else
       readingAzimuth.setText(Degrees+"° SW");
   }
   
   if(azimuth < -90 && azimuth > -180)
   {
    int azimuth2 = Integer.parseInt(Degrees);
    azimuth2 = 360 - Math.abs(azimuth2);
    Degrees = String.valueOf(azimuth2);
    
    if(azimuth2 < 181)
    readingAzimuth.setText(Degrees+"° S");
    else
    readingAzimuth.setText(Degrees+"° SE");
    
    if(azimuth2 == 180)
    {
    readingAzimuth.setText(Degrees+"° S");
    }
   }
   
   if(azimuth < 0 && azimuth > -90)
   {
    int azimuth2 = Integer.parseInt(Degrees);
    azimuth2 = 360 - Math.abs(azimuth2);
    Degrees = String.valueOf(azimuth2);
    
    if(azimuth2 < 271)
    readingAzimuth.setText(Degrees+"° E");
    else
    readingAzimuth.setText(Degrees+"° NE");
    
    if(azimuth2 == 270)
    {
        readingAzimuth.setText(Degrees+"° E");
    }
    
    if(azimuth2 == 360)
    {
        readingAzimuth.setText(Degrees+"° N");
    }
    
   }
   
   
   //readingPitch.setText("Pitch: " + String.valueOf(pitch));
   //readingRoll.setText("Roll: " + String.valueOf(roll));
   
   myCompass.update(matrixValues[0]);
  }
 }

   
  @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
        .setTitle("Really Exit?")
        .setMessage("Are you sure you want to exit?")
        .setNegativeButton(android.R.string.no, null)
        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
        {

            public void onClick(DialogInterface arg0, int arg1)
            {
                Page_Main.super.onBackPressed();
                finish();
            }
        }).create().show();
    }
    

}