package com.example.hash.gossipgirl.activity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.example.hash.gossipgirl.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by HaSh on 10/2/2016.
 */

public class MainActivity extends AppCompatActivity {

//    private static final String TAG = "Tag";
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private String mUserId;
    Button logout, subscribe;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = (Button) findViewById(R.id.btLogout);
        subscribe = (Button) findViewById(R.id.btSubscribe);


        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {
            //Logout feature
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFirebaseAuth.signOut();
                    loadLogInView();
                }
            });
//            mUserId = mFirebaseUser.getUid();

            String[] name = {"Serena van der Woodsen", "Blair Waldorf", "Dan Humphrey",
                    "Nate Archibald", "Chuck Bass", "Vanessa Abrams", "Jenny Humphrey",
                    "Eric van der Woodsen", " Lily van der Woodsen", "Rufus Humphrey"};

            final ArrayAdapter<String> adapter
                    = new ArrayAdapter<>(this, R.layout.list_view, name);
            final ListView listview = (ListView) findViewById(R.id.listView);
            listview.setAdapter(adapter);

            //Contain values of checkboxes
            final List<String> selection = new ArrayList<>(adapter.getCount());

            //When user subscribes then notification is send
            subscribe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendNotification("Subscribed!");
                    for(int i=0 ; i<adapter.getCount() ; i++){
                        if(i==0) {
                            selection.clear();
                        }
                        String obj = adapter.getItem(i);
                        View cs = adapter.getView(i, findViewById(R.id.label), listview);
//                        Log.i("Class!!", cs.getClass().toString());
                        if(((CheckBox) cs).isChecked()) {
                            FirebaseMessaging.getInstance().subscribeToTopic(obj.replaceAll("\\s+",""));
                            selection.add("1");
                        }
                        else {
                            FirebaseMessaging.getInstance().unsubscribeFromTopic(obj.replaceAll("\\s+",""));
                            selection.add("0");
                        }
                    }
                    FirebaseDatabase.getInstance().getReference().setValue(selection);
                    Log.i("Subscribed", "Subscribed!!!!");
                }
            });
        }
    }

//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "Refreshed token: " + refreshedToken);
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
//    }

   //To send Notifications
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Character's Whereabouts")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    //To go to login activity
    private void loadLogInView() {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}
