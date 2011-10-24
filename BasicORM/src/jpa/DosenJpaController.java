/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package jpa;

import entities.Dosen;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Mahasiswa;
import java.util.ArrayList;
import java.util.List;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author radityo
 */
public class DosenJpaController {

    public DosenJpaController() {
        emf = Persistence.createEntityManagerFactory("BasicOOPPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dosen dosen) {
        if (dosen.getMahasiswaWali() == null) {
            dosen.setMahasiswaWali(new ArrayList<Mahasiswa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Mahasiswa> attachedMahasiswaWali = new ArrayList<Mahasiswa>();
            for (Mahasiswa mahasiswaWaliMahasiswaToAttach : dosen.getMahasiswaWali()) {
                mahasiswaWaliMahasiswaToAttach = em.getReference(mahasiswaWaliMahasiswaToAttach.getClass(), mahasiswaWaliMahasiswaToAttach.getId());
                attachedMahasiswaWali.add(mahasiswaWaliMahasiswaToAttach);
            }
            dosen.setMahasiswaWali(attachedMahasiswaWali);
            em.persist(dosen);
            for (Mahasiswa mahasiswaWaliMahasiswa : dosen.getMahasiswaWali()) {
                Dosen oldDosenWaliOfMahasiswaWaliMahasiswa = mahasiswaWaliMahasiswa.getDosenWali();
                mahasiswaWaliMahasiswa.setDosenWali(dosen);
                mahasiswaWaliMahasiswa = em.merge(mahasiswaWaliMahasiswa);
                if (oldDosenWaliOfMahasiswaWaliMahasiswa != null) {
                    oldDosenWaliOfMahasiswaWaliMahasiswa.getMahasiswaWali().remove(mahasiswaWaliMahasiswa);
                    oldDosenWaliOfMahasiswaWaliMahasiswa = em.merge(oldDosenWaliOfMahasiswaWaliMahasiswa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dosen dosen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dosen persistentDosen = em.find(Dosen.class, dosen.getId());
            List<Mahasiswa> mahasiswaWaliOld = persistentDosen.getMahasiswaWali();
            List<Mahasiswa> mahasiswaWaliNew = dosen.getMahasiswaWali();
            List<Mahasiswa> attachedMahasiswaWaliNew = new ArrayList<Mahasiswa>();
            for (Mahasiswa mahasiswaWaliNewMahasiswaToAttach : mahasiswaWaliNew) {
                mahasiswaWaliNewMahasiswaToAttach = em.getReference(mahasiswaWaliNewMahasiswaToAttach.getClass(), mahasiswaWaliNewMahasiswaToAttach.getId());
                attachedMahasiswaWaliNew.add(mahasiswaWaliNewMahasiswaToAttach);
            }
            mahasiswaWaliNew = attachedMahasiswaWaliNew;
            dosen.setMahasiswaWali(mahasiswaWaliNew);
            dosen = em.merge(dosen);
            for (Mahasiswa mahasiswaWaliOldMahasiswa : mahasiswaWaliOld) {
                if (!mahasiswaWaliNew.contains(mahasiswaWaliOldMahasiswa)) {
                    mahasiswaWaliOldMahasiswa.setDosenWali(null);
                    mahasiswaWaliOldMahasiswa = em.merge(mahasiswaWaliOldMahasiswa);
                }
            }
            for (Mahasiswa mahasiswaWaliNewMahasiswa : mahasiswaWaliNew) {
                if (!mahasiswaWaliOld.contains(mahasiswaWaliNewMahasiswa)) {
                    Dosen oldDosenWaliOfMahasiswaWaliNewMahasiswa = mahasiswaWaliNewMahasiswa.getDosenWali();
                    mahasiswaWaliNewMahasiswa.setDosenWali(dosen);
                    mahasiswaWaliNewMahasiswa = em.merge(mahasiswaWaliNewMahasiswa);
                    if (oldDosenWaliOfMahasiswaWaliNewMahasiswa != null && !oldDosenWaliOfMahasiswaWaliNewMahasiswa.equals(dosen)) {
                        oldDosenWaliOfMahasiswaWaliNewMahasiswa.getMahasiswaWali().remove(mahasiswaWaliNewMahasiswa);
                        oldDosenWaliOfMahasiswaWaliNewMahasiswa = em.merge(oldDosenWaliOfMahasiswaWaliNewMahasiswa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = dosen.getId();
                if (findDosen(id) == null) {
                    throw new NonexistentEntityException("The dosen with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dosen dosen;
            try {
                dosen = em.getReference(Dosen.class, id);
                dosen.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dosen with id " + id + " no longer exists.", enfe);
            }
            List<Mahasiswa> mahasiswaWali = dosen.getMahasiswaWali();
            for (Mahasiswa mahasiswaWaliMahasiswa : mahasiswaWali) {
                mahasiswaWaliMahasiswa.setDosenWali(null);
                mahasiswaWaliMahasiswa = em.merge(mahasiswaWaliMahasiswa);
            }
            em.remove(dosen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dosen> findDosenEntities() {
        return findDosenEntities(true, -1, -1);
    }

    public List<Dosen> findDosenEntities(int maxResults, int firstResult) {
        return findDosenEntities(false, maxResults, firstResult);
    }

    private List<Dosen> findDosenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dosen.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Dosen findDosen(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dosen.class, id);
        } finally {
            em.close();
        }
    }

    public int getDosenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dosen> rt = cq.from(Dosen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
