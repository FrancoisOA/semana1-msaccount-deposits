package pe.com.bootcamp.microservice.deposit.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Document(collection = "tb_deposit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DepositDTO {
	@Id
	private String id;
	private String idSourceAccount;
	private String idDestinationAccount;
	private String nameDestinationBank;
	private String dateDeposit;
	private String timeDeposit;
	private String channelDeposit;
	private String conceptDeposit;
	private String amountDeposit;
	private String statusDeposit;
	private String codEmployee;
	private double comissionDeposit;
}