package RedPheasant.CompassApp;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

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
      setContentView(R.layout.main);
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
  }

 @Override
 protected void onResume()
 {

  sensorManager.registerListener(this,
    sensorAccelerometer,
    SensorManager.SENSOR_DELAY_NORMAL);
  sensorManager.registerListener(this,
    sensorMagneticField,
    SensorManager.SENSOR_DELAY_NORMAL);
  super.onResume();
 }

 @Override
 protected void onPause() {

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
   
   if(azimuth > 0 && azimuth < 90)
   {
   readingAzimuth.setText(String.valueOf(azimuth)+"° N");
   }
   else if(azimuth > 90 && azimuth < 180)
   {
   readingAzimuth.setText(String.valueOf(azimuth)+"° W");
   }
      else if(azimuth > 180 && azimuth < 270)
   {
   readingAzimuth.setText(String.valueOf(azimuth)+"° S");
   }
      else if(azimuth > 270 && azimuth < 360)
   {
   readingAzimuth.setText(String.valueOf(azimuth)+"° E");
   }
   
   
   //readingPitch.setText("Pitch: " + String.valueOf(pitch));
   //readingRoll.setText("Roll: " + String.valueOf(roll));
   
   myCompass.update(matrixValues[0]);
  }
 }
}