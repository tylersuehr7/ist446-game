package com.tylersuehr.ist446game.game;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.tylersuehr.ist446game.game.framework.IGame;
import com.tylersuehr.ist446game.game.framework.IGamePresenter;
import com.tylersuehr.ist446game.game.framework.InputManager;
import com.tylersuehr.ist446game.game.framework.Painter;
import com.tylersuehr.ist446game.game.framework.Screen;
import com.tylersuehr.ist446game.game.screens.LoadingScreen;
/**
 * Copyright 2017 Tyler Suehr
 * Created by tyler on 3/29/2017.
 *
 * This is what handles drawing/running our game in an Activity.
 */
@SuppressLint("ViewConstructor")
public class GameView extends SurfaceView implements IGame, Runnable {
//    public static final int GAME_WIDTH = 450;
//    public static final int GAME_HEIGHT = 900;
    public static final int GAME_WIDTH = 1440;
    public static final int GAME_HEIGHT = 2560;

    private Bitmap gameImage;
    private Rect gameImageSrc;
    private Rect gameImageDst;
    private Canvas gameCanvas;
    private Painter graphics;

    private final IGamePresenter gamePresenter;
    private InputManager inputManager;
    private Thread gameThread;
    private volatile boolean running = false; // Whether the game is running or not
    private volatile Screen currentScreen; // The screen being updated and drawn


    public GameView(Context c, IGamePresenter presenter) {
        super(c);

        // Setup the game Bitmap and its clipping
        gameImage = Bitmap.createBitmap(GAME_WIDTH, GAME_HEIGHT, Bitmap.Config.RGB_565);
        gameImageSrc = new Rect(0, 0, gameImage.getWidth(), gameImage.getHeight());
        gameImageDst = new Rect();
        gameCanvas = new Canvas(gameImage);
        graphics = new Painter(gameCanvas);
        gamePresenter = presenter;

        // Handle necessary callbacks when the view is created/destroyed.
        getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Initialize input controller
                if (inputManager == null) {
                    inputManager = new InputManager(GAME_WIDTH, GAME_HEIGHT);
                }
                setOnTouchListener(inputManager);

                // Lazy load game state
                if (currentScreen == null) {
                    setCurrentScreen(new LoadingScreen(GameView.this, gamePresenter, getContext()));
                }

                // Start the entire game
                startGame();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // Pause the entire game
                stopGame();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
        });
    }

    @Override
    public void run() {
        long updateDurationMillis = 0;
        long sleepDurationMillis = 0;

        while (running) {
            try {
                long beforeUpdate = System.nanoTime();
                long deltaMillis = sleepDurationMillis + updateDurationMillis;

                // Update and draw the game objects
                currentScreen.onUpdate(deltaMillis / 1000f);
                currentScreen.onDraw(graphics);

                // Draw the game's entire image
                Canvas screen = getHolder().lockCanvas();
                if (screen != null) {
                    screen.getClipBounds(gameImageDst);
                    screen.drawBitmap(gameImage, gameImageSrc, gameImageDst, null);
                    getHolder().unlockCanvasAndPost(screen);
                }

                updateDurationMillis = (System.nanoTime() - beforeUpdate) / 1000000L;
                sleepDurationMillis = Math.max(2, 17 - updateDurationMillis);
                Thread.sleep(sleepDurationMillis);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void startGame() {
        this.running = true;
        this.gameThread = new Thread(this, "GameYo!");
        this.gameThread.start();
    }

    @Override
    public void stopGame() {
        this.running = false;
        while (gameThread.isAlive()) {
            try {
                gameThread.join();
                break;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void setCurrentScreen(Screen screen) {
        System.gc();
        screen.onInitialize();
        this.currentScreen = screen;
        this.inputManager.setCurrentScreen(currentScreen);
    }
}