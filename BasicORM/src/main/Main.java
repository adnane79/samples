/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package main;

import entities.Dosen;
import entities.Mahasiswa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author radityo
 */
public class Main {

    public static void main(String[] a){

        Dosen dosen = new Dosen();

        dosen.setNama("didit");

        dosen.setNip("456");

        dosen.setTelp("huhuhu");


        Mahasiswa mhs1 = new Mahasiswa();

        mhs1.setName("mhs1");

        mhs1.setNoTelp("99888");

        mhs1.setNrp("55667788");

        mhs1.setAlamat("alamat1");

        mhs1.setDosenWali(dosen);
        
        dosen.getMahasiswaWali().add(mhs1);


        Mahasiswa mhs2 = new Mahasiswa();

        mhs2.setName("mhs2");

        mhs2.setNoTelp("55667788");

        mhs2.setDosenWali(dosen);

        dosen.getMahasiswaWali().add(mhs2);




        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BasicOOPPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dosen);
        em.persist(mhs1);
        em.persist(mhs2);

        System.out.println(dosen.getId());
        System.out.println(dosen.getNama());
        System.out.println(mhs1.getId());
        System.out.println(mhs1.getName());
        System.out.println(mhs2.getId());
        System.out.println(mhs2.getName());


        Mahasiswa mhs3 = new Mahasiswa();

        mhs3.setName("mhs3");

        mhs3.setNoTelp("8888");

        mhs3.setAlamat("jijijiji");

        mhs3.setDosenWali(dosen);

        dosen.getMahasiswaWali().add(mhs3);

        em.persist(mhs3);

        em.merge(dosen);

        System.out.println(mhs3.getId());
        System.out.println(mhs3.getName());

        System.out.println(dosen.getMahasiswaWali().size());
       
        em.getTransaction().commit();
        em.close();
        //em = null;
        emf.close();
        //emf = null;

    }

}
