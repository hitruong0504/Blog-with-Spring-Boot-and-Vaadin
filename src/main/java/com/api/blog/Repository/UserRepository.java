package com.api.blog.Repository;

import com.api.blog.Domain.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(COALESCE(:startDate, NULL) IS NULL OR FUNCTION('DATE', u.createdAt) >= :startDate) " +
            "AND (COALESCE(:endDate, NULL) IS NULL OR FUNCTION('DATE', u.createdAt) <= :endDate) " +
            "AND (COALESCE(:searchTerm, NULL) IS NULL OR " +
            "     (LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "      LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "      LOWER(u.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "      LOWER(u.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%')))" +
            ")"
    )
    Page<User> findAllUsers(@Param("startDate") Date startDate,
                            @Param("endDate") Date endDate,
                            @Param("searchTerm") String searchTerm,
                            Pageable pageable);
}
