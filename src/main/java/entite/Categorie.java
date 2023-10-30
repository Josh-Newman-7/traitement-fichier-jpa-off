package entite;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categorie")
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToMany(mappedBy = "categorie")
    private Set<Produit> produits;

    
    // Constructeurs
	public Categorie(String nom) {
		this.nom = nom;
	}

	public Categorie() {}

	
	//Getters / Setters
	public Long getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

    // equals()

    // Autres méthodes (CRUD, etc.) pour la gestion des catégories.
}

