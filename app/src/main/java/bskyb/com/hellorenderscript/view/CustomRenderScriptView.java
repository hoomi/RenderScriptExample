package bskyb.com.hellorenderscript.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.RenderScript;
import android.util.AttributeSet;
import android.view.View;

import javax.inject.Inject;

import bskyb.com.hellorenderscript.R;
import bskyb.com.hellorenderscript.RenderscriptApplication;
import bskyb.com.hellorenderscript.ScriptC_MyScript;

/**
 * Created by hos05 on 5/23/16.
 */
public class CustomRenderScriptView extends View {

    @Inject
    RenderScript renderScript;
    private Allocation inAllocation;
    private Allocation outAllocation;
    private Bitmap outBitmap;
    private Paint testPaint;
    public CustomRenderScriptView(Context context) {
        this(context, null);
    }

    public CustomRenderScriptView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomRenderScriptView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomRenderScriptView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        if (!isInEditMode()) {
            inject(context);
        }
    }

    private void inject(Context context) {
        RenderscriptApplication renderscriptApplication = (RenderscriptApplication) context.getApplicationContext();
        renderscriptApplication.getApplicationComponent().inject(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        outBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        inAllocation = Allocation.createFromBitmap(renderScript, bitmap);
        outAllocation = Allocation.createFromBitmap(renderScript, outBitmap);

        ScriptC_MyScript scriptC_myScript = new ScriptC_MyScript(renderScript);
        scriptC_myScript.forEach_invert(inAllocation, outAllocation);
        inAllocation.destroy();
        outAllocation.destroy();
        testPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int savedState = canvas.save();
        canvas.drawBitmap(outBitmap,0,0,testPaint);
        canvas.restoreToCount(savedState);
        super.onDraw(canvas);
    }
}
