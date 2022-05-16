package pe.com.bootcamp.microservice.account.deposit.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "tb_account")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
	@Id
	private String id;
	private String idCustomer;
	private String typeAccount;
	private String numberAccount;
	private String dateStart;
	private String dateEnd;	
	private String condition;
	private double availableBalance;
	private double amountAvaible;
}
