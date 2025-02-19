package springbook.tr.patient.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import springbook.tr.FeNo.model.entity.Measurement;
import springbook.tr.user.model.entity.User;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Measurement> measurements;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false)
	private LocalDate birthdate;

	@Column(nullable = false, length = 1)
	private String gender;

	@Column(nullable = false)
	private BigDecimal height;

	@Column(nullable = false)
	private BigDecimal weight;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	public Patient(User user, String name, int age, LocalDate birthDate, String gender,
		BigDecimal weight, BigDecimal height) {
		assignUser(user);
		this.name = name;
		this.age = age;
		this.birthdate = birthDate;
		this.gender = gender;
		this.weight = weight;
		this.height = height;

	}

	public void assignUser(User user) {
		if (this.user != user) {
			this.user = user;
			user.addPatients(this);
		}
	}

	public void update(BigDecimal weight, BigDecimal height) {
		this.weight = weight;
		this.height = height;
	}
}
