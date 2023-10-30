package entite;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "vitamine")
public class Vitamine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private VitamineValue type;

    @Column(name = "quantite")
    private Double quantite;


    @ManyToMany(mappedBy = "vitamines")
    private Set<Produit> produits;


    // Constructeurs
	public Vitamine(VitamineValue type, Double quantite) {
		this.type = type;
		this.quantite = quantite;
	}
	
	public Vitamine() {}

	
	//Getters / Setters
	public Long getId() {
		return id;
	}
	
	public VitamineValue getType() {
		return type;
	}
	public void setType(VitamineValue type) {
		this.type = type;
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

    // Autres méthodes (CRUD, etc.) pour la gestion des vitamines.
}

