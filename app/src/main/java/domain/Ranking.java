package domain;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by frederique on 4/09/14.
 */
@ParseClassName("Ranking")
public class Ranking extends ParseObject implements Comparable<Ranking> {

    public Ranking() {
    }
    public String getTeamName(){
        return getString("team");
    }
    public int getAantalGewonnenWedstrijden(){
        return getNumber("gewonnen").intValue();
    }
    public int getAantalVerlorenWedstrijden(){
        return getNumber("verloren").intValue();
    }
    public int getAantalGelijkeSpelen(){
        return getNumber("gelijk").intValue();
    }
    public int getDoelpuntenVoor(){
        return getNumber("doelpuntenVoor").intValue();
    }
    public int getDoelpuntenTegen(){
        return getNumber("doelpuntenTegen").intValue();
    }
    public int getAantalPunten(){
        return (getAantalGewonnenWedstrijden()*3) + getAantalGelijkeSpelen();
    }

    @Override
    public int compareTo(Ranking another) {
      if(another.getAantalPunten()-this.getAantalPunten()==0){

          return (another.getDoelpuntenVoor() - another.getDoelpuntenTegen())
                  - (this.getDoelpuntenVoor() - this.getDoelpuntenTegen());
      }
        else return another.getAantalPunten() - this.getAantalPunten();
    }
}
