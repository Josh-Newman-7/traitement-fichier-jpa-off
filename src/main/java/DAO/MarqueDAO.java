package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entite.Marque;

public class MarqueDAO {

    private EntityManager entityManager;

    public MarqueDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Marque marque) {
        entityManager.getTransaction().begin();
        entityManager.persist(marque);
        entityManager.getTransaction().commit();
    }

    public Marque findById(Long id) {
        return entityManager.find(Marque.class, id);
    }

    public List<Marque> findAll() {
        TypedQuery<Marque> query = entityManager.createQuery("SELECT m FROM Marque m", Marque.class);
        return query.getResultList();
    }

    public void update(Marque marque) {
        entityManager.getTransaction().begin();
        entityManager.merge(marque);
        entityManager.getTransaction().commit();
    }

    public void delete(Long id) {
        entityManager.getTransaction().begin();
        Marque marque = entityManager.find(Marque.class, id);
        if (marque != null) {
            entityManager.remove(marque);
        }
        entityManager.getTransaction().commit();
    }

	public Marque findOrCreateMarque(String nom) {
		// Recherchez la catégorie par nom
        TypedQuery<Marque> query = entityManager.createQuery("SELECT m FROM Marque m WHERE m.nom = :nom", Marque.class);
        query.setParameter("nom", nom);

        List<Marque> marques = query.getResultList();

        if (!marques.isEmpty()) {
            // La catégorie existe déjà
            return marques.get(0);
        } else {
            // La catégorie n'existe pas
            Marque nouvelleMarque = new Marque();
            nouvelleMarque.setNom(nom);

            // Insérer la nouvelle catégorie dans la base de données
            entityManager.getTransaction().begin();
            entityManager.persist(nouvelleMarque);
            entityManager.getTransaction().commit();

            // Retourner la nouvelle catégorie
            return nouvelleMarque;
        }
	}
}

