/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radityo
 */
public class DaftarMahasiswa {

    public static List<Mahasiswa> getList(){
        List<Mahasiswa> x = new ArrayList<Mahasiswa>();


        Mahasiswa mhs1 = new Mahasiswa();
        mhs1.setNama("mhs1");
        mhs1.setNrp("001");

        x.add(mhs1);

        Mahasiswa mhs2 = new Mahasiswa();
        mhs2.setNama("mhs2");
        mhs2.setNrp("002");

        x.add(mhs2);

        Mahasiswa mhs3 = new Mahasiswa();
        mhs3.setNama("mhs3");
        mhs3.setNrp("003");

        x.add(mhs3);


        return x;
    }

}
