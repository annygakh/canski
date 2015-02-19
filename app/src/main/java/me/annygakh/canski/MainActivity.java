package me.annygakh.canski;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class MainActivity extends ActionBarActivity {

    private TextView mWhistlerStatus, mBlackStatus, mGrouseStatus, mCypressStatus, mSeymourStatus;
    private BootstrapButton mAboutButton, mReport;


    public final static String ERROR = "ERROR",
                                CLOSED = "CLOSED",
                                OPEN = "OPEN";

    Firebase myFirebaseRef;

    // no need for manual update, because initially it updates by itself.
//    public void manual_update(){
//
//        mGrouseStatus.setText(myFirebaseRef.child("grouse").toString());
//        mWhistlerStatus.setText(myFirebaseRef.child("whistler").getKey());
//        mBlackStatus.setText(myFirebaseRef.child("blackcomb").getKey());
//        mCypressStatus.setText(myFirebaseRef.child("cypress").getKey());
//        mSeymourStatus.setText(myFirebaseRef.child("seymour").getKey()
//        );
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);

        myFirebaseRef = new Firebase("https://canski.firebaseio.com/");

        mWhistlerStatus = (TextView) findViewById(R.id.whistler_status);
        mBlackStatus = (TextView) findViewById(R.id.blackcomb_status);
        mGrouseStatus = (TextView) findViewById(R.id.grouse_status);
        mCypressStatus = (TextView) findViewById(R.id.cypress_status);
        mSeymourStatus = (TextView) findViewById(R.id.seymour_status);


//        manual_update();

//
//        mRefreshButton = (Button) findViewById(R.id.refresh_button);
//        mRefreshButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                manual_update();
//            }
//        });


    mAboutButton = (BootstrapButton) findViewById(R.id.about_button);
        getSupportFragmentManager();

    mAboutButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getSupportFragmentManager(), "about_dialog");
        }
    });


    addValueEventListeners();





    }

    private void addValueEventListeners(){
        myFirebaseRef.child("grouse").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String data = (String) snapshot.getValue();

                if (data.equals("-1"))
                    mGrouseStatus.setText(ERROR);
                else if (data.equals("0"))
                    mGrouseStatus.setText(CLOSED);
                else if (data.equals("OPEN"))
                    mGrouseStatus.setText(OPEN);
                else
                    mGrouseStatus.setText(ERROR); //this case wont happen
            }

            @Override public void onCancelled(FirebaseError error) {
                mGrouseStatus.setText(ERROR);
            }

        });

        ///


        myFirebaseRef.child("whistler").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String data = (String) snapshot.getValue();

                if (data.equals("-1"))
                    mWhistlerStatus.setText(ERROR);
                else if (data.equals("0"))
                    mWhistlerStatus.setText(CLOSED);
                else //if (data.equals("OPEN"))
                    mWhistlerStatus.setText(data); //this case wont happen
            }

            @Override public void onCancelled(FirebaseError error) {
                mWhistlerStatus.setText(ERROR);
            }

        });

        myFirebaseRef.child("blackcomb").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String data = (String) snapshot.getValue();

                if (data.equals("-1"))
                    mBlackStatus.setText(ERROR);
                else if (data.equals("0"))
                    mBlackStatus.setText(CLOSED);
                else //if (data.equals("OPEN"))
                    mBlackStatus.setText(data); //this case wont happen
            }

            @Override public void onCancelled(FirebaseError error) {
                mBlackStatus.setText(ERROR);
            }

        });


        myFirebaseRef.child("cypress").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String data = (String) snapshot.getValue();

                if (data.equals("-1"))
                    mCypressStatus.setText(ERROR);
                else if (data.equals("0"))
                    mCypressStatus.setText(CLOSED);
                else if (data.equals("OPEN"))
                    mCypressStatus.setText(OPEN);
                else
                    mCypressStatus.setText(ERROR); //this case wont happen
            }

            @Override public void onCancelled(FirebaseError error) {
                mCypressStatus.setText(ERROR);
            }

        });

        myFirebaseRef.child("seymour").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String data = (String) snapshot.getValue();

                if (data.equals("-1"))
                    mSeymourStatus.setText(ERROR);
                else if (data.equals("0"))
                    mSeymourStatus.setText(CLOSED);
                else if (data.equals("OPEN"))
                    mSeymourStatus.setText(OPEN);
                else
                    mSeymourStatus.setText(ERROR); //this case wont happen
            }

            @Override public void onCancelled(FirebaseError error) {
                mSeymourStatus.setText(ERROR);
            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
