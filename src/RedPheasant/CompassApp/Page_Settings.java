/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RedPheasant.CompassApp;

import android.app.Activity;
import android.os.Bundle;



public class Page_Settings extends Activity
{
    /** Called when the activity is first created.*/
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
        setContentView(R.layout.main_settingsscreen);
    }
    
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
    }
        
}
