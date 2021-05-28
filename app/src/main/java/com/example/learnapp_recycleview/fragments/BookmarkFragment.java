package com.example.learnapp_recycleview.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnapp_recycleview.R;
import com.example.learnapp_recycleview.adapters.NowPlayingAdapter;
import com.example.learnapp_recycleview.models.NowPlaying;
import com.example.learnapp_recycleview.models.NowPlayingResponse;
import com.example.learnapp_recycleview.networks.Const;
import com.example.learnapp_recycleview.networks.MovieAPIClient;
import com.example.learnapp_recycleview.networks.MovieAPIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookmarkFragment extends Fragment implements NowPlayingAdapter.OnItemClick {
    private static final String TAG = "BookmarkFragment";
    private RecyclerView recyclerView;
    private NowPlayingAdapter nowPlayingAdapter;
    private List<NowPlaying> nowPlayings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookmark_fragment, container, false);
//        View vi2 = inflater.inflate(R.layout.history_fragment, container, false);
        recyclerView = view.findViewById(R.id.rv_bookmark);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        loadData();
        return view;
    }

    public void loadData() {
        MovieAPIInterface movieAPIInterface = MovieAPIClient.getRetrofit().create(MovieAPIInterface.class);
        Call<NowPlayingResponse> nowPlayingCall = movieAPIInterface.getNowPlaying(Const.API_KEY);
        nowPlayingCall.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                if (response.isSuccessful() && response.body().getNowPlayings() != null) {
                    nowPlayings = response.body().getNowPlayings();
                    nowPlayingAdapter = new NowPlayingAdapter(nowPlayings, BookmarkFragment.this);
                    recyclerView.setAdapter(nowPlayingAdapter);

                } else {
                    Toast.makeText(getActivity(),"Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(int position) {

    }
}
