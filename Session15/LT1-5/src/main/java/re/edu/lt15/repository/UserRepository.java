package re.edu.lt15.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import re.edu.lt15.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = """
    select u
    from User u
    where u.email = :username
    """)
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByPhone(String phone);
}
