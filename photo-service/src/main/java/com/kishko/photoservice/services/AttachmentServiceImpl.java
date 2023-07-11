package com.kishko.photoservice.services;

import com.kishko.photoservice.entities.Attachment;
import com.kishko.photoservice.repositories.AttachmentRepository;
import com.kishko.userservice.entities.User;
import com.kishko.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(MultipartFile file, Long userId) throws Exception {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {

            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + fileName);
            }

            Attachment attachment = Attachment
                    .builder()
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .fileName(file.getOriginalFilename())
                    .userId(userId)
                    .build();

            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Couldn't save a file " + fileName + " \n " + e);
        }

    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow(
                () -> new Exception("File not found with id: " + fileId)
        );
    }
}
