/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RedPheasant.CompassApp;

import android.content.Context;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.util.AttributeSet;
import android.view.View;

public class Adapter_Compass_View extends View
{
  private float position;
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  public Adapter_Compass_View(Context context)
  {
    super(context);
    init();
  }
  
 public Adapter_Compass_View(Context context, AttributeSet attrs)
 {
    super(context, attrs);
 }

 public Adapter_Compass_View(Context context, AttributeSet attrs, int defStyle)
 {
  super(context, attrs, defStyle);
 }
  private void init()
  {
    paint.setAntiAlias(true);
    paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(1);
    paint.setColor(Color.WHITE);
    paint.setFilterBitmap(true);
  }

   @Override
 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
 {
    setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getSize(heightMeasureSpec));
 }

 @Override
 protected void onDraw(Canvas canvas)
 {

    int w = getMeasuredWidth();
    int h = getMeasuredHeight();
    int r;

    if(w > h)
    {
        r = h/2;
    }
    else
    {
        r = w/2;
    }

    Options options = new BitmapFactory.Options();
    options.inScaled = false;
    options.inDither = false;
    options.inJustDecodeBounds = false;
    options.inSampleSize = 1;
    options.mCancel = false;
    options.inPreferredConfig = Config.RGB_565;

    Bitmap newcompass11 = BitmapFactory.decodeResource(getResources(), R.drawable.newcompass,options);
    Bitmap newcompass = Bitmap.createScaledBitmap(newcompass11, getMeasuredWidth(),  getMeasuredWidth()+2, true);

    canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);   

    if (position != 0.0)
    {
        double d = (180/Math.PI);
        float radiale = -position * (float)d;
        canvas.rotate(radiale);
    } 
    
    int cx =  - newcompass.getWidth() / 2 ;
    int cy =  - newcompass.getHeight() / 2;

    canvas.drawBitmap(newcompass, cx, cy, paint);
 }
 
 public void update(float dir)
 {
    position = dir;
    invalidate();
 }
}