package net.aguara.soft.vlsm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Subnet> as = new ArrayList<Subnet>();
    int x1;
    int x2;
    int x3;
    int x4;
    int m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main();

    }

    private void main() {
        as.clear();
        setContentView(R.layout.activity_main);
        findViewById(R.id.ADD).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x1 = Integer.parseInt(((EditText) findViewById(R.id.IP1)).getText().toString());
                x2 = Integer.parseInt(((EditText) findViewById(R.id.IP2)).getText().toString());
                x3 = Integer.parseInt(((EditText) findViewById(R.id.IP3)).getText().toString());
                x4 = Integer.parseInt(((EditText) findViewById(R.id.IP4)).getText().toString());
                m = Integer.parseInt(((EditText) findViewById(R.id.MSC)).getText().toString());
                if (x1 < 256 && x1 >= 0 && x2 < 256 && x2 >= 0 && x3 < 256 && x3 >= 0 && x4 < 256 && x4 >= 0 && m < 33 && m >= 0) {
                    add();
                } else
                    ((TextView) findViewById(R.id.message)).setText("Incorrect");
            }
        });
    }

    private void add() {
        setContentView(R.layout.add_ac);
        listalistar(as);
        findViewById(R.id.calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calc();
            }
        });
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String na = ((EditText) findViewById(R.id.name)).getText().toString();
                int co = Integer.parseInt(((EditText) findViewById(R.id.count)).getText().toString());
                Subnet s = new Subnet(na, co);
                as.add(s);
                listalistar(as);
            }
        });
    }

    private void calc() {
        setContentView(R.layout.result_ac);
        ((TextView) findViewById(R.id.result)).setText("...");
        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main();
            }
        });
        findViewById(R.id.make).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add();
            }
        });
        Subnet z;
        for (int x=0;x<as.size()-1;x++) {
            for (int y = x + 1; y < as.size(); y++) {
                if (as.get(x).count < as.get(y).count) {
                    z = as.get(x);
                    as.set(x,as.get(y));
                    as.set(y,z);
                }
            }
        }

        String bin = ipToBin(x1,x2,x3,x4);
        String net = bin.substring(0, m);
        String sub = new String();
        int su=0;
        for (int i=0;i<as.size();i++){
            sub=Integer.toBinaryString(su);
            while (sub.length()<(32-m))
                sub="0"+sub;
            as.set(i,binToIp(net+sub,as.get(i)));
            su=su+ exp(2,(32-as.get(i).msc));
        }
        listalista(as);
        ((TextView) findViewById(R.id.result)).setText("Result");
    }

    private Subnet binToIp(String s,Subnet j){
        j.setIp(
                Integer.parseInt(s.substring(0,8),2),
                Integer.parseInt(s.substring(8,16),2),
                Integer.parseInt(s.substring(16,24),2),
                Integer.parseInt(s.substring(24,32),2)
        );
        return j;
    }

    private String ipToBin(Integer i1,Integer i2,Integer i3,Integer i4){
        String s = Integer.toBinaryString(i4);
        while (s.length()<8)
            s="0"+s;
        s = Integer.toBinaryString(i3)+s;
        while (s.length()<16)
            s="0"+s;
        s = Integer.toBinaryString(i2)+s;
        while (s.length()<24)
            s="0"+s;
        s = Integer.toBinaryString(i1)+s;
        while (s.length()<32)
            s="0"+s;
        return s;
    }

    public void listalistar(ArrayList<?> listisima) {
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new listaAdaptador(this, R.layout.itemlistar, listisima) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView name = (TextView) view.findViewById(R.id.rname);
                name.setText(((Subnet) entrada).getName());

                TextView count = (TextView) view.findViewById(R.id.rcount);
                count.setText(((Subnet) entrada).getCount());
            }
        });
    }

    public void listalista(ArrayList<?> listisima) {
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new listaAdaptador(this, R.layout.itemlista, listisima) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView name = (TextView) view.findViewById(R.id.rname);
                name.setText(((Subnet) entrada).getName());

                TextView count = (TextView) view.findViewById(R.id.rcount);
                count.setText(((Subnet) entrada).getCount());

                TextView sIP = (TextView) view.findViewById(R.id.sIP);
                sIP.setText(((Subnet) entrada).getIp());

                TextView sMAX = (TextView) view.findViewById(R.id.sMAX);
                sMAX.setText(((Subnet) entrada).MaxHost());

                TextView sMSC = (TextView) view.findViewById(R.id.sMSC);
                sMSC.setText(((Subnet) entrada).getMsc());

                TextView sMSC2 = (TextView) view.findViewById(R.id.sMSC2);
                sMSC2.setText(((Subnet) entrada).getMsc2());
            }
        });
    }

    public int exp(Integer n, Integer x){
        int y=n;
        for (int i = 1;i<x;i++)
            y=y*n;
        return y;
    }
}
