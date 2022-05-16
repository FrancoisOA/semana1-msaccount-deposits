package pe.com.bootcamp.microservice.account.deposit.util;

import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;

import pe.com.bootcamp.microservice.account.deposit.dto.AccountDTO;
import pe.com.bootcamp.microservice.account.deposit.entity.Account;

public class Utils {

	public static Consumer<HttpHeaders> mapToHeaders(Map<String, String> headers) {
		return h -> headers.forEach((k, v) -> h.set(k, v));
	}
	
    public static AccountDTO entityToDto(Account account){
        var accountdto=new AccountDTO();
        BeanUtils.copyProperties(account,accountdto);
        return accountdto;
    }
    public static Account dtoToEntity(AccountDTO accountdto){
        var entity=new Account();
        BeanUtils.copyProperties(accountdto,entity);
        return entity;
    }
    
}
