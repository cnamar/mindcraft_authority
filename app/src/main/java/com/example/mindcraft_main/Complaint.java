package com.example.mindcraft_main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Complaint#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Complaint extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Complaint() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Complaint.
     */
    // TODO: Rename and change types and number of parameters
    public static Complaint newInstance(String param1, String param2) {
        Complaint fragment = new Complaint();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView complaintstore;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private FirestoreRecyclerAdapter adapter;
    private Spinner spinner;

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
    int f;
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
        View view=inflater.inflate(R.layout.fragment_complaint, container, false);
        complaintstore=view.findViewById(R.id.complaint_list);
        spinner=view.findViewById(R.id.spinner);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),R.array.spinner_values,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter1);
       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(adapterView.getItemAtPosition(i).equals("new"))
               {
                   Log.d("Tagimportant",String.valueOf(getF())+"ivide");
                   Query query = db.collection("Authorities").document(mUser.getUid()).collection("Complaints").whereEqualTo("Status", 0);

                   FirestoreRecyclerOptions<complaint_details> options=new FirestoreRecyclerOptions.Builder<complaint_details>()
                           .setQuery(query,complaint_details.class)
                           .build();
                   adapter= new FirestoreRecyclerAdapter<complaint_details, ComplaintsViewHolder>(options) {

                       @NonNull
                       @Override
                       public ComplaintsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                           View view1=LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_sample,parent,false);
                           return new ComplaintsViewHolder(view1);
                       }

                       @Override
                       protected void onBindViewHolder(@NonNull ComplaintsViewHolder holder, final int position, @NonNull final complaint_details model) {
                           holder.landmark.setText(model.getLandmark());
                           holder.ward.setText(model.getWard_no());
                           holder.description.setText(model.getProblem_description());
                           holder.type.setText(model.problem_type);
                           holder.date.setText(model.getDate());


                       }
                   };
                   complaintstore.setLayoutManager(new LinearLayoutManager(getContext()));
                   complaintstore.setAdapter(adapter);
                   adapter.startListening();



               }
               else
               {
                   Log.d("Tagimportant",String.valueOf(getF())+"ivide");
                   Query query = db.collection("Authorities").document(mUser.getUid()).collection("Complaints").whereEqualTo("Status", 1);

                   FirestoreRecyclerOptions<complaint_details> options=new FirestoreRecyclerOptions.Builder<complaint_details>()
                           .setQuery(query,complaint_details.class)
                           .build();
                   adapter= new FirestoreRecyclerAdapter<complaint_details, ComplaintsViewHolder>(options) {
                       @NonNull
                       @Override
                       public ComplaintsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                           View view1=LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_sample,parent,false);
                           return new ComplaintsViewHolder(view1);
                       }

                       @Override
                       protected void onBindViewHolder(@NonNull ComplaintsViewHolder holder, int position, @NonNull final complaint_details model) {
                           holder.landmark.setText(model.getLandmark());
                           holder.ward.setText(model.getWard_no());
                           holder.description.setText(model.getProblem_description());
                           holder.type.setText(model.problem_type);
                           holder.date.setText(model.getDate());

                       }
                   };
                   complaintstore.setLayoutManager(new LinearLayoutManager(getContext()));
                   complaintstore.setAdapter(adapter);

                   adapter.startListening();


               }

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
    return view;

    }


    private class ComplaintsViewHolder extends RecyclerView.ViewHolder{
        private TextView type,description,ward,landmark,time,date;
        Button change;

        public ComplaintsViewHolder(@NonNull View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.type);
            description=itemView.findViewById(R.id.description);
            ward=itemView.findViewById(R.id.ward);
            landmark=itemView.findViewById(R.id.landmark);
            time=itemView.findViewById(R.id.time);
            date=itemView.findViewById(R.id.date);
            change=itemView.findViewById(R.id.button);

        }
    }

}