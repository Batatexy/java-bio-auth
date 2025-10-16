package BioAuth.api.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "USER_FINGER_PRINTS")
public class UserFingerPrints {
	@Id
	@Column(name = "user_finger_prints_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public UserFingerPrints() {}
	public UserFingerPrints(Long id, @NotNull User user, @NotNull byte[] image) {
		this.user = user;
		this.image = image;
	}

	@NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@NotNull
	@Basic(fetch = FetchType.LAZY)
    private byte[] image;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

}
