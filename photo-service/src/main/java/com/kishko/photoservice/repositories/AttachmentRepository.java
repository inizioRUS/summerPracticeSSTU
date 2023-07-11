package com.kishko.photoservice.repositories;

import com.kishko.photoservice.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {

    void deleteAttachmentByUserId(Long userId);

}
