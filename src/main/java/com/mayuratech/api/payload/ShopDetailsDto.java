package com.mayuratech.api.payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopDetailsDto {
	
	@Size(min = 6,message = "name should atleast contain 6 characters")
	private String shopName;
	
	@NotNull@Pattern(regexp = "^\\d{10}$",message = "phone number is not valid")
	private String mobile;
	
	@Email(message = "Email is not valid")
	private String email;
	
	@NotBlank(message="Address field should not be null")
	private String address;
}
