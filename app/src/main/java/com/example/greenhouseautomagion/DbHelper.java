package com.example.greenhouseautomagion;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbHelper {

    private FirebaseFirestore database;
    public long collectionSize;


    public interface MyCallback{
        void onRecive(ArrayList<Place> places);
    }
    public void postToDb(Place currentPlace) {



        try{
            Log.d("fa",String.valueOf(currentPlace.isVentilation()));
            database = FirebaseFirestore.getInstance();
            database.collection("palaces")
                    .add(currentPlace)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("fa", "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    });

        }
        catch (Exception e){
            Log.e("fa", e.getMessage());
        }

    }

    public void getPlace(MyCallback callback){
        try{
            database = FirebaseFirestore.getInstance();
            database.collection("palaces")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                ArrayList<Map<String,Object>> questionsMapList = new ArrayList<Map<String,Object>>();
                                ArrayList<Place> allPlaces = new ArrayList<Place>();
                                for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                                    Log.d("fa",documentSnapshot.getData().toString());
                                    Place place = new Place();
                                    place.setId(documentSnapshot.getId());
                                    place = documentSnapshot.toObject(Place.class);
                                    place.setId(documentSnapshot.getId());
                                    place.setblinding((boolean)documentSnapshot.get("blinding"));
                                    place.setventilation((boolean) documentSnapshot.get("ventilation"));
                                    allPlaces.add(place);
                                }
                                callback.onRecive(allPlaces);
                            }else {
                                Log.e("fa","error: ", task.getException());
                            }
                        }
                    });

        }
        catch (Exception e){
            Log.e("fa", e.getMessage());
        }
    }

    public void deleteDoc(String id){
        database = FirebaseFirestore.getInstance();
        DocumentReference docRef = database.collection("palaces").document(id);

        docRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    // Document successfully deleted
                    //TODO add toast
                } else {
                    // An error occurred
                    Log.d("Firestore", "Error deleting document: " + task.getException());
                    //TODO add toast
                }
            }
        });
    }

    public void updateDoc(String id, Place place){
        database = FirebaseFirestore.getInstance();
        DocumentReference docRef = database.collection("palaces").document(id);
// Add more field-value pairs for the updates
        Map<String, Object> updates = new HashMap<>();
        updates.put("activeDays", place.getActiveDays());
        updates.put("watterVolume", place.getWatterVolume());
        updates.put("blinding", place.isBlinding());
        updates.put("ventilation", place.isVentilation());
        updates.put("startTime", place.getStartTime());
        updates.put("placeName", place.getPlaceName());
        docRef.update(updates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Document successfully updated
                            //TODO toast
                        } else {
                            // An error occurred
                            //TODO toast
                            Log.d("Firestore", "Error updating document: " + task.getException());
                        }
                    }
                });
    }
}
