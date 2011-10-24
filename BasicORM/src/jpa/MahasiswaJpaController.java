/**
 * @author radityo.p.w (radityo.p.w@gmail.com)
 * @version 1.0
 */

package jpa;

import entities.Mahasiswa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Dosen;
import jpa.exceptions.NonexistentEntityException;

/**
 *
 * @author radityo
 */
public class MahasiswaJpaController {

    public MahasiswaJpaController() {
        emf = Persistence.createEntityManagerFactory("BasicOOPPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mahasiswa mahasiswa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dosen dosenWali = mahasiswa.getDosenWali();
            if (dosenWali != null) {
                dosenWali = em.getReference(dosenWali.getClass(), dosenWali.getId());
                mahasiswa.setDosenWali(dosenWali);
            }
            em.persist(mahasiswa);
            if (dosenWali != null) {
                dosenWali.getMahasiswaWali().add(mahasiswa);
                dosenWali = em.merge(dosenWali);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mahasiswa mahasiswa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mahasiswa persistentMahasiswa = em.find(Mahasiswa.class, mahasiswa.getId());
            Dosen dosenWaliOld = persistentMahasiswa.getDosenWali();
            Dosen dosenWaliNew = mahasiswa.getDosenWali();
            if (dosenWaliNew != null) {
                dosenWaliNew = em.getReference(dosenWaliNew.getClass(), dosenWaliNew.getId());
                mahasiswa.setDosenWali(dosenWaliNew);
            }
            mahasiswa = em.merge(mahasiswa);
            if (dosenWaliOld != null && !dosenWaliOld.equals(dosenWaliNew)) {
                dosenWaliOld.getMahasiswaWali().remove(mahasiswa);
                dosenWaliOld = em.merge(dosenWaliOld);
            }
            if (dosenWaliNew != null && !dosenWaliNew.equals(dosenWaliOld)) {
                dosenWaliNew.getMahasiswaWali().add(mahasiswa);
                dosenWaliNew = em.merge(dosenWaliNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mahasiswa.getId();
                if (findMahasiswa(id) == null) {
                    throw new NonexistentEntityException("The mahasiswa with id " + id + " no longer exists.");
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
            Mahasiswa mahasiswa;
            try {
                mahasiswa = em.getReference(Mahasiswa.class, id);
                mahasiswa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mahasiswa with id " + id + " no longer exists.", enfe);
            }
            Dosen dosenWali = mahasiswa.getDosenWali();
            if (dosenWali != null) {
                dosenWali.getMahasiswaWali().remove(mahasiswa);
                dosenWali = em.merge(dosenWali);
            }
            em.remove(mahasiswa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mahasiswa> findMahasiswaEntities() {
        return findMahasiswaEntities(true, -1, -1);
    }

    public List<Mahasiswa> findMahasiswaEntities(int maxResults, int firstResult) {
        return findMahasiswaEntities(false, maxResults, firstResult);
    }

    private List<Mahasiswa> findMahasiswaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mahasiswa.class));
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

    public Mahasiswa findMahasiswa(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mahasiswa.class, id);
        } finally {
            em.close();
        }
    }

    public int getMahasiswaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mahasiswa> rt = cq.from(Mahasiswa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
