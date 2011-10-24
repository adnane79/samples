/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package object;

import java.util.HashMap;


public class Kamus {


    HashMap<String,Mata> daftarMata;

    public HashMap<String, Mata> getDaftarMata() {

        if(daftarMata == null){

            daftarMata = new HashMap<String, Mata>();

            Mata m = new Mata();

            m.setIsBeleken(Boolean.TRUE);
            m.setJenisMata("Mata Sakit");
            m.setWarnaBolaMata("Merah");

            daftarMata.put(m.getJenisMata(), m);

            Mata m1 = new Mata();

            m1.setIsBeleken(Boolean.FALSE);
            m1.setJenisMata("Mata Sehat");
            m1.setWarnaBolaMata("Biru");

            daftarMata.put(m1.getJenisMata(), m1);


            Mata m2 = new Mata();
            m2.setIsBeleken(Boolean.FALSE);
            m2.setWarnaBolaMata("IJO");
            m2.setJenisMata("Mata Duitan");

            daftarMata.put(m2.getJenisMata(), m2);



        }


        return daftarMata;
    }


    HashMap<String,KacaMata> daftarKacaMata;

    public HashMap<String, KacaMata> getDaftarKacaMata() {

        if(daftarKacaMata == null){

            daftarKacaMata = new HashMap<String,KacaMata>();

            KacaMata km = new KacaMata();

            km.setWarna("merah");

            km.setMerk("Kaca Mata Kijang");

            km.setHarga("3000");


            daftarKacaMata.put(km.getMerk(), km);


            KacaMata km1 = new KacaMata();

            km1.setWarna("putih");

            km1.setMerk("Kaca Mata Kuda");

            km1.setHarga("40000");

            daftarKacaMata.put(km1.getMerk(), km1);


            KacaMata km2 =  new KacaMata();

            km2.setWarna("hitam");

            km2.setMerk("Kaca Mata Orang");

            km2.setHarga("50000");


        }


        return daftarKacaMata;
    }



       

}
