package edu.universitydhaka.cse2216.lifeatcsedu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class QAListRecyclerViewAdapter extends RecyclerView.Adapter<QAListRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> QAKeys = new ArrayList<>();
    ArrayList<String> QATitles = new ArrayList<>();
    ArrayList<String> QAAskers = new ArrayList<>();
    ArrayList<String> QATimes = new ArrayList<>();
    Context context;

    public QAListRecyclerViewAdapter(ArrayList<String> QAKeys, ArrayList<String> QATitles, ArrayList<String> QAAskers, ArrayList<String> QATimes, Context context) {
        this.QAKeys = QAKeys;
        this.QATitles = QATitles;
        this.QAAskers = QAAskers;
        this.QATimes = QATimes;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listof_qa,viewGroup,false);
        QAListRecyclerViewAdapter.ViewHolder viewHolder = new QAListRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,final int i) {

        viewHolder.QATitle.setText(QATitles.get(i));
        viewHolder.QAAsker.setText(QAAskers.get(i));
        viewHolder.QATime.setText(QATimes.get(i));

        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,showSingleQuestion.class);
                intent.putExtra("qkey",QAKeys.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return QATitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView QATitle;
        TextView QATime;
        TextView QAAsker;
        RelativeLayout parentLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            QATitle = itemView.findViewById(R.id.qa_title);
            QATime = itemView.findViewById(R.id.qa_time);
            QAAsker = itemView.findViewById(R.id.qa_asker);
            parentLayout = itemView.findViewById(R.id.qa_element_layout);
        }
    }
}
