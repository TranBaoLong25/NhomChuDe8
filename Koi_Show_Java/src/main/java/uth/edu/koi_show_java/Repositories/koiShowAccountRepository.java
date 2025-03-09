package uth.edu.koi_show_java.Repositories;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import uth.edu.koi_show_java.Models.koiShowAccount;

import java.util.List;

@Repository
@Transactional
public class koiShowAccountRepository {
    private EntityManager em;
    public void save(koiShowAccount a) {
        em.persist(a);
    }
    public koiShowAccount findById(int id) {
        return em.find(koiShowAccount.class, id);
    }
    public List<koiShowAccount> findAll() {
        return em.createQuery("from koiShowAccount", koiShowAccount.class).getResultList();
    }
    public void delete(int id) {
        koiShowAccount acc =findById(id);
        if (acc != null) {
            em.remove(acc);
        }
    }
    public void update(koiShowAccount a) {
        em.merge(a);
    }

}
