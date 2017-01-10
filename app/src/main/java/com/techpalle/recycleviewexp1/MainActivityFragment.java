package com.techpalle.recycleviewexp1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    EditText et1,et2;
    Button b1;
    RecyclerView rv;
    MyAdapter m;
    ArrayList<Movie>al;
    int count;
        //create a inner class for fragment adapter

        public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                //load row.xml and pass row xml to below view holder
                View v=getActivity().getLayoutInflater().inflate(R.layout.row,parent,false);
                ViewHolder viewHolder=new ViewHolder(v);
                //return viewholder
                return viewHolder;
            }


            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                //based on load data from arraay list(source)
                Movie movie=al.get(position);
                //fill data onto above view holder
                holder.tv1.setText(movie.getSno());
                holder.tv2.setText(movie.getActor());
                holder.tv3.setText(movie.getMovie());

            }
            @Override
            public int getItemCount() {
                return al.size();
            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                //view holder
                public TextView tv1,tv2,tv3;
                public ViewHolder(View itemView) {
                    super(itemView);
                    tv1= (TextView) itemView.findViewById(R.id.textview1);
                    tv2= (TextView) itemView.findViewById(R.id.textview2);
                    tv3= (TextView) itemView.findViewById(R.id.textview3);
                }
            }
        }

    public MainActivityFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View v= inflater.inflate(R.layout.fragment_main, container, false);
        et1= (EditText) v.findViewById(R.id.edittext1);
        et2= (EditText) v.findViewById(R.id.edittext2);
        rv= (RecyclerView) v.findViewById(R.id.recycle1);
        b1= (Button) v.findViewById(R.id.button1);
        al=new ArrayList<Movie>();
        m=new MyAdapter();//this is recycle view adapter
        rv.setAdapter(m);//estblish link between recycle view and adapter
        //prepare a layoutmanager and link with recycleview
        //i will prepare linearlayout manager for recycle view
       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),
               LinearLayoutManager.VERTICAL,false);
       // GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        //link recycleview with linear layout manager
        rv.setLayoutManager(linearLayoutManager);
       // rv.setLayoutManager(gridLayoutManager);
               b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String actor=et1.getText().toString();
                String moviename=et2.getText().toString();
                Movie mymovie=new Movie();
                mymovie.setSno(""+count);
                mymovie.setActor(actor);
                mymovie.setMovie(moviename);
                al.add(mymovie);
                m.notifyItemInserted(count-1);//also write (al.size-1)
                et1.setText("");
                et2.setText("");
                et1.requestFocus();
            }
        });

        return v;
    }
}
