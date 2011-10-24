/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package object;


public class Orang {

    private Mulut mulut;

    private Kulit kulit;

    private JenisKelamin jenisKelamin;

    private Mata mata;

    private Rambut rambut;


    public Orang(){

        mulut = Mulut.TERSENYUM;

        kulit = Kulit.PUTIH;

        mata =  new Kamus().getDaftarMata().get("Mata Sehat");

        jenisKelamin = JenisKelamin.PRIA;

        rambut = Rambut.HITAM;

    }



    /**
     *
     * mengecat Rambut menjadi warna putih
     *
     */
    public void mengecatRambut(){

        setRambut(Rambut.PUTIH);

    }

    /**
     *
     * mengecat rambut berdasarkan jenis tertentu
     *
     * @param r jenis rambut
     */
    public void mengecatRambut(Rambut r){
        setRambut(r);
    }


    /**
     *
     * mengecat rambut berdasarkan warna rambut dari string tertentu
     *
     * @param warnaRambut string dari jenis rambut
     */
    public void mengecatRambut(String warnaRambut){

        warnaRambut = warnaRambut.toUpperCase();


        if(warnaRambut.equals("PUTIH")){

            setRambut(Rambut.PUTIH);
        }


        if(warnaRambut.equals("HITAM")){

            setRambut(Rambut.HITAM);

        }

        if(warnaRambut.equals("MERAH")){


            setRambut(Rambut.MERAH);
        }

    }

    /**
     * @return the mulut
     */
    public Mulut getMulut() {
        return mulut;
    }

    /**
     * @param mulut the mulut to set
     */
    public void setMulut(Mulut mulut) {
        this.mulut = mulut;
    }

    /**
     * @return the kulit
     */
    public Kulit getKulit() {
        return kulit;
    }

    /**
     * @param kulit the kulit to set
     */
    public void setKulit(Kulit kulit) {
        this.kulit = kulit;
    }

    /**
     * @return the jenisKelamin
     */
    public JenisKelamin getJenisKelamin() {
        return jenisKelamin;
    }

    /**
     * @param jenisKelamin the jenisKelamin to set
     */
    public void setJenisKelamin(JenisKelamin jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    /**
     * @return the mata
     */
    public Mata getMata() {
        return mata;
    }

    /**
     * @param mata the mata to set
     */
    public void setMata(Mata mata) {
        this.mata = mata;
    }

    /**
     * @return the rambut
     */
    public Rambut getRambut() {
        return rambut;
    }

    /**
     * @param rambut the rambut to set
     */
    public void setRambut(Rambut rambut) {
        this.rambut = rambut;
    }


    /**
     * Melakukan Aksi Marah
     */
    public void marah(){

        setMulut(Mulut.CEMBERUT);

        setMata(new Kamus().getDaftarMata().get("Mata Sakit"));

    }

    /**
     *
     * melakukan aksi menendang orang lain
     *
     * @param a objek Orang
     */
    public void menendangOrang(Orang a){
        a.kesakitan();
    }

    /**
     * merasa kesakitan ..
     *
     * lalu meneriakkan "Gile LUUU" menggunakan method teriakSesuatu
     * @see #teriakSesuatu(java.lang.String)
     */
    public void kesakitan(){
        setMata(new Kamus().getDaftarMata().get("Mata Sakit"));

        teriakSesuatu("Gile LUUU");
    }

    /**
     * meneriakkan sesuatu yang akan dilihat pada conosle..
     *
     * 
     * @param sesuatu
     */
    public String teriakSesuatu(String sesuatu){
        return "I Said : "+sesuatu;
    }
    

}