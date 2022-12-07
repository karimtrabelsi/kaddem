package tn.esprit.projet.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        private String firstName;
        private String lastName;
        @NotBlank
        @Size(max = 20)
        private String username;
        @NotBlank
        @Size(max = 50)
        @Email
        private String email;
        @NotBlank
        @Size(max = 120)
        private String password;
        private String token;
        private int verificationCode;

        private boolean enabled;
        private String phoneNumber;

        @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
        @JsonIgnoreProperties({"user"})
        private Set<Contrat> contrats;
        @ManyToOne
        Departement departement;
        @ManyToMany(cascade = CascadeType.ALL,mappedBy = "Users")
        private List<Formation> Formation;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Set<Role> roles = new HashSet<>();

        public User() {
        }

        public User(String firstName, String lastName, String username, String email, String password,String phoneNumber,boolean enabled) {
                this.firstName = firstName;
                this.lastName = lastName;
                this.username = username;
                this.email = email;
                this.phoneNumber= phoneNumber;
                this.enabled= enabled;
                this.password = password;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public Set<Role> getRoles() {
                return roles;
        }

        public void setRoles(Set<Role> roles) {
                this.roles = roles;
        }

        public String getToken() {
                return token;
        }

        public void setToken(String token) {
                this.token = token;
        }

        public int getVerificationCode() {
                return verificationCode;
        }

        public void setVerificationCode(int verificationCode) {
                this.verificationCode = verificationCode;
        }

        public boolean isEnabled() {
                return enabled;
        }

        public void setEnabled(boolean enabled) {
                this.enabled = enabled;
        }

        public String getPhoneNumber() {
                return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
        }
}
