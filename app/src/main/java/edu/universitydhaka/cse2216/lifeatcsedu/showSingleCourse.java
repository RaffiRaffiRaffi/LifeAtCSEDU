package edu.universitydhaka.cse2216.lifeatcsedu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class showSingleCourse extends Activity {

    String courseCode;

    TextView textTitle,textCode;
    Button toBooks,toResources,toPastQuestions,toQuestions;

    private DatabaseReference courseDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_single_course);

        courseCode = getIntent().getStringExtra("coursecode");

        textTitle = findViewById(R.id.textTitle);
        textCode = findViewById(R.id.textCode);
        toBooks = findViewById(R.id.toBooks);
        toResources = findViewById(R.id.toResource);
        toPastQuestions = findViewById((R.id.toPastQuestions));
        toQuestions = findViewById(R.id.course_to_qa);

        courseDatabaseRef = FirebaseDatabase.getInstance().getReference("courses/"+courseCode);

        courseDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Course course = dataSnapshot.getValue(Course.class);
                textTitle.setText(course.getTitle());
                textCode.setText(course.getCode());

                toQuestions.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String> currentFilters = new ArrayList<>();
                        currentFilters.add(course.getCode());
                        Intent intent = new Intent(showSingleCourse.this,showQAList.class);
                        intent.putExtra("currentFilters",currentFilters);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        toBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showSingleCourse.this,showBooksList.class);
                intent.putExtra("courseCode",courseCode);
                startActivity(intent);
            }
        });

        toResources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showSingleCourse.this,showResourceList.class);
                intent.putExtra("courseCode",courseCode);
                startActivity(intent);
            }
        });

        toPastQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(showSingleCourse.this,showPastQuestionList.class);
                intent.putExtra("courseCode",courseCode);
                startActivity(intent);
            }
        });
    }
}
