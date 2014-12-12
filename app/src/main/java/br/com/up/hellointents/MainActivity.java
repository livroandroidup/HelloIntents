package br.com.up.hellointents;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;


/**
 * Main.
 */
public class MainActivity extends ActionBarActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            findViewById(R.id.btSms).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new
                            Intent(Intent.ACTION_SENDTO, Uri.parse("sms:99887766"));
                    startActivity(intent);
                }
            });
            findViewById(R.id.btLigar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new
                            Intent(Intent.ACTION_CALL,Uri.parse("tel:99887766"));
                    startActivity(intent);
                }
            });
            findViewById(R.id.btSite).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new
                            Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
                    startActivity(intent);
                }
            });
            findViewById(R.id.btMaps).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String latitude = "-25.451664";
                    String longitude = "-49.350114";
                    String zoom = "15";
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("geo:"+latitude+","+longitude+"?z="+zoom ));
                    startActivity(i);
                }
            });
            findViewById(R.id.btContatos).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("content://contacts/people/1"));
                    startActivity(i);
                }
            });

            findViewById(R.id.btScan).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                        intent.putExtra("SCAN_MODE","QR_CODE_MODE");
                        startActivityForResult(intent, 1);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getBaseContext(),"Instale o barcode reader", Toast.LENGTH_LONG).show();
                    }
                }
            });


        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode == 1) {
            String result = data.getStringExtra("SCAN_RESULT");

            Toast.makeText(this,"Scan: " + result, Toast.LENGTH_LONG).show();
        }
    }
}