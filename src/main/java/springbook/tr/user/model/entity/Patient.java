package springbook.tr.user.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import springbook.tr.FeNo.model.entity.Measurement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
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
  private char gender;

  @Column(nullable = false)
  private BigDecimal height;

  @Column(nullable = false)
  private BigDecimal weight;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;
}
