/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package toko.online;

/**
 *
 * @author radityo
 */
public class Barang {

    private String kode;
    private String nama;
    private int harga;

    /**
     * @return the kode
     */
    public String getKode() {
        return kode;
    }

    /**
     * @param kode the kode to set
     */
    public void setKode(String kode) {
        this.kode = kode;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Barang){
            Barang x = (Barang) obj;
            if(x.getKode().equals(this.getKode())){
                if(x.getNama().equals(this.getNama())){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * @return the harga
     */
    public int getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(int harga) {
        this.harga = harga;
    }



    


}
