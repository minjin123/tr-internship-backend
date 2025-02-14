package springbook.tr.FeNo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import springbook.tr.user.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Measurement {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "measurement_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Lob
  @Column(name = "raw_content",nullable = false, columnDefinition = "TEXT")
  private String rawContent;
  @Column(name = "flow_rate" , nullable = false)
  private BigDecimal flowRate;

  @Column(name = "nitric_oxide" , nullable = false)
  private BigDecimal nitricOxide;

  @Column(nullable = false)
  private BigDecimal pressure;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;
}
