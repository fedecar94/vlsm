package net.aguara.soft.vlsm;

import java.util.IdentityHashMap;

/**
 * Created by tekor on 30/11/2015.
 */
public class Subnet {
    String name;
    Integer count;
    Integer ip1;
    Integer ip2;
    Integer ip3;
    Integer ip4;
    Integer msc;

    public Subnet(String name, Integer count) {
        this.name = name;
        this.count = count;
        this.msc=Integer.numberOfLeadingZeros(count+1);
    }

    public void setIp(Integer ip1, Integer ip2, Integer ip3, Integer ip4) {
        this.ip1 = ip1;
        this.ip2 = ip2;
        this.ip3 = ip3;
        this.ip4 = ip4;
    }

    public String MaxHost() {
        return Integer.toString((exp(2,(32-msc))) - 2);
    }

    public String getIp() {
        return Integer.toString(ip1) + "." + Integer.toString(ip2) + "." + Integer.toString(ip3) + "." + Integer.toString(ip4);
    }

    public String getMsc() {
        return Integer.toString(msc);
    }

    public String getCount() {
        return Integer.toString(count);
    }

    public String getName() {
        return name;
    }

    public String getMsc2() {
        String ms = new String();
        String x = new String();
        for (int i = 0; i < msc; i++)
            ms = ms + "1";
        for (int i = msc; i < 32; i++)
            ms = ms + "0";
        x = Integer.toString(Integer.parseInt(ms.substring(0, 8), 2)) + ".";
        x = x + Integer.toString(Integer.parseInt(ms.substring(8, 16), 2)) + ".";
        x = x + Integer.toString(Integer.parseInt(ms.substring(16, 24), 2)) + ".";
        x = x + Integer.toString(Integer.parseInt(ms.substring(24, 32), 2));
        return x;
    }

    public static int exp(Integer n, Integer x){
        int y=n;
        for (int i = 1;i<x;i++)
            y=y*n;
        return y;
    }
}
