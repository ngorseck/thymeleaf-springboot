package com.samanecorp.thymeleaf1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "firstName", length = 250, nullable = false)
	private String firstName;
	@Column(name = "lastName", length = 200, nullable = false)
	private String lastName;
	@Column(name = "email", length = 250, nullable = false, unique = true)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	

}
