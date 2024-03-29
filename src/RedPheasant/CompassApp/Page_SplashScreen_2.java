/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RedPheasant.CompassApp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.ViewGroup.LayoutParams;

/**
 *
 * @author Gideon
 */
public class Page_SplashScreen_2 extends Activity {

  // Set the display time, in milliseconds (or extract it out as a configurable parameter)
	private final int SPLASH_DISPLAY_LENGTH = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
                overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
		setContentView(R.layout.layout_splashscreen_2);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
		// Obtain the sharedPreference, default to true if not available
		boolean isSplashEnabled = sp.getBoolean("isSplashEnabled", true);

		if (isSplashEnabled)
		{
			new Handler().postDelayed(new Runnable()
			{
				@Override
				public void run()
				{
					//Finish the splash activity so it can't be returned to.
					Page_SplashScreen_2.this.finish();
					// Create an Intent that will start the main activity.
					Intent mainIntent = new Intent(Page_SplashScreen_2.this, Page_Main.class);
					Page_SplashScreen_2.this.startActivity(mainIntent);
				}
			}, SPLASH_DISPLAY_LENGTH);
		}
		else
		{
			// if the splash is not enabled, then finish the activity immediately and go to main.
			finish();
			Intent mainIntent = new Intent(Page_SplashScreen_2.this, Page_Main.class);
			Page_SplashScreen_2.this.startActivity(mainIntent);
		}
        }
        
         @Override
    public void onBackPressed()
    {
    }
}
