package domain;

import android.app.Fragment;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import minivoetbal.devaldorojo.R;

/**
 * Created by Frederique on 3/09/2014.
 */
public class RankingFragment extends Fragment {
    private ProgressDialog progressDialog;
    private List<Ranking> teamRankingList;
    private RankingAdapter rankingAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ranking_fragment_layout, container, false);
        teamRankingList=new ArrayList<Ranking>();
        rankingAdapter=new RankingAdapter(getActivity(),R.layout.listview_row_item,teamRankingList);
        ListView lv =(ListView)rootView.findViewById(R.id.list);
        lv.setAdapter(rankingAdapter);
        lv.setClickable(false);
       // setListAdapter(rankingAdapter);
        new RemoteDataTask().execute();
        return rootView;
    }

    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(true);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ParseQuery<Ranking> query = ParseQuery.getQuery(Ranking.class);
            query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);

            query.findInBackground(new FindCallback<Ranking>() {

                @Override
                public void done(List<Ranking> rankings, com.parse.ParseException e) {
                    teamRankingList=rankings;
                    Collections.sort(rankings);
                    rankingAdapter.clear();
                    rankingAdapter.addAll(rankings);
                    rankingAdapter.notifyDataSetChanged();
                    progressDialog.dismiss();
                }
            });
            return null;

        }
    }


}