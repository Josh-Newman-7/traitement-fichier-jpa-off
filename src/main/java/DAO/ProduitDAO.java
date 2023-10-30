package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entite.Produit;

public class ProduitDAO implements GenericDAO<Produit, Long> {
    private EntityManager entityManager;

    public ProduitDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Produit create(Produit entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public Produit update(Produit entity) {
        entityManager.getTransaction().begin();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Produit entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public Produit findById(Long id) {
        return entityManager.find(Produit.class, id);
    }

    @Override
    public List<Produit> findAll() {
        TypedQuery<Produit> query = entityManager.createQuery("SELECT p FROM Produit p", Produit.class);
        return query.getResultList();
    }

	public Produit createProductFromData(String data) {
	    Produit produit = new Produit();
	    produit.setNom(data);

	    return produit;
	}
}

