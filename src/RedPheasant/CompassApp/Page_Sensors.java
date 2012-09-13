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
    private Sensor sensorAccelerometer;//Force.
    private Sensor sensorGyroscope;//Rotation.
    private Sensor sensorGravity;//Force to the ground.
    private Sensor sensorLight;//Sunny //Dark //Cloudy.
    private Sensor sensorAirPressure;//Hecto Pascal.

    //Varables to store the specific sensor data.
    private float[] valuesAccelerometer;
    private float[] valuesGyroscope;
    private float[] valuesGravity;
    private float[] valuesLight;
    private float[] valuesAirPressure;
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
        sensorAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorGravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorAirPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
    }

    public void onSensorChanged(SensorEvent se)
    {
      
       switch(se.sensor.getType())
        {
        case Sensor.TYPE_ACCELEROMETER:
            System.arraycopy(se.values, 0, valuesAccelerometer, 0, 3);
            break;

        case Sensor.TYPE_GYROSCOPE:
            System.arraycopy(se.values, 0, valuesGyroscope, 0, 3);
            break;
            
        case Sensor.TYPE_GRAVITY:
            System.arraycopy(se.values, 0, valuesGravity, 0, 3);
            break;
            
        case Sensor.TYPE_LIGHT:
            System.arraycopy(se.values, 0, valuesLight, 0, 3);
            break;
         
        case Sensor.TYPE_PRESSURE:
            System.arraycopy(se.values, 0, valuesAirPressure, 0, 3);
            break;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {
    }
    
    @Override
 protected void onResume()
 {
     sensorManager.registerListener(this,sensorAccelerometer, SensorManager.SENSOR_DELAY_FASTEST);
     sensorManager.registerListener(this,sensorGyroscope, SensorManager.SENSOR_DELAY_FASTEST);
     sensorManager.registerListener(this,sensorGravity, SensorManager.SENSOR_DELAY_FASTEST);
     sensorManager.registerListener(this,sensorLight, SensorManager.SENSOR_DELAY_FASTEST);
     sensorManager.registerListener(this,sensorAirPressure, SensorManager.SENSOR_DELAY_FASTEST);
  
     super.onResume();
 }

 @Override
 protected void onPause()
 {

  sensorManager.unregisterListener(this);
  super.onPause();
 }
}
