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
public class KeranjangBelanja {

    private List<Barang> daftarBarang;

    public KeranjangBelanja(){
        daftarBarang = new ArrayList<Barang>();
    }


    public void masukkanBarang(Barang a){
        daftarBarang.add(a);
    }

    public Barang keluarkanBarang(Barang a) throws Exception{
        Barang x = null;

        int index = daftarBarang.indexOf(a);

        if(index != -1){
            x = daftarBarang.remove(index);
        }else{
            throw new Exception("Barang gak ada");
        }

        return x;
    }

    public List<Barang> getDaftarBarang(){
        return daftarBarang;
    }

}
