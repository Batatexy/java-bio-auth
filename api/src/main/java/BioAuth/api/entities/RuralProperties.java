package BioAuth.api.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "RURAL_PROPERTIES")
public class RuralProperties {
	@Id
	@Column(name = "place_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public RuralProperties() {}

	public RuralProperties(@NotBlank String placeName, @NotBlank String description, @NotBlank String ownerName, @NotBlank String address,
			@NotBlank float size, @NotBlank String agroChemicals, @NotBlank Long agrochemicalsLevelOrder,
			@NotBlank Long levelOrder, byte[] image) {
		this.placeName = placeName;
		this.description = description;
		this.ownerName = ownerName;
		this.address = address;
		this.size = size;
		this.agroChemicals = agroChemicals;
		this.agrochemicalsLevelOrder = agrochemicalsLevelOrder;
		this.levelOrder = levelOrder;
		this.image = image;
	}

	@NotBlank
    @Column(nullable = false)
    private String placeName;
	
	@NotBlank
    @Column(nullable = false)
    private String description;

    @NotBlank
    @Column(nullable = false)
    private String ownerName;

    @NotBlank
    @Column(nullable = false)
    private String address;
    
	@NotNull
    @Column(nullable = false)
    private float size;
    
    @NotBlank
    @Column(nullable = false)
    private String agroChemicals;
    
	@NotNull
    @Column(nullable = false)
    private Long agrochemicalsLevelOrder;
    
	@NotNull
    @Column(nullable = false)
    private Long levelOrder;

	@Basic(fetch = FetchType.LAZY)
    private byte[] image;

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	
	public String getPlaceName() { return placeName; }
	public void setPlaceName(String placeName) { this.placeName = placeName; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public String getOwnerName() { return ownerName; }
	public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
	
	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }
	
	public float getSize() { return size; }
	public void setSize(float size) { this.size = size; }
	
	public String getAgroChemicals() { return agroChemicals; }
	public void setAgroChemicals(String agroChemicals) { this.agroChemicals = agroChemicals; }
	
	public Long getAgrochemicalsLevelOrder() { return agrochemicalsLevelOrder; }
	public void setAgrochemicalsLevelOrder(Long agrochemicalsLevelOrder) { this.agrochemicalsLevelOrder = agrochemicalsLevelOrder; }
	
	public Long getLevelOrder() { return levelOrder; }
	public void setLevelOrder(Long levelOrder) { this.levelOrder = levelOrder; }
	
	public byte[] getImage() { return image; }
	public void setImage(byte[] image) { this.image = image; }
	
	@Override
	public String toString() {
		return "RuralProperties{" + "id=" + id + ", placeName='" + placeName + '\'' + ", address='" + address + '\'' + '}';
	}
}
