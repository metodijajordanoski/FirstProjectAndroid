package prvaaplikacija.vtoraaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



    }


    @OnClick(R.id.btnLogIn)
    public void OnLogInClick(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.textviewGuest)
    public void clickGuest(View view) {
        String s="Guest";
        Intent i = new Intent(this, Main3Activity.class);
        i.putExtra("EXTRA_GUEST",s);
        startActivity(i);
    }
}
