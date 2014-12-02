package domain;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import customcards.CalendarCard;
import customcards.StatistiekenCard;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.prototypes.CardSection;
import it.gmariotti.cardslib.library.prototypes.SectionedCardAdapter;
import it.gmariotti.cardslib.library.view.CardListView;
import minivoetbal.devaldorojo.R;

/**
 * Created by Fre on 31/08/2014.
 */
public class StatistiekenFragment extends Fragment {
    private List<Player> mPlayerList;
    private ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statistieken_fragment_layout, container, false);
        new RemoteDataTask().execute();
        setRetainInstance(true);
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
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }



        @Override
        protected Void doInBackground(Void... voids) {
            ParseQuery<Player> query = ParseQuery.getQuery(Player.class);
            query.orderByDescending("doelpunten");
            query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
            query.findInBackground(new FindCallback<Player>() {

                @Override
                public void done(List<Player> playerList, ParseException e) {
                    mPlayerList = playerList;
                    progressDialog.dismiss();
                    makeCards();
                }
            });
            return null;
        }
    }

    private void makeCards() {

        ArrayList<Card> cards = new ArrayList<Card>();
        Collections.sort(mPlayerList, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                return Integer.parseInt(rhs.getAantalDoelpunten()) - Integer.parseInt(lhs.getAantalDoelpunten());
            }
        });
        haalSpelersOp(cards,false);
        Collections.sort(mPlayerList, new Comparator<Player>() {
            @Override
            public int compare(Player lhs, Player rhs) {
                return Integer.parseInt(rhs.getAantalAssists()) - Integer.parseInt(lhs.getAantalAssists());
            }
        });
        //Standard array
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        // Define your sections
        List<CardSection> sections = new ArrayList<CardSection>();
        sections.add(new CardSection(0, "TOPSCHUTTER"));
        CardSection[] dummy = new CardSection[sections.size()];

        //Define your Sectioned adapter
        SectionedCardAdapter mAdapter = new SectionedCardAdapter(getActivity(), mCardArrayAdapter);
        mAdapter.setCardSections(sections.toArray(dummy));
        CardListView listView = (CardListView) getActivity().findViewById(R.id.card_listview);
        if (listView != null) {
            //Use the external adapter.
            listView.setExternalAdapter(mAdapter, mCardArrayAdapter);
        }
        progressDialog.dismiss();
    }

    private void haalSpelersOp(ArrayList<Card> cards, boolean assistenKoning) {
        for (Player p : mPlayerList) {
            StatistiekenCard c = new StatistiekenCard(getActivity());
            c.setPhotoFile(p.getPhotoFile());
            c.setName(p.getName());
            c.setAantal("Aantal doelpunten: "+p.getAantalDoelpunten());
            c.setGemiddelde(p.getGemiddeldAantalDoelpunten());
            c.setAantalWedstrijden(p.getAantalWedstrijden()+" wedstrijden");
            cards.add(c);
        }
    }
}
