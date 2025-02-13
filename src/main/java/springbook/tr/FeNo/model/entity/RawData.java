package springbook.tr.FeNo.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class RawData {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "raw_data_id")
  private Long id;

 @Lob
 @Column(name = "raw_data",nullable = false, columnDefinition = "TEXT")
  private String rawData;

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;
}
