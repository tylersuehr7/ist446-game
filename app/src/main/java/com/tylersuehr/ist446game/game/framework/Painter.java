package com.tylersuehr.ist446game.game.framework;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * Utility to store shared resources for painting in-game objects.
 */
public final class Painter {
    private final Canvas canvas;
    private final Paint paint;
    private final Rect srcRect;
    private final Rect dstRect;
    private final RectF dstRectF;


    public Painter(Canvas canvas) {
        this.canvas = canvas;
        this.paint = new Paint();
        this.srcRect = new Rect();
        this.dstRect = new Rect();
        this.dstRectF = new RectF();
    }

    public void setColor(@ColorInt int color) {
        this.paint.setColor(color);
    }

    public void setTypeface(Typeface typeface) {
        this.paint.setTypeface(typeface);
    }

    public void setTextSize(float size) {
        this.paint.setTextSize(size);
    }

    public void reset() {
        this.paint.reset();
    }

    public Paint.FontMetrics getFontMetrics() {
        return paint.getFontMetrics();
    }

    public float measureText(String text) {
        return paint.measureText(text);
    }

    public Rect getTextBounds(String text) {
        this.paint.getTextBounds(text, 0, text.length(), srcRect);
        return srcRect;
    }

    public float centerText(String text, int objectWidth) {
        float textWidth = paint.measureText(text);
        return (objectWidth / 2) - (textWidth / 2);
    }

    public void drawString(String text, float x, float y) {
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        this.canvas.drawText(text, x, y, paint);
    }

    public void drawBoldString(String text, float x, float y) {
        this.paint.setFakeBoldText(true);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        this.canvas.drawText(text, x, y, paint);
        this.paint.setFakeBoldText(false);
    }

    public void fillRect(int x, int y, int width, int height) {
        this.dstRect.set(x, y, width, height);
        this.paint.setStyle(Paint.Style.FILL);
        this.canvas.drawRect(dstRect, paint);
    }

    public void fillOval(int x, int y, int width, int height) {
        this.dstRectF.set(x, y, x + width, y + height);
        this.paint.setStyle(Paint.Style.FILL);
        this.canvas.drawOval(dstRectF, paint);
    }

    public void fillCircle(int x, int y, int radius) {
        this.paint.setStyle(Paint.Style.FILL);
        this.canvas.drawCircle(x, y, radius, paint);
    }

    public void drawImage(Bitmap bp, int x, int y) {
        this.canvas.drawBitmap(bp, x, y, paint);
    }

    public void drawImage(Bitmap bp, int x, int y, int width, int height) {
        this.srcRect.set(0, 0, bp.getWidth(), bp.getHeight());
        this.dstRect.set(x, y, x + width, y + height);
        this.canvas.drawBitmap(bp, srcRect, dstRect, paint);
    }

    public void drawImage(Drawable dr, int x, int y, int width, int height) {
        dr.setBounds(x, y, x + width, y + height);
        dr.draw(canvas);
    }

    public void drawGameObject(Bitmap bp, GameObject ob) {
        this.canvas.drawBitmap(bp, ob.getX(), ob.getY(), paint);
    }

    public void drawGameObject(Drawable dr, GameObject ob) {
        int x = (int)ob.getX();
        int y = (int)ob.getY();
        dr.setBounds(x, y, x + ob.getWidth(), y + ob.getHeight());
        dr.draw(canvas);
    }
}