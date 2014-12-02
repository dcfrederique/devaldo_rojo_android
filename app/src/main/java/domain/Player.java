package domain;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

/**
 * Created by Frederique on 1/09/2014.
 */
@ParseClassName("Speler")
public class Player extends ParseObject {

    public String getName() {
        return getString("naam");
    }
    public String getAantalDoelpunten() {
        return ""+getNumber("doelpunten");
    }
    public String getAantalAssists() {
        return ""+getNumber("assist");
    }
    public ParseFile getPhotoFile() {
        return getParseFile("speler_image");
    }

    public String getAantalWedstrijden() {
        return "" +getNumber("wedstrijdenGespeeld");
    }
    public String getGemiddeldAantalDoelpunten(){
        return String.format("Gemiddelde: %.2f",getNumber("doelpunten").doubleValue()/getNumber("wedstrijdenGespeeld").doubleValue());
    }
}
