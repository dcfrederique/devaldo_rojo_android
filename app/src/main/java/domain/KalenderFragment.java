package domain;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseQuery;
import java.util.ArrayList;
import java.util.List;
import customcards.CalendarCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.view.CardListView;
import minivoetbal.devaldorojo.R;

/**
 * Created by Fre on 31/08/2014.
 */
public class KalenderFragment extends Fragment {
    private List<MatchCalendar> mMatchCalendars;
    private int aantal=0;
    private int nr=0;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.kalender_fragment_layout,container, false);
        aantal=0;
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
            ParseQuery<MatchCalendar> query = ParseQuery.getQuery(MatchCalendar.class);
            query.orderByAscending("Datum");
            query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);

            query.findInBackground(new FindCallback<MatchCalendar>() {

                @Override
                public void done(List<MatchCalendar> matchCalendars, com.parse.ParseException e) {
                    mMatchCalendars=matchCalendars;
                    progressDialog.dismiss();
                    makeCards();
                }
            });
            return null;
        }
    }

    private void makeCards() {
        int i =0;
        ArrayList<Card> cards = new ArrayList<Card>();
        for(MatchCalendar mc : mMatchCalendars){

            CalendarCard c = new CalendarCard(getActivity());
            c.setMatchData(mc.getMatchData());
            c.setDate(mc.getDate());
            c.setScore(mc.getScore());
            c.setSporthal(mc.getSporthal());
            c.setAanvangsUur(mc.getAanvangsUur());
            c.setAanwezigen(mc.getAanwezigen());
            c.setAanwezigenAantal(mc.getAanwezigen().size());
            c.setBackgroundColor(mc.getColorHeader());
            if(mc.getScore()==null && aantal==0){
                c.setBackgroundColor("#33b5e5");
                aantal++;
                nr=i;

            }
            cards.add(c);
            i++;
        }
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);
        CardListView listView = (CardListView) getActivity().findViewById(R.id.myList);
        if (listView != null) {

            listView.setAdapter(mCardArrayAdapter);
            listView.setSelection(--nr);
        }
        progressDialog.dismiss();

    }
}
