package pe.com.bootcamp.microservice.account.deposit.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
@Document(collection = "tbDeposit")
@Getter
@Setter
public class AccountDTO {
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
