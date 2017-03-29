package com.tylersuehr.ist446game.game.framework;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * Basic, arcade-looking button.
 */
public final class MenuButton {
    private final Rect bounds;
    private final String text;
    private boolean down = false;


    public MenuButton(String text, int left, int top, int right, int bottom) {
        this.bounds = new Rect(left, top, right, bottom);
        this.text = text;
    }

    public void onDraw(Painter p) {
        // Draw the button's background
        p.setColor(down ? Color.LTGRAY : Color.RED);
        p.fillRect(bounds.left, bounds.top, bounds.right, bounds.bottom);

        // Draw the button's text
        Paint.FontMetrics fm = p.getFontMetrics();
        float textWidth = p.measureText(text);

        p.setColor(down ? Color.DKGRAY : Color.WHITE);
        p.setTextSize(28f);

        float y = ((bounds.top + bounds.bottom) / 2) + fm.bottom;
        float x = ((bounds.left + bounds.right) / 2) - textWidth / 2;
        p.drawString(text, x, y);
    }

    public void onPressed(int x, int y) {
        this.down = bounds.contains(x, y);
    }

    public boolean isPressed(int x, int y) {
        return down && bounds.contains(x, y);
    }

    public void cancel() {
        this.down = false;
    }
}