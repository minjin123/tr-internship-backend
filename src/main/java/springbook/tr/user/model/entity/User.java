package springbook.tr.user.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import springbook.tr.patient.model.entity.Patient;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NonNull
	@Column(unique = true, nullable = false)
	private String username;

	@NonNull
	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Patient> patients = new LinkedList<>();

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	public void passwordEncoder(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public void addPatients(Patient patient) {
		if (!patients.contains(patient)) {
			patients.add(patient);
			patient.assignUser(this);
		}
	}
}
