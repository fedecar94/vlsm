package net.aguara.soft.vlsm;

/**
 * Created by tekor on 29/11/2015.
 */
public class SubNet {
    String name;
    Integer count;
    Integer max;
    Integer ipv41;
    Integer ipv42;
    Integer ipv43;
    Integer ipv44;
    Integer masc4;
    Integer masc41;
    Integer masc42;
    Integer masc43;
    Integer masc44;
    Integer ipv61;
    Integer ipv62;
    Integer ipv63;
    Integer ipv64;
    Integer ipv65;
    Integer ipv66;
    Integer ipv67;
    Integer ipv68;
    Integer masc6;

    public SubNet() {
    }

    public SubNet(String name, Integer count) {
        this.name = name;
        this.count = count;
    }

    public void setIpv4(Integer i1, Integer i2, Integer i3, Integer i4, Integer masc) {
        ipv41 = i1;
        ipv42 = i2;
        ipv43 = i3;
        ipv44 = i4;
        masc4 = masc;
        max = 2 ^ (32 - masc) - 2;
        calcMasc();
    }

    private void calcMasc() {
        String bin = new String();
        for (int i = 0; i < masc4; i++) {
            bin = bin + "1";
        }
        for (int i = masc4; i < 32; i++) {
            bin = bin + "0";
        }
        masc41 = Integer.parseInt(bin.substring(0, 7), 2);
        masc42 = Integer.parseInt(bin.substring(8, 15), 2);
        masc43 = Integer.parseInt(bin.substring(16, 23), 2);
        masc44 = Integer.parseInt(bin.substring(24, 31), 2);
    }

    public void setIpv6(Integer i1, Integer i2, Integer i3, Integer i4, Integer i5, Integer i6, Integer i7, Integer i8, Integer masc) {
        ipv61 = i1;
        ipv62 = i2;
        ipv63 = i3;
        ipv64 = i4;
        ipv65 = i5;
        ipv66 = i6;
        ipv67 = i7;
        ipv68 = i8;
        masc6 = masc;
    }

    public String getName() {
        return name;
    }

    public String getCount() {
        return "host count: "+Integer.toString(count);
    }

    public String getMax() {
        return "max allowed host: " + Integer.toString(max);
    }

    public String getIpv4() {
        return "ipv4: " + Integer.toString(ipv41) + "." + Integer.toString(ipv42) + "." + Integer.toString(ipv43) + "." + Integer.toString(ipv44) + "/" + Integer.toString(masc4);
    }

    public String getMasc() {
        return "masc: " + Integer.toString(masc41) + "." + Integer.toString(masc42) + "." + Integer.toString(masc43) + "." + Integer.toString(masc44);
    }

    public String getIpv6() {
        return "ipv6: " + Integer.toHexString(ipv61) + ":" + Integer.toHexString(ipv62) + ":" + Integer.toHexString(ipv63) + ":" + Integer.toHexString(ipv64) + ":" + Integer.toHexString(ipv65) + ":" + Integer.toHexString(ipv66) + ":" + Integer.toHexString(ipv67) + ":" + Integer.toHexString(ipv68) + "/" + Integer.toString(masc6);
    }
}