package springbook.tr.user.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Patient> patients;
  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User() {

  }

  public void passwordEncoder(String password) {
    this.password = BCrypt.hashpw(password, BCrypt.gensalt());
  }

}
