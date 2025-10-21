package BioAuth.api.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Basic;
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
@Table(name = "USERS")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public User() {}

	public User(@NotBlank String fullName, @NotBlank String email, @NotBlank String password, byte[] userImage,
			List<byte[]> digitalImages) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.image = userImage;
		this.digitalImages = digitalImages;
	}

	@NotBlank
    @Column(nullable = false)
    private String fullName;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String password;

	@Basic(fetch = FetchType.LAZY)
    private byte[] image;

	@Basic(fetch = FetchType.LAZY)
	private List<byte[]> digitalImages = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

    public List<UserRole> getUserRoles() { return userRoles; }
    public void setUserRoles(List<UserRole> userRoles) { this.userRoles = userRoles; }
    
    public List<byte[]> getDigitalImagesRoles() { return digitalImages; }
    public void setDigitalImagesRoles(List<byte[]> digitalImages) { this.digitalImages = digitalImages; }

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + fullName + '\'' + ", email='" + email + '\'' + '}';
	}
}
