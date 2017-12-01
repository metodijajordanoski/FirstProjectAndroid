package prvaaplikacija.vtoraaplikacija;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.spinerGuest)
    Spinner spinner;
    @BindView(R.id.imePrezime)
    TextView imeprezime;
    @BindView(R.id.imageGender)
    ImageView genderPic;
    @BindView(R.id.btnMale)
    RadioButton male;
    @BindView(R.id.btnFemale)
    RadioButton female;
    @BindView(R.id.textInternet) TextView myTextField;
    public Korisnik korisnik2=new Korisnik();
    public Korisnik pomosenKorisnik;
    static final int REQUEST_CODE = 1000;
    static final int REQUEST_CODE1 = 2000;
    static final int REQUEST_CODE2 = 3000;
    static final int REQUEST_CODE3 = 4000;
    public Korisnik guest;
    ArrayList<Korisnik> lista = new ArrayList<Korisnik>();
    ArrayAdapter<Korisnik> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        if(intent.hasExtra("EXTRA")) {
            korisnik2 = (Korisnik) intent.getSerializableExtra("EXTRA");
            imeprezime.setText(korisnik2.getName()+"\n"+korisnik2.getLastname()+"");
            if(korisnik2.getGender()) {
                male.setChecked(true);
            } else if(!korisnik2.getGender())
                female.setChecked(true);

            if(female.isChecked()) {
                genderPic.setImageResource(R.drawable.mujer);
            } else if(male.isChecked())
                genderPic.setImageResource(R.drawable.man);

            female.setClickable(false);
            male.setClickable(false);
            pomosenKorisnik=korisnik2;
            lista.add(korisnik2);
        } else if (intent.hasExtra("EXTRA_GUEST")) {
            String k = intent.getStringExtra("EXTRA_GUEST");
            guest = new Korisnik();
            guest.setName(k);
            imeprezime.setText(k);
            guest.setLastname(k);
            guest.setUsername(k);
            guest.setGender(true);
            male.setChecked(true);
            lista.add(guest);
        }

        adapter = new ArrayAdapter<Korisnik>(this, R.layout.support_simple_spinner_dropdown_item,lista);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Korisnik korisnikPom = lista.get(position);
                imeprezime.setText(korisnikPom.getName().toString()+"\n"+korisnikPom.getLastname().toString());
                if(korisnikPom.getGender()) {
                    male.setChecked(true);
                } else if(!korisnikPom.getGender())
                    female.setChecked(true);

                if(female.isChecked()) {
                    genderPic.setImageResource(R.drawable.mujer);
                } else if(male.isChecked())
                    genderPic.setImageResource(R.drawable.man);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    @OnClick(R.id.btnkonekcija)
    public void clickConection(View view) {
        String s="";
        Intent i = new Intent(this, Main4Activity.class);
        i.putExtra("EXTRA_STRING",s);
        startActivityForResult(i,REQUEST_CODE3);
    }
    @Override
    public void onBackPressed() {
        Korisnik korisnikNazad = lista.get(spinner.getSelectedItemPosition());
        Intent intent2 = new Intent(this, Main2Activity.class);
        intent2.putExtra("EXTRA_USER",korisnikNazad);
        lista.remove(spinner.getSelectedItem());
        startActivityForResult(intent2,REQUEST_CODE);
//        super.onBackPressed();
    }


    @OnClick(R.id.btnEdit)
    public void clickEdit(View view) {
        Korisnik korisnik2 = (Korisnik) spinner.getSelectedItem();
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("EXTRA_USER",korisnik2);
        lista.remove(korisnik2);
        startActivityForResult(intent,REQUEST_CODE );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==REQUEST_CODE) {
            if(data.hasExtra("EXTRA_USER")) {
                Korisnik pomosen = new Korisnik();
                pomosen = (Korisnik) data.getSerializableExtra("EXTRA_USER");
                if(pomosen.getGender()) {
                    male.setChecked(true);
                } else if(!pomosen.getGender())
                    female.setChecked(true);
                imeprezime.setText(pomosen.getName()+"\n"+pomosen.getLastname()+"");
                if(female.isChecked()) {
                    genderPic.setImageResource(R.drawable.mujer);
                } else if(male.isChecked())
                    genderPic.setImageResource(R.drawable.man);

                female.setClickable(false);
                male.setClickable(false);
                lista.add(0,pomosen);
                adapter = new ArrayAdapter<Korisnik>(this, R.layout.support_simple_spinner_dropdown_item,lista);
                spinner.setAdapter(adapter);


            }
        } else if(data.hasExtra("EXTRA_USER1")) {
            Korisnik pomosen = new Korisnik();
            pomosen = (Korisnik) data.getSerializableExtra("EXTRA_USER1");
            if(pomosen.getGender()) {
                male.setChecked(true);
            } else if(!pomosen.getGender())
                female.setChecked(true);
            imeprezime.setText(pomosen.getName()+"\n"+pomosen.getLastname()+"");
            if(female.isChecked()) {
                genderPic.setImageResource(R.drawable.mujer);
            } else if(male.isChecked())
                genderPic.setImageResource(R.drawable.man);

            female.setClickable(false);
            male.setClickable(false);
            lista.add(0,pomosen);
            adapter = new ArrayAdapter<Korisnik>(this, R.layout.support_simple_spinner_dropdown_item,lista);
            spinner.setAdapter(adapter);


        } else if (data.hasExtra("EXTRA_STRING")) {
            String k = data.getStringExtra("EXTRA_STRING");
            myTextField.setText(k+"");
        }
    }

    @OnClick(R.id.btnADD)
    public void clickAdd(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        Korisnik user = new Korisnik();
        intent.putExtra("EXTRA_EMPTY",user);
        startActivityForResult(intent,REQUEST_CODE1);
    }
}
