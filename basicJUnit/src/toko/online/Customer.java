/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package toko.online;

/**
 *
 * @author radityo
 */
public class Customer {

    private String nama;
    private String id;
    private KeranjangBelanja keranjang;
    private Toko toko;

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

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the keranjang
     */
    public KeranjangBelanja getKeranjang() {
        return keranjang;
    }

    /**
     * @param keranjang the keranjang to set
     */
    public void setKeranjang(KeranjangBelanja keranjang) {
        this.keranjang = keranjang;
    }


    public void membeliBarang(Barang a){


        Barang barang1 = toko.diambilBarang(a);


        keranjang.masukkanBarang(barang1);

    }


    public void membayar(){
        int totalBayar = 0;

        while(keranjang.getDaftarBarang().iterator().hasNext()){

            Barang barang = keranjang.getDaftarBarang().iterator().next();

            totalBayar += barang.getHarga();

        }

        toko.dibayar(totalBayar);

        keranjang.getDaftarBarang().clear();
    }

    

}
