package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entite.Vitamine;

public class VitamineDAO {

    private EntityManager entityManager;

    public VitamineDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Vitamine vitamine) {
        entityManager.getTransaction().begin();
        entityManager.persist(vitamine);
        entityManager.getTransaction().commit();
    }

    public Vitamine findById(Long id) {
        return entityManager.find(Vitamine.class, id);
    }

    public List<Vitamine> findAll() {
        TypedQuery<Vitamine> query = entityManager.createQuery("SELECT v FROM Vitamine v", Vitamine.class);
        return query.getResultList();
    }

    public void update(Vitamine vitamine) {
        entityManager.getTransaction().begin();
        entityManager.merge(vitamine);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Vitamine vitamine = entityManager.find(Vitamine.class, id);
        if (vitamine != null) {
            entityManager.remove(vitamine);
        }
        entityManager.getTransaction().commit();
    }
}

