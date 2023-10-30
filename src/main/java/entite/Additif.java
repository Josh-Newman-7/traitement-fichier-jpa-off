package entite;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "additif")
public class Additif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @ManyToMany(mappedBy = "additifs")
    private Set<Produit> produits;

    // Constructeurs
	public Additif(String nom) {
		this.nom = nom;
	}
	
	public Additif() {}

	
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
	

    // Autres méthodes (CRUD, etc.) pour la gestion des allergènes.
}
