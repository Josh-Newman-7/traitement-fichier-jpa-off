package entite;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "marque")
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom de la marque
    @Column(name = "nom")
    private String nom;

    // Liste des produits associés à cette marque
    @OneToMany(mappedBy = "marque")
    private Set<Produit> produits;


    // Constructeur
	public Marque(String nom) {
		this.nom = nom;
	}
	
	public Marque() {}
	
	
    // Getters et setters
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
    
	
	// Méthodes equals()

    // Autres méthodes (CRUD, etc.) pour la gestion des marques.
}
