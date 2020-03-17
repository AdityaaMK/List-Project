package com.example.lvtest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button play;
    Button webScrape;
    ListView lv;
    TextView rank, strength, info, info2;
    ArrayList<Player> players = new ArrayList<>();
    int atpRank;
    boolean removed = true;
    String atpStrength = "";
    String moreinfo = "";
    String web = "";
    String web2 = "";
    String pos = "";
    ConstraintLayout cl;
    //String words2 = "";
    public static final String rankKey = "rank";
    public static final String strengthKey = "strength";
    public static final String moreinfoKey = "moreinfo";
    public static final String playerKey = "player";
    public static final String posKey = "pos";
    public static final String webKey = "web";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.id_lv);
        rank = findViewById(R.id.id_rank);
        strength = findViewById(R.id.id_type);
        play = findViewById(R.id.id_play);
        webScrape = findViewById(R.id.id_webscrape);
        cl = findViewById(R.id.id_cl);

        Timer timer = new Timer();
        MyTimer myTimer = new MyTimer();
        timer.schedule(myTimer, 2000, 2000);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            info = findViewById(R.id.id_info);
            info.setMovementMethod(new ScrollingMovementMethod());
            info2 = findViewById(R.id.id_info2);
            info2.setMovementMethod(new ScrollingMovementMethod());
        }

        players.add(new Player(1, "Forehand", "Rafael Nadal has won 19 Grand Slams (2nd Most of All Time).  " +
                "He has won 12 Roland Garros titles which is the most for any Grand Slam in the Open-Era.  He will most likely contiue to win the French Open for a while..."
                , "Rafael Nadal", "https://www.youtube.com/watch?v=6VaxpcnKtBA", "https://en.wikipedia.org/wiki/Rafael_Nadal",R.drawable.rafa));

        players.add(new Player(2, "Two-handed backhand", "Novak Djokovic has won 16 Grand Slams (3rd Most of All Time).  " +
                "He is not a fan-favorite against Nadal and Federer but many think he will overtake their records due to his present dominance on the tour" +
                ".", "Novak Djokovic","https://www.youtube.com/watch?v=QFeGYpdFNyQ", "https://en.wikipedia.org/wiki/Novak_Djokovic", R.drawable.novak));

        players.add(new Player(3, "One-handed backhand", "Roger Federer has won 20 Grand Slams (Most of All Time).  " +
                "He is regarded as the GOAT since he has won the most Grand Slams.  His elegance on and off the court is why he is greatly admired and respected.  "
                , "Roger Federer","https://www.youtube.com/watch?v=hXBq28O84yo", "https://en.wikipedia.org/wiki/Roger_Federer",R.drawable.rf));

        players.add(new Player(4, "Forehand", "Dominic Thiem is an amazing clay court player.  " +
                "Many think that he will replace Nadal as the 'King of Clay'.  He is one of the most consistent players in the NextGen to challenge the Big 3.  "
                , "Dominic Thiem","https://www.youtube.com/watch?v=7tKwtx8trH8","https://en.wikipedia.org/wiki/Dominic_Thiem", R.drawable.dominic));

        players.add(new Player(5, "Serve", "Medwed had an insanely good hard court swing only losing 3 matches.  " +
                "His playing style is unconventional but his consistency is impeccable.", "Daniel Medvedev",
                "https://www.youtube.com/watch?v=1JKo3BjTVKE","https://en.wikipedia.org/wiki/Daniil_Medvedev", R.drawable.med));

        players.add(new Player(6, "One-handed backhand", "Stef had a great end to the season as he won the Nitto ATP Finals in his first appearance.  " +
                "He is a solid contender for Grand Slams in 2020 and will definitely achieve a lot in his career.", "Stefanos Tsitsipas",
                "https://www.youtube.com/watch?v=edy27S3JDmc","https://en.wikipedia.org/wiki/Stefanos_Tsitsipas",R.drawable.stef));

        players.add(new Player(7, "Two-handed backhand", "Zverev had a great 2018 season winning the Nitto ATP Finals 2018.  However, his 2019 was not as good.  " +
                "He had a rocky start but managed to stay in the Top 10 and qualify for the Nitto ATP Finals.", "Alexander Zverev",
                "https://www.youtube.com/watch?v=_wHvjZ2Jxcw&list=PLQHHr8gPOsH7So2FcFwgrAD1_puVlhX7o&index=10&t=0s", "https://en.wikipedia.org/wiki/Alexander_Zverev",R.drawable.alex));

        players.add(new Player(8, "Forehand", "Berrettini had an amazing run at the US Open, beating top players such as Gael Monfils.  " +
                "He is sure to be a front runner in 2020 with his powerful forehands and big serves.", "Matteo Berrettini",
                "https://www.youtube.com/watch?v=5HA1bzR3kbE", "https://en.wikipedia.org/wiki/Matteo_Berrettini",R.drawable.matteo));

        players.add(new Player(9, "Forehand", "RBA had some great wins this season.  His flat playing style make him an intimidating opponent against" +
                "even the best players such as Novak Djokovic.  He also managed to seal the Davis Cup title for Spain.", "Roberto Bautista Agut",
                "https://www.youtube.com/watch?v=pncoHEnVIfQ", "https://en.wikipedia.org/wiki/Roberto_Bautista_Agut",R.drawable.rba));

        players.add(new Player(10, "Forehand", "Monfils has been playing better ever since he has started dating Elina Svitlona.  " +
                "Monfils has amazing athleticism and is always a fan favorite for his insane trickshots.  This year he has also been playing more seriously and thus, winning more matches.  ",
                "Gael Monfils", "https://www.youtube.com/watch?v=6wJj-g3ASpA","https://en.wikipedia.org/wiki/Gaël_Monfils", R.drawable.gael));

        players.add(new Player(11, "Forehand", "Goffin is a solid player.  Although he lacks power, he makes up for this through his consistency " +
                "which challenges even the hardest-hitters", "David Goffin","" +
                "https://www.youtube.com/watch?v=Sgk1TdU-mCc", "https://en.wikipedia.org/wiki/David_Goffin",R.drawable.goffin));

        players.add(new Player(12, "Two-handed backhand", "Fabio Fognini played amazing in Monte Carlo, winning his first ATP Masters 1000 Title.  Fognini has a nice" +
                "backhand but his temper and mentality sometimes prevent him from playing his best and rather, hinder his talent.",
                "Fabio Fognini","https://www.youtube.com/watch?v=XqWhKmFZr6o","https://en.wikipedia.org/wiki/Fabio_Fognini", R.drawable.fabio));

        players.add(new Player(13, "Forehand", "Kei Nishikori struggled this season: he lost to players he beat last season and seemed to lack his " +
                "optimal form.  He was considered one of the main opponents of the Big 3 but now he is losing to many NextGen players. ",
                "Kei Nishikori","https://www.youtube.com/watch?v=ZoTvAZI67sQ", "https://en.wikipedia.org/wiki/Kei_Nishikori", R.drawable.kei));

        players.add(new Player(14, "Forehand", "Despite his small stature, Schwartzman grit and strength is impeccable.  He beat Alexander Zverev at the US Open " +
                "who is almost 1 foot taller than him.",
                "Diego Schwartzman","https://www.youtube.com/watch?v=UvpTV-V3fms", "https://en.wikipedia.org/wiki/Diego_Schwartzman", R.drawable.diego));

        players.add(new Player(15, "One-handed backhand", "Shapovalov struggled in the early season but he started to play better at the Paris Rolex Masters.  " +
                "His increasing form led him to capture the Stockholm Title and he played great during the Davis Cup.",
                "Denis Shapovalov","https://www.youtube.com/watch?v=S8Cw6NOpIeo", "https://en.wikipedia.org/wiki/Denis_Shapovalov", R.drawable.deni));

        if (savedInstanceState != null) {
            atpRank = savedInstanceState.getInt(rankKey);
            atpStrength = savedInstanceState.getString(strengthKey);
            moreinfo = savedInstanceState.getString(moreinfoKey);
            players = (ArrayList<Player>)savedInstanceState.getSerializable(playerKey);
            pos = savedInstanceState.getString(posKey);
            web2 = savedInstanceState.getString(webKey);
            if(atpRank!=0) {
                rank.setText("Rank: " + atpRank);
            }
            strength.setText("Best Shot "+atpStrength);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                info.setText(moreinfo);
                /*
                if(!removed) {
                    System.out.println(removed);
                    play.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pos)));
                        }
                    });

                     */
                    webScrape.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            web = web2;
                        }
                    });
            }
        }

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, players);
        lv.setAdapter(customAdapter);
    }

    public class CustomAdapter extends ArrayAdapter<Player> {
        Context parentContext;
        List<Player> list;
        int xmlResource;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Player> objects) {
            super(context, resource, objects);
            parentContext = context;
            list = objects;
            xmlResource = resource;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = layoutInflater.inflate(R.layout.adapter_custom, null);
            TextView name = adapterView.findViewById(R.id.id_name);
            ImageView pic = adapterView.findViewById(R.id.id_pic);
            pic.setImageResource(list.get(position).getImage());
            name.setText(list.get(position).getName());

            Button rem = adapterView.findViewById(R.id.id_rem);
            rem.setTextColor(Color.RED);
            rem.getBackground().setAlpha(0);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                    pos = list.get(position).getLink();
                    web = list.get(position).getWeb();
                    atpRank = list.get(position).getRank();
                    rank.setText("Rank: " + atpRank);
                    atpStrength = list.get(position).getStrength();
                    strength.setText("Best Shot: " + atpStrength);
                    moreinfo = list.get(position).getAchievements();
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        info.setText(moreinfo);
                        info2.setText("");
                        play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getLink())));
                            }
                        });
                        webScrape.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                web = list.get(position).getWeb();
                                new webScraper().execute();
                            }
                        });
                    }
                }
            });

            rem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle("Delete?");
                    adb.setMessage("Are you sure you want to delete " + list.get(position).getName() + "?");
                    adb.setNegativeButton("Cancel", null);
                    adb.setPositiveButton("Yes", new AlertDialog.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(list.size()==1){
                                moreinfo = "";
                                rank.setText("");
                                strength.setText("");
                                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                    info.setText("");
                                    info2.setText("");
                                }
                                Toast.makeText(MainActivity.this, "All the Top 15 players have been removed!", Toast.LENGTH_LONG).show();
                            }
                            else if(position < list.size()-1){
                                atpRank = list.get(position+1).getRank();
                                rank.setText("Rank: " + atpRank);
                                atpStrength = list.get(position+1).getStrength();
                                strength.setText("Best Shot: " + atpStrength);
                                moreinfo = list.get(position+1).getAchievements();
                            }
                            else if(position == list.size()-1){
                                atpRank = list.get(position-1).getRank();
                                rank.setText("Rank: " + atpRank);
                                atpStrength = list.get(position-1).getStrength();
                                strength.setText("Best Shot: " + atpStrength);
                                moreinfo = list.get(position-1).getAchievements();
                            }
                            list.remove(position);
                            notifyDataSetChanged();
                            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                                info.setText(moreinfo);
                                info2.setText("");
                                play.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(list.get(position).getLink())));
                                    }
                                });
                                webScrape.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        web = list.get(position).getWeb();
                                        new webScraper().execute();
                                    }
                                });
                            }
                        }
                    });
                    adb.show();
                }
            });

            return adapterView;

        }
        public class webScraper extends AsyncTask<Void, Void, Void> {
            String words;

            @Override
            public Void doInBackground(Void... params) {
                try {
                    Document doc = Jsoup.connect(web).get();
                    words = doc.text();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                info2.setText(words);
            }
        }
    }

    public class MyTimer extends TimerTask {

        public void run() {

            runOnUiThread(new Runnable() {

                public void run() {
                    Random rand = new Random();
                    cl.setBackgroundColor(Color.argb(50, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                }
            });
        }

    }

    public class Player implements Serializable{
        int rank;
        String strength;
        String achievements;
        String name;
        String link;
        String web;
        int image;

        public Player(int rank, String strength, String achievements, String name, String link, String web, int image){
            this.rank = rank;
            this.strength = strength;
            this.achievements = achievements;
            this.name = name;
            this.link = link;
            this.image = image;
            this.web = web;
        }
        public int getRank(){
            return rank;
        }
        public String getStrength(){
            return strength;
        }
        public String getAchievements(){
            return achievements;
        }
        public String getName(){
            return name;
        }
        public int getImage(){
            return image;
        }
        public String getLink(){
            return link;
        }
        public String getWeb(){
            return web;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(rankKey,atpRank);
        outState.putString(strengthKey, atpStrength);
        outState.putString(posKey, pos);
        outState.putString(webKey, web2);
        outState.putSerializable(moreinfoKey, moreinfo);
        outState.putSerializable(playerKey, players);
    }
}
