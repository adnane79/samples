/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package toko.online;

import junit.framework.TestCase;

/**
 *
 * @author radityo
 */
public class KeranjangBelanjaTest extends TestCase {


    private KeranjangBelanja keranjang;

    private Barang barang1;

    private Barang barang2;

    public KeranjangBelanjaTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        keranjang = new KeranjangBelanja();

        barang1 = new Barang();

        barang1.setHarga(1000);
        barang1.setKode("101");
        barang1.setNama("si jojo");

        barang2 = new Barang();

        barang2.setHarga(2000);
        barang2.setKode("102");
        barang2.setNama("si koko");


    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        keranjang = null;

        barang1 = null;

        barang2 = null;
    }

    /**
     * Test of masukkanBarang method, of class KeranjangBelanja.
     */
    public void testMasukkanBarang() {
        keranjang.masukkanBarang(barang1);

        assertEquals(1, keranjang.getDaftarBarang().size());
    }

    /**
     * Test of keluarkanBarang method, of class KeranjangBelanja.
     */
    public void testKeluarkanBarang() throws Exception {

        keranjang.masukkanBarang(barang2);

        assertEquals(1, keranjang.getDaftarBarang().size());

        keranjang.keluarkanBarang(barang2);

        assertEquals(0, keranjang.getDaftarBarang().size());

        try{
            
            Barang keluarkanBarang = keranjang.keluarkanBarang(barang1);

            fail("seharusnya itu Eksepsi");

        }catch(Exception e){

        }

        

        

    }

    /**
     * Test of getDaftarBarang method, of class KeranjangBelanja.
     */
    public void testGetDaftarBarang() {
        assertEquals(0, keranjang.getDaftarBarang().size());
    }

}
