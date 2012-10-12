/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RedPheasant.CompassApp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;



public class Page_Settings extends Activity
{
    /** Called when the activity is first created.*/
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
        setContentView(R.layout.main_settingsscreen);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        
        
          CheckedTextView boxi = (CheckedTextView) findViewById(R.id.checboxi);
          boxi.setText("Set Location: ");
        //Create and initialize submit Button
        ImageButton submit = (ImageButton) findViewById(R.id.submit_button);
        submit.setAdjustViewBounds(true);
        submit.setImageResource(R.drawable.selector_friendscreen_add_friends_find_friends_button);
        
        submit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //
              Toast.makeText(Page_Settings.this, "Searching ...", Toast.LENGTH_SHORT).show();
            }
        });
    
    }
    
    
    
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
    }
        
}
