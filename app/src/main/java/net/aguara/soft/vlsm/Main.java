package net.aguara.soft.vlsm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main extends AppCompatActivity {
    private String ipv6 = null;
    private Integer masc6 = 0;
    private String ipv4 = null;
    private Integer masc4 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main();
    }

    private void main() {
        setContentView(R.layout.main_ac);
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (valid6() || valid4())
                    add();
                else
                    ((TextView) findViewById(R.id.message)).setText("Invaild");
            }
        });
    }

    private void add() {

    }

    private boolean valid4() {
        String x = ((EditText) findViewById(R.id.ipv4)).getText().toString();
        Integer i1 = Integer.parseInt(x.substring(1));
        Integer i2;
        Integer i3;
        Integer i4;
        Integer m = Integer.parseInt(((EditText) findViewById(R.id.masc4)).getText().toString());
        return false;
    }

    private boolean valid6() {
        return false;
    }

    private void lista(ArrayList<?> listisima) {
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new listaAdaptador(this, R.layout.itemlista, listisima) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textTitulo);
                texto_superior_entrada.setText(((SubNet) entrada).getName());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textSubTitulo);
                texto_inferior_entrada.setText(((SubNet) entrada).getCount());
            }
        });
    }

    private void listalista(ArrayList<?> listisima) {
        ListView lista = (ListView) findViewById(R.id.lista);
        lista.setAdapter(new listaAdaptador(this, R.layout.itemlista2, listisima) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textTitulo);
                texto_superior_entrada.setText(((SubNet) entrada).getName());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textSubTitulo);
                texto_inferior_entrada.setText(((SubNet) entrada).getCount());

                TextView max = (TextView) view.findViewById(R.id.maxHost);
                max.setText(((SubNet) entrada).getMax());

                TextView ipv4 = (TextView) view.findViewById(R.id.ipv4Address);
                ipv4.setText(((SubNet) entrada).getIpv4());

                TextView masc4 = (TextView) view.findViewById(R.id.ipv4Masc);
                masc4.setText(((SubNet) entrada).getMasc());

                TextView ipv6 = (TextView) view.findViewById(R.id.ipv6Address);
                ipv6.setText(((SubNet) entrada).getIpv6());
            }
        });
    }
}
