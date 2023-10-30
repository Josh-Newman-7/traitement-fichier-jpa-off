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
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "quantite")
    private Double quantite;


    @ManyToMany(mappedBy = "ingredients")
    private Set<Produit> produits;


    // Constructeurs
	public Ingredient(String nom, Double quantite) {
		this.nom = nom;
		this.quantite = quantite;
	}
	
	public Ingredient() {}
	
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

	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	public Set<Produit> getProduits() {
		return produits;
	}
	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

	public void setProduit(Produit produit) {
		this.produits.add(produit);	
	}
	
}
