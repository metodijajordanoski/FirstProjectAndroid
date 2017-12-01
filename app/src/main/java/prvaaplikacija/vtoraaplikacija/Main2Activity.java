package prvaaplikacija.vtoraaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.editName)
    EditText name;
    @BindView(R.id.editLastName)
    EditText lastname;
    @BindView(R.id.radioGrupa1)
    RadioGroup RadioGrupa;
    @BindView(R.id.editUserName)
    EditText username;
    @BindView(R.id.female) RadioButton femaleBtn;
    @BindView(R.id.male1) RadioButton maleBtn;

    static int REQUEST_CODE = 1000;

    Boolean gender;
    Korisnik korisnik;
    Korisnik korisnikEdit=new Korisnik();
    Intent intentPomosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);

        intentPomosen= getIntent();
        if(intentPomosen.hasExtra("EXTRA_USER")) {
            korisnikEdit = (Korisnik) intentPomosen.getSerializableExtra("EXTRA_USER");
            name.setText(korisnikEdit.getName().toString());
            lastname.setText(korisnikEdit.getLastname().toString());
            username.setText(korisnikEdit.getUsername().toString());
            if(korisnikEdit.getGender()) {
                maleBtn.setChecked(true);
            }else if(!korisnikEdit.getGender()) {
                femaleBtn.setChecked(true);
            }
        }



    }

    @OnClick(R.id.btnNext)
    public void clickNext(View view) {
        intentPomosen = getIntent();
        if(intentPomosen.hasExtra("EXTRA_USER")) {
            korisnikEdit = (Korisnik) intentPomosen.getSerializableExtra("EXTRA_USER");
            if(maleBtn.isChecked()) {
                gender=true;
                korisnikEdit.setGender(gender);
            }
            if(femaleBtn.isChecked()) {
                gender=false;
                korisnikEdit.setGender(gender);
            }
            korisnikEdit.setName(name.getText().toString());
            korisnikEdit.setLastname(lastname.getText().toString());
            korisnikEdit.setUsername(username.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("EXTRA_USER",korisnikEdit);
            setResult(RESULT_OK,intent);
            finish();

        } else if(intentPomosen.hasExtra("EXTRA_EMPTY")) {
            korisnikEdit = new Korisnik();
            if(maleBtn.isChecked()) {
                gender=true;
                korisnikEdit.setGender(gender);
            }
            if(femaleBtn.isChecked()) {
                gender=false;
                korisnikEdit.setGender(gender);
            }
            korisnikEdit.setName(name.getText().toString());
            korisnikEdit.setLastname(lastname.getText().toString());
            korisnikEdit.setUsername(username.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("EXTRA_USER1",korisnikEdit);
            setResult(RESULT_OK,intent);
            finish();
        }else {
            korisnik = new Korisnik();
            if(maleBtn.isChecked()) {
                gender=true;
                korisnik.setGender(gender);
            }
            if(femaleBtn.isChecked()) {
                gender=false;
                korisnik.setGender(gender);
            }
            korisnik.setName(name.getText().toString());
            korisnik.setLastname(lastname.getText().toString());
            korisnik.setUsername(username.getText().toString());
            Intent intent = new Intent(this, Main3Activity.class);
            intent.putExtra("EXTRA", korisnik);
            startActivity(intent);
        }
    }

//    @Override
//    public void onBackPressed() {
//        Korisnik korisnikBack = new Korisnik();
//        Intent i = new Intent(this,MainActivity.class);
//        i.putExtra("EXTRA5",korisnikBack);
//        startActivityForResult(i,REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(resultCode==RESULT_OK && requestCode==REQUEST_CODE) {
//            if(data.hasExtra("EXTRA5")) {
//                Korisnik pomosen = new Korisnik();
//                pomosen = (Korisnik) data.getSerializableExtra("EXTRA5");
//            }
//        }
//    }
}
