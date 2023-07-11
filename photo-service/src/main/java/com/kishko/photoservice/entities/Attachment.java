package com.kishko.photoservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kishko.userservice.entities.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "fileName")
    private String fileName;

    @Column(name = "fileType")
    private String fileType;

    @Lob
    private byte[] data;

    @Column(name = "userId")
    private Long userId;

}
