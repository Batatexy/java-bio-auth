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
	public User(@NotBlank String fullName, @NotBlank String email, @NotBlank String password, byte[] image,
			byte[] digitalImage1, byte[] digitalImage2, byte[] digitalImage3, byte[] digitalImage4,
			byte[] digitalImage5, byte[] digitalImage6) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
		this.image = image;
		this.digitalImage1 = digitalImage1;
		this.digitalImage2 = digitalImage2;
		this.digitalImage3 = digitalImage3;
		this.digitalImage4 = digitalImage4;
		this.digitalImage5 = digitalImage5;
		this.digitalImage6 = digitalImage6;
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
    private byte[] digitalImage1;
	
	@Basic(fetch = FetchType.LAZY)
    private byte[] digitalImage2;
	
	@Basic(fetch = FetchType.LAZY)
    private byte[] digitalImage3;
	
	@Basic(fetch = FetchType.LAZY)
    private byte[] digitalImage4;
	
	@Basic(fetch = FetchType.LAZY)
    private byte[] digitalImage5;
	
	@Basic(fetch = FetchType.LAZY)
    private byte[] digitalImage6;
	
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
    
	public byte[] getDigitalImage1() { return digitalImage1; }
	public void setDigitalImage1(byte[] digitalImage1) { this.digitalImage1 = digitalImage1; }

	public byte[] getDigitalImage2() { return digitalImage2; }
	public void setDigitalImage2(byte[] digitalImage2) { this.digitalImage2 = digitalImage2; }

	public byte[] getDigitalImage3() { return digitalImage3; }
	public void setDigitalImage3(byte[] digitalImage3) { this.digitalImage3 = digitalImage3; }

	public byte[] getDigitalImage4() { return digitalImage4; }
	public void setDigitalImage4(byte[] digitalImage4) { this.digitalImage4 = digitalImage4; }

	public byte[] getDigitalImage5() { return digitalImage5; }
	public void setDigitalImage5(byte[] digitalImage5) { this.digitalImage5 = digitalImage5; }

	public byte[] getDigitalImage6() { return digitalImage6; }
	public void setDigitalImage6(byte[] digitalImage6) { this.digitalImage6 = digitalImage6; }

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", name='" + fullName + '\'' + ", email='" + email + '\'' + '}';
	}
}
