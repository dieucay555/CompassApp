/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RedPheasant.CompassApp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;





/**
 *
 * @author Gideon
 */
public class Page_Favorites extends Activity
{
   
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
        setContentView(R.layout.main_helpscreen);

    }
    
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.layout.animation_fadein, R.layout.animation_fadeout);
    }
}

