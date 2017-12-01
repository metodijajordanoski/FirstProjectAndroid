package prvaaplikacija.vtoraaplikacija;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main4Activity extends AppCompatActivity {

    @BindView(R.id.internetTextField)
    TextView myTextField;
    NetworkChangeReceiver mNetworkRecevier = new NetworkChangeReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnConnection)
    public void clickConnecton(View view) {
        registerReceiver(mNetworkRecevier,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @OnClick(R.id.btnBack)
    public void clickBack(View view) {
        Intent intent = getIntent();
        String text=myTextField.getText().toString();
        intent.putExtra("EXTRA_STRING",text);
        setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = getIntent();
        String text=myTextField.getText().toString();
        intent.putExtra("EXTRA_STRING",text);
        setResult(RESULT_OK,intent);
        finish();
    }
}
