package com.testsample.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import com.testsample.R;
import com.testsample.VideoActivity;
import com.testsample.dto.Modelurl;

import java.util.ArrayList;

/**
 * Created by Ananth kumar on 29-06-2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    ArrayList arrayList;
    Context scontext;
    private int selectedposition = -1;
    private RecyclerView recyclerView;

    public VideoAdapter(VideoActivity mainActivity, ArrayList<Modelurl> arraylist, RecyclerView recyclerView) {
        this.arrayList = arraylist;
        this.scontext = mainActivity;
        this.recyclerView=recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listvideos, parent, false);

        return new ViewHolder(view);
    }


    private ViewHolder holders;


   /* public void onScrollStateChaned() {

        holders.videoView.stopPlayback();
        holders.videoView.pause();
        holders.play.setImageResource(R.drawable.play);
        notifyDataSetChanged();


    }*/




    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Modelurl murl = (Modelurl) arrayList.get(position);
        final Uri videolink = Uri.parse(murl.getUrllink());
        /*if (VideoActivity.scrollstatus == 1) {
            holder.videoView.requestFocus();
            holder.videoView.stopPlayback();
            holder.play.setImageResource(R.drawable.playicon);
            murl.setPlaystatus("0");
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
               holder.videoView.stopPlayback();
                holder.play.setImageResource(R.drawable.playicon);
            }
        });

        holder.linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                holders = holder;
                VideoActivity.scrollstatus = 0;
                holder.videoView.setVideoURI(videolink);
                if (murl.getPlaystatus().equals("0")) {
                    murl.setPlaystatus("1");
                    selectedposition = position;
                    holder.play.setImageResource(R.drawable.pauseicon);
                    notifyDataSetChanged();
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            holder.play.setVisibility(View.GONE);
//                        }
//                    }, 1000);
                    holder.videoView.setVideoURI(videolink);
                    holder.videoView.requestFocus();
                    holder.videoView.start();
                } else {
                    holder.play.setVisibility(View.VISIBLE);
                    holder.play.setImageResource(R.drawable.playicon);
                    murl.setPlaystatus("0");
                    notifyItemChanged(position);
                    holder.videoView.requestFocus();
                    holder.videoView.pause();
                }
            }
        });*/
        if (selectedposition != -1 && selectedposition != position) {
            holder.videoView.requestFocus();
            holder.videoView.stopPlayback();
            holder.play.setImageResource(R.drawable.playicon);
            murl.setPlaystatus("0");
        }

        if (murl.getPlaystatus().equals("0")) {
            holder.play.setVisibility(View.VISIBLE);
            holder.play.setImageResource(R.drawable.playicon);
            holder.videoView.pause();
        } else {
            holder.play.setImageResource(R.drawable.pauseicon);
            holder.videoView.start();

        }
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        FrameLayout linearLayout;
        private ImageView play;

        public ViewHolder(View itemView) {
            super(itemView);
            videoView = (VideoView) itemView.findViewById(R.id.lists);
            linearLayout = (FrameLayout) itemView.findViewById(R.id.listlayout);
            play = (ImageView) itemView.findViewById(R.id.play);
        }
    }
}
