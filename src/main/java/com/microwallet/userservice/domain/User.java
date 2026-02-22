package com.microwallet.userservice.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private ObjectId id; // Mongo ObjectId

    private String email; // Unique email address

    private String password; // Hashed password

    private String firstName;

    private String lastName;

    private Instant createdAt;

    private UserStatus status;

}
