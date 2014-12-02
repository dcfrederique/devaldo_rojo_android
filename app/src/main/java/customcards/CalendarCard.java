package customcards;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.ViewToClickToExpand;
import minivoetbal.devaldorojo.R;

/**
 * Created by Fre on 29/08/2014.
 */
public class CalendarCard extends Card {
    protected TextView mMatchDataTitle;
    protected TextView mScoreTitle;
    private String matchData;
    private String score;
    private String date;
    private TextView mDateTitle;
    private TextView mSporthalTitle;
    private TextView mAanvangsuurTitle;
    private String aanvangsUur;
    private List<String> aanwezigen;
    private TextView mAanwezigenTitle;
    private int aanwezigenAantal;
    private String sporthal;
    private String backgroundColor;



    public CalendarCard(Context context) {
        this(context, R.layout.customcard_layout);
    }
    public CalendarCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    public void setSporthal(String sporthal) {
        this.sporthal = sporthal;
    }
    public void setAanvangsUur(String aanvangsUur) {
        this.aanvangsUur = aanvangsUur;
    }
    public void setMatchData(String matchData) {
        this.matchData = matchData;
    }
    public void setAanwezigen(List<String> aanwezigen) {
        this.aanwezigen = aanwezigen;
        init();
    }
    public void setAanwezigenAantal(int aanwezigenAantal) {
        this.aanwezigenAantal = aanwezigenAantal;
    }
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public void setScore(String score) {
        this.score = score;
    }
    public void setDate(String date){
        this.date=date;
    }
    private void init() {
        //Click to expand
        CardExpand expand = new CardExpand(getContext());
        //Set inner title in Expand Area
        String tempString = "AANWEZIG";
        for(String s : aanwezigen){
            tempString+="\n"+s;
        }
        expand.setTitle(tempString);
        addCardExpand(expand);
        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .highlightView(false)
                        .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);
       setViewToClickToExpand(viewToClickToExpand);


    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mMatchDataTitle = (TextView) parent.findViewById(R.id.textview_matchData);
        mScoreTitle = (TextView) parent.findViewById(R.id.textview_score);
        mDateTitle = (TextView) parent.findViewById(R.id.textview_date);
        mSporthalTitle = (TextView) parent.findViewById(R.id.textview_sporthal);
        mAanvangsuurTitle = (TextView) parent.findViewById(R.id.textview_aanvangsuur);
        mAanwezigenTitle=(TextView)parent.findViewById(R.id.textview_aantalAanwezig);
        LinearLayout ll = (LinearLayout)parent.findViewById(R.id.backgroundcolorheader);
        ll.setBackgroundColor(Color.parseColor(backgroundColor));

       if (mAanwezigenTitle!=null)
            mAanwezigenTitle.setText(""+aanwezigenAantal);
        if (mMatchDataTitle!=null)
            mMatchDataTitle.setText(matchData);
        if (mAanvangsuurTitle!=null)
            mAanvangsuurTitle.setText(aanvangsUur);
        if (mScoreTitle!=null)
            mScoreTitle.setText(score);
        if (mDateTitle!=null)
            mDateTitle.setText(date);
        if (mSporthalTitle!=null)
            mSporthalTitle.setText(sporthal);


    }

}
