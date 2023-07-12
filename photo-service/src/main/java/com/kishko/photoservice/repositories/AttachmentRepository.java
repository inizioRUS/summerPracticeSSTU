package com.kishko.photoservice.repositories;

import com.kishko.photoservice.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, String> {

    void deleteAttachmentByUserId(Long userId);

}
