package study.datajpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * 순수 jpa baseEntity
 */
@MappedSuperclass
@Data
public class JpaBaseEntity {

    @Column(updatable = false) //실수라도 바꿔도 업데이트가 되지 않음
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        updatedDate = now; //등록일, 수정일 맞춰주는게 편하다.
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }




}
