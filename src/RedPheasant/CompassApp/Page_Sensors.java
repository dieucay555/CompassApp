/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RedPheasant.CompassApp;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

/**
 *
 * @author gideon
 */
public class Page_Sensors extends Activity implements SensorEventListener
{
 //---------------------------------------------
 // SENSOR VARIABLES
 //---------------------------------------------
    
    //Manager that takes care of the Service.
    SensorManager sensorManager;
    
    //Specific sensors.
   // private Sensor sensorAccelerometer;//Force.
    private Sensor sensorGyroscope;//Rotation.
    private Sensor sensorGravity;//Force to the ground.
    private Sensor sensorLight;//Sunny //Dark //Cloudy.
    //private Sensor sensorAirPressure;//Hecto Pascal.

    TextView  lightSensor,gravitySensor,gyroscopeSensor,accelerometerSensor;
    float maxLight;
    
 //---------------------------------------------
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
        setContentView(R.layout.main_sensorscreen);
        
        //Maak seker die screen bly aan.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
        //Kry die sensor Service van die foon af.
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        
        //Kry die spesifieke sensors van die Service af.
        //sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //sensorAirPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        
        //-----------------------
        //  Light Sensor
        //-----------------------
           maxLight = sensorLight.getMaximumRange();
           sensorManager.registerListener(this, sensorLight, SensorManager.SENSOR_DELAY_FASTEST);
           lightSensor = (TextView)findViewById(R.id.sensor_light);
        //-----------------------
           
        //-----------------------
        //  Gravity Sensor
        //-----------------------
        gravitySensor = (TextView)findViewById(R.id.sensor_gravity);
        //-----------------------
        
    
        //accelerometerSensor = (TextView)findViewById(R.id.sensor_pressure);
           
    }

    public void onSensorChanged(SensorEvent se)
    {
        
       switch(se.sensor.getType())
        {
        case Sensor.TYPE_GRAVITY:
            gravitySensor.setText("x: "+ Math.round(se.values[0]*100.0)/100.0+" m/s^2 \ny: "+ Math.round(se.values[1]*100.0)/100.0+" m/s^2 \nz: "+ Math.round(se.values[2]*100.0)/100.0 +" m/s^2");
            break;
            
        case Sensor.TYPE_LIGHT:
            float currentReading = se.values[0];
            
            if(currentReading >= 0 && currentReading <= 10)
            {
                lightSensor.setText("Pitch Black"); 
            }
            else if(currentReading >= 11 && currentReading <= 50)
            {
                lightSensor.setText("Very Dark"); 
            }
            else if(currentReading >= 51 && currentReading <= 200)
            {
                lightSensor.setText("Dark Indoors"); 
            }
            else if(currentReading >= 201 && currentReading <= 400)
            {
                lightSensor.setText("Dim Indoors"); 
            }
            else if(currentReading >= 401 && currentReading <= 1000)
            {
                lightSensor.setText("Normal Indoors"); 
            }
            else if(currentReading >= 1001 && currentReading <= 5000)
            {
                lightSensor.setText("Bright Indoors"); 
            }
            else if(currentReading >= 5001 && currentReading <= 10000)
            {
                lightSensor.setText("Dim Outdoors"); 
            }
            else if(currentReading >= 10001 && currentReading <= 30000)
            {
                lightSensor.setText("Cloudy Outdoors"); 
            }
            else if(currentReading >= 30001 && currentReading <= 100000)
            {
                lightSensor.setText("Direct Sunlight"); 
            }
           
            break;
         
        
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }
    
    @Override
 protected void onResume()
 {
     sensorManager.registerListener(this,sensorGravity, SensorManager.SENSOR_DELAY_FASTEST);
     sensorManager.registerListener(this,sensorLight, SensorManager.SENSOR_DELAY_FASTEST);
  
     super.onResume();
 }

 @Override
 protected void onPause()
 {

  sensorManager.unregisterListener(this);
  super.onPause();
 }
}
