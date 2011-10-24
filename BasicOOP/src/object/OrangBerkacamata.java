/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package object;


public class OrangBerkacamata extends Orang {

    private KacaMata kacaMata;

    public OrangBerkacamata(){
        super();

        kacaMata = new Kamus().getDaftarKacaMata().get("Kaca Mata Orang");
    }

    /**
     * @return the kacaMata
     */
    public KacaMata getKacaMata() {
        return kacaMata;
    }

    /**
     * @param kacaMata the kacaMata to set
     */
    public void setKacaMata(KacaMata kacaMata) {
        this.kacaMata = kacaMata;
    }

    /**
     * melakukan aksi marah
     */
    @Override
    public void marah(){

        super.marah();

        setKacaMata(new Kamus().getDaftarKacaMata().get("Kaca Mata Kuda"));

        teriakSesuatu("hihihihi");

    }


    

}