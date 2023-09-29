package com.mayuratech.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="bank_accounts")
public class UserDetails {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", accountNumber=" + accountNumber + ", balance=" + balance
				+ "]";
	}

	@Column(name="customer_name")
	private String name;
	
	@Column(name="account_number",unique=true)
	private String accountNumber;
	
	@Column(name="account_balance")
	private double balance;
}
