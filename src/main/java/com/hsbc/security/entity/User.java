package com.hsbc.security.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_tab")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usr_id_col")
	private Integer userId;
	@Column(name = "usr_name_col")
	private String userName;
	@Column(name = "usr_email_col")
	private String userEmail;
	@Column(name = "usr_pwd_col")
	private String userPassword;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "authorities_tab", 
					joinColumns = @JoinColumn(name = "userId"))
	@Column(name = "usr_authroties_col")
	private Set<String> authorities;

}
