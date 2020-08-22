package com.example.mindcraft_main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detected#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detected extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public detected() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detected.
     */
    // TODO: Rename and change types and number of parameters
    public static detected newInstance(String param1, String param2) {
        detected fragment = new detected();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView detectstore;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirestoreRecyclerAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_detected, container, false);

        detectstore=view.findViewById(R.id.detected_list);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        Query query = db.collection("Authorities").document(mUser.getUid()).collection("Self_detected_problems");

        FirestoreRecyclerOptions<detection_details> options=new FirestoreRecyclerOptions.Builder<detection_details>()
                .setQuery(query,detection_details.class)
                .build();
        adapter= new FirestoreRecyclerAdapter<detection_details, detections_view_holder>(options) {
            @NonNull
            @Override
            public detections_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view1=LayoutInflater.from(parent.getContext()).inflate(R.layout.detection_sample,parent,false);

                return new detections_view_holder(view1);
            }

            @Override
            protected void onBindViewHolder(@NonNull detections_view_holder holder, int position, @NonNull detection_details model) {

                holder.number.setText(model.getNumber_plate());
                holder.time.setText(model.getTime_stamp());
                holder.classes.setText(model.getDetected_classes());
                holder.ward.setText(model.getWard_no());

            }
        };
        detectstore.setLayoutManager(new LinearLayoutManager(getContext()));
        detectstore.setAdapter(adapter);


        return view;
    }

    private class detections_view_holder extends RecyclerView.ViewHolder {
         private TextView ward,classes,time,number;
        public detections_view_holder(@NonNull View itemView) {
            super(itemView);
            ward=itemView.findViewById(R.id.ward);
            classes=itemView.findViewById(R.id.classes);
            time=itemView.findViewById(R.id.time);
            number=itemView.findViewById(R.id.number_plate);
        }
    }
}