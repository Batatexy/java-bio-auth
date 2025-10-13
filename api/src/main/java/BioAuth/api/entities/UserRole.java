package BioAuth.api.entities;

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
@Table(name = "USER_ROLES")
public class UserRole {
	@Id
	@Column(name = "user_role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public UserRole() {}
	public UserRole(@NotNull User user, @NotNull Role role) {
		this.user = user;
		this.role = role;
	}

	@NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

	@Override
	public String toString() {
		return "UserRole{" + "id=" + id + ", userName='" + user.getFullName() + '\'' + ", roleName='" + role.getName()
				+ '\'' + '}';
	}
}
