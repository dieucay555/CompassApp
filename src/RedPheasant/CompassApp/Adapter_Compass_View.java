/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package RedPheasant.CompassApp;

import android.content.Context;
import android.graphics.*;
import android.graphics.BitmapFactory.Options;
import android.util.AttributeSet;
import android.view.View;

public class Adapter_Compass_View extends View
{

  private Paint paint;
  private float position;

  public Adapter_Compass_View(Context context)
  {
    super(context);
    //init();
    //setBackgroundResource(R.drawable.compasscreen2);
  }
 public Adapter_Compass_View(Context context, AttributeSet attrs) {
  super(context, attrs);
  // TODO Auto-generated constructor stub
 }

 public Adapter_Compass_View(Context context, AttributeSet attrs, int defStyle) {
  super(context, attrs, defStyle);
  // TODO Auto-generated constructor stub
 }
  private void init()
  {
    paint = new Paint();
    paint.setAntiAlias(true);
    paint.setStrokeWidth(2);
    paint.setTextSize(25);
    paint.setStyle(Paint.Style.STROKE);
    paint.setColor(Color.WHITE);
  }
/*
  @Override
  protected void onDraw(Canvas canvas)
  {
    /*

    float radius = (float) (Math.max(xPoint, yPoint) * 0.6);
    
   //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mapscreencompass);
   //Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
   
   
   //Bitmap b1 = Bitmap.createScaledBitmap(bitmap, getMeasuredWidth(), getMeasuredHeight(), false);
    //Bitmap b2 = Bitmap.createScaledBitmap(bitmap2, getMeasuredWidth(), getMeasuredWidth(), false);
     Bitmap b3 = Bitmap.createScaledBitmap(bitmap3, getMeasuredWidth(), getMeasuredWidth(), false);

   //canvas.drawBitmap(b1, 0, 0, null);
   //canvas.drawBitmap(b2, 0, 100, null);
   canvas.drawBitmap(b3, 0, 100, null);
  //bitmap3.
   //canvas.drawBitmap(bitmap3, 5, 25, null);
 
  
    // 3.143 is a good approximation for the circle
    /*canvas.drawLine(xPoint,
        yPoint,
        (float) (xPoint + radius
            * Math.sin((double) (-position) / 180 * 3.143)),
        (float) (yPoint - radius
            * Math.cos((double) (-position) / 180 * 3.143)), paint);

    canvas.drawText(String.valueOf(position), xPoint, yPoint, paint);
  
   
   canvas.rotate(position, xPoint, yPoint);
   */
/*
       super.onDraw(canvas);
 
            //canvas.drawColor(Color.GRAY);
            int w = canvas.getWidth();
            int h = canvas.getHeight();
            int cw = w / 2;
            int ch = h / 2;
            
            Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);
            Bitmap b3 = Bitmap.createScaledBitmap(bitmap3, getMeasuredWidth(), getMeasuredWidth(), false);

            canvas.translate(cw, ch);   
            if (position != 0.0)
            {
                canvas.rotate(-position);
            } 
            int cx =  - b3.getWidth() / 2 ;
            int cy =  - b3.getHeight() / 2;

            canvas.drawBitmap(b3, cx, cy, null);
  }

  public void update(float position)
  {
    this.position = position;
    invalidate();
  }

} */
  
   @Override
 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
  setMeasuredDimension(
    MeasureSpec.getSize(widthMeasureSpec),
    MeasureSpec.getSize(heightMeasureSpec));
 }

 @Override
 protected void onDraw(Canvas canvas) {
 
  int w = getMeasuredWidth();
  int h = getMeasuredHeight();
  int r;
  if(w > h){
   r = h/2;
  }else{
   r = w/2;
  }
  
  Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  paint.setStyle(Paint.Style.STROKE);
  paint.setStrokeWidth(5);
  paint.setColor(Color.WHITE);
  
  //canvas.drawCircle(w/2, h/2, r, paint);
  
  Options options = new BitmapFactory.Options();
    options.inScaled = false;
    
    
  Bitmap newcompass11 = BitmapFactory.decodeResource(getResources(), R.drawable.newcompass,options);
  //ImageView newc = (ImageView)
  Bitmap newcompass = Bitmap.createScaledBitmap(newcompass11, getMeasuredWidth(),  getMeasuredWidth()+2, true);

    //Rect srcRect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
    //Rect dstRect = new Rect(srcRect);
    //Bitmap.Config conf = Bitmap.Config.ARGB_8888; 
    paint.setFilterBitmap(true);
 
    //canvas.save(Canvas.MATRIX_SAVE_FLAG);
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
    
    //canvas.restore();
    //canvas.drawBitmap(newcompass,0,(h/2-(newcompass.getScaledHeight(canvas)+2)/2),paint);
  //canvas.rotate(-position);
  
  paint.setColor(Color.RED);
  canvas.drawLine(
    w/2,
    h/2,
    (float)(w/2 + r * Math.sin(-position)),
    (float)(h/2 - r * Math.cos(-position)),
    paint);

 }
 
 public void update(float dir){
  position = dir;
  invalidate();
 }
 
}