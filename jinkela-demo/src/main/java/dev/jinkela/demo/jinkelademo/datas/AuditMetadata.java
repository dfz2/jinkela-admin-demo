package dev.jinkela.demo.jinkelademo.datas;

import java.time.Instant;

import org.springframework.boot.actuate.audit.listener.AuditListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditListener.class)
public class AuditMetadata {

  @CreatedBy
  private String createdBy;

  @CreatedDate
  private Instant createdAt;

  @LastModifiedBy
  private String modifiedBy;

  @LastModifiedDate
  private Instant modifiedAt;

}
