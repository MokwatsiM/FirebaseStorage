package com.example.mlab.firebasestorage;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewUploads extends AppCompatActivity {
    ListView listView;

    //DatabaseReference to get uploads
    DatabaseReference mDatabaseReference;

    //list to get uploads
    List<Uploads> uploadsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_uploads);

       uploadsList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listview);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uploads uploads = uploadsList.get(position);

                //Opening the upload file in the browser using the upload url

                Intent in = new Intent(Intent.ACTION_VIEW);
                in.setData(Uri.parse(uploads.getUrl()));
                startActivity(in);
            }
        });

        //getting the database reference
        mDatabaseReference = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //retrieving uploads data from Firebase database
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapShot: dataSnapshot.getChildren())
                {
                    Uploads uploads = postSnapShot.getValue(Uploads.class);
                    uploadsList.add(uploads);

                }
                ArrayList<String> uploadsName = new ArrayList<>();
                for(int i=0;i<uploadsList.size();i++)
                {
                    uploadsName.add(uploadsList.get(i).getName());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,uploadsName);

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
