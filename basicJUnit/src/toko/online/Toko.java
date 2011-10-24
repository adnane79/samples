/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package toko.online;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radityo
 */
public enum Toko {

    
    TOKO_SI_JO,
    TOKO_SI_JOJO,
    TOKO_HONG_HONG;
    
    
    private List<Barang> koleksiBarang = new ArrayList<Barang>();

    private int uang = 0;

    Toko(){



        for(int i=0;i<10;i++){

            Barang x = new Barang();
            x.setNama("barang"+i);
            x.setKode(i+"");

            koleksiBarang.add(x);


        }

        for(int i=0;i<10;i=i+2){

            Barang x = new Barang();
            x.setNama("barang"+i);
            x.setKode(i+"");

            koleksiBarang.add(x);
        }

    }


    public Barang diambilBarang(Barang a){
        

        koleksiBarang.remove(a);

        return a;
    }


    public void ditaruhBarang(Barang a){

        koleksiBarang.add(a);
    }

    public List<Barang> getKoleksiBarang(){
        return koleksiBarang;
    }

    public void dibayar(int uang){
        this.uang = this.uang + uang;
    }

    public int getJumlahUang(){

        return uang;

    }

}
