package domain;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import minivoetbal.devaldorojo.R;

/**
 * Created by frederique on 4/09/14.
 */
public class RankingAdapter extends ArrayAdapter<Ranking> {
    private List<Ranking>list;
    public RankingAdapter(Context context, int resource,List<Ranking> list) {

        super(context, resource, list);
        this.list=list;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            convertView = inflater.inflate(R.layout.listview_row_item, parent, false);
        }
        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.GRAY);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }
        Ranking r = list.get(position);

        TextView textViewTeamName =(TextView)convertView.findViewById(R.id.team_name);
        textViewTeamName.setText(position+1 +". " +r.getTeamName());
        TextView textViewTeamGames=(TextView)convertView.findViewById(R.id.team_games);
        textViewTeamGames.setText(""+(r.getAantalVerlorenWedstrijden()+r.getAantalGelijkeSpelen()+r.getAantalGewonnenWedstrijden()));

        TextView textViewTeamGamesWin =(TextView)convertView.findViewById(R.id.team_games_win);
        textViewTeamGamesWin.setText(""+r.getAantalGewonnenWedstrijden());

        TextView textViewTeamGamesDraw =(TextView)convertView.findViewById(R.id.team_games_draw);
        textViewTeamGamesDraw.setText(""+r.getAantalGelijkeSpelen());

        TextView textViewTeamGamesLose =(TextView)convertView.findViewById(R.id.team_games_loose);
        textViewTeamGamesLose.setText(""+r.getAantalVerlorenWedstrijden());

        TextView textViewTeamGoals =(TextView)convertView.findViewById(R.id.team_goals_dif);
        textViewTeamGoals.setText("" + r.getDoelpuntenVoor());

        TextView textViewTeamGoalsAgainst=(TextView)convertView.findViewById(R.id.team_goals_got);
        textViewTeamGoalsAgainst.setText(""+r.getDoelpuntenTegen());

        TextView textViewTeamPoints=(TextView)convertView.findViewById(R.id.team_points);
        textViewTeamPoints.setText(""+r.getAantalPunten());


        return convertView;
    }
}
