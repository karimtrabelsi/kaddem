package tn.esprit.projet.services;

import java.util.List;

import tn.esprit.projet.entities.Role;
import tn.esprit.projet.entities.User;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();

    void updateResetPasswordToken(String token,String email);

    User get(String token);

    void updatePassword(User user,String newPassword);

}
