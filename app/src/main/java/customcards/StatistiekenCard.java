package customcards;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseImageView;

import it.gmariotti.cardslib.library.internal.Card;
import minivoetbal.devaldorojo.R;

/**
 * Created by Frederique on 2/09/2014.
 */
public class StatistiekenCard extends Card {

    private ParseFile photoFile;
    private ParseImageView imageView;
    private String name;
    private String aantal;
    private int plaats;
    private String gemiddeldAantalDoelpunten;
    private String aantalWedstrijden
            ;

    public StatistiekenCard(Context context) {
        this(context, R.layout.statistieken_card_layout);
    }

    public StatistiekenCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    public void setPhotoFile(ParseFile photoFile) {
        this.photoFile = photoFile;
        
    }
    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        imageView = (ParseImageView) parent.findViewById(R.id.image_player);
        imageView.setParseFile(photoFile);
        imageView.loadInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] bytes, ParseException e) {
                //nothing to do
                if (e != null)
                    Log.e("memmo", e.getMessage());
            }
        });
        TextView nameView =(TextView) parent.findViewById(R.id.textview_name);
        nameView.setText(name);
        TextView aantalView =(TextView) parent.findViewById(R.id.textview_aantal);
        aantalView.setText(aantal);

        TextView gemiddeldGoalenPerWedstrijd = (TextView)parent.findViewById(R.id.textview_gemiddeld);
        gemiddeldGoalenPerWedstrijd.setText(gemiddeldAantalDoelpunten);


        TextView aantalWedstrijdenTextView = (TextView)parent.findViewById(R.id.textview_aantalWedstrijden);
        aantalWedstrijdenTextView.setText(aantalWedstrijden);

        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAantal(String aantal) {
        this.aantal =aantal;
    }

    public void setPlaats(int plaats) {
        this.plaats = plaats;
    }

    public void setAantalWedstrijden(String aantalWedstrijden) {
        this.aantalWedstrijden = aantalWedstrijden;
    }

    public void setGemiddelde(String gemiddelde) {
        this.gemiddeldAantalDoelpunten = gemiddelde;
    }
}
