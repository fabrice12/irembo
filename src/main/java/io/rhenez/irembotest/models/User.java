package io.rhenez.irembotest.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private Long age;
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Column(name = "martal_status")
    private String martalStatus;
    @Column(name = "email")
    private String email;
    @Column(name="profile_picture")
    private String profilePicture;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;

    @Column(name = "nationality")
    private String nationality;
    @Column(name = "identification_type")
    private String identityType;
    @Column(name = "identification_number")
    private String identityNumber;
    @Column(name = "identity_attachment")
    private String identityAttachment;
    @Column(name = "status")
    private String status;
    @Column(name="otp")
    private String OTP;
    @Column(name="otp_token")
    private String OtpToken;
    @Column(name="created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;




}
