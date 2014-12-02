package domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Fre on 30/08/2014.
 */
@ParseClassName("MatchCalendar")
public class MatchCalendar extends ParseObject {
    public MatchCalendar() {
    }
    public String getMatchData() {
        return getString("HomeTeam")+" - "+ getString("AwayTeam");
    }
    public String getDate(){
        SimpleDateFormat ddMMMMyyFormat = new SimpleDateFormat("EEEE dd MMMM yyyy", new Locale("nl", "BE"));
        ddMMMMyyFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
       return ddMMMMyyFormat.format(getDate("Datum"));

    }
    public String getScore() {
        return getString("Score");
    }

    public String getSporthal() {
        return getString("Sporthal");
    }

    public String getAanvangsUur() {

        SimpleDateFormat format = new SimpleDateFormat("H:mm", new Locale("nl", "BE"));
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        return "aanvangsuur: "+format.format(getDate("Datum"));
    }
    public List<String> getAanwezigen(){

       return getList("aanwezigeSpelers");
    }

    public String getColorHeader() {
        SimpleDateFormat format = new SimpleDateFormat("H:mm", new Locale("nl", "BE"));
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        if(getDate("Datum").before(new Date())){
            return "#7f7f78";
        }
        else return "#ccccc0" ;
    }
}
