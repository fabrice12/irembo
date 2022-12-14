package io.rhenez.irembotest.repositories;

import io.rhenez.irembotest.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email=:email")
    public User getUserByEmail(@Param("email") String email);
    @Query("SELECT u FROM User u WHERE u.OtpToken=:token")
    public User findUserByOtpToken(@Param("token")String otpToken);
}