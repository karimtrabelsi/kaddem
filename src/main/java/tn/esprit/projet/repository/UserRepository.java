package tn.esprit.projet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projet.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);

    User findUserByEmail(String userEmail);
    User findByPhoneNumber(String number);

    User findByEmail(String email);

    User findByToken(String token);


    public User findByFirstNameAndLastName(String firstname, String lastname);

    User findByLastName(String lastname);


}
