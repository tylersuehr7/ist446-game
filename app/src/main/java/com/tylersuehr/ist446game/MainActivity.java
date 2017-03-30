package com.tylersuehr.ist446game;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.tylersuehr.ist446game.game.GameView;
import com.tylersuehr.ist446game.game.Registry;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {
    private MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Initialize the game registry
        Registry.initialize(this);

        // Instantiate the game presenter
        presenter = new MainPresenter(this);

        // Setup the game view
        setContentView(new GameView(this, presenter));
    }

    @Override
    public void onDisplayMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGameOver(int score, boolean isBest, Runnable callback) {

    }
}
