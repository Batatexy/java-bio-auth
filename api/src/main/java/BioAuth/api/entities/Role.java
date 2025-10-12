package BioAuth.api.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ROLES")
public class Role {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public Role() {}
    public Role(Long id, @NotBlank String name, @NotBlank String description, List<UserRole> userRoles) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.userRoles = userRoles;
	}

	@NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<UserRole> getUserRoles() { return userRoles; }
    public void setUserRoles(List<UserRole> userRoles) { this.userRoles = userRoles; }

	@Override
	public String toString() {
		return "Role{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + '}';
	}
}
