package pe.com.bootcamp.microservice.account.service.deposit.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import pe.com.bootcamp.microservice.account.deposit.dto.AccountDTO;
import pe.com.bootcamp.microservice.account.deposit.entity.Account;
import pe.com.bootcamp.microservice.account.deposit.repository.IAccountRepository;
import pe.com.bootcamp.microservice.account.deposit.service.AccountService;
import pe.com.bootcamp.microservice.account.deposit.util.Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
	@Autowired
	 private final ReactiveMongoTemplate reactiveMongoTemplate;
	 
	@Autowired
	private final IAccountRepository iAccountRepository;

	@Override
	public Flux<Account> getAllAccount() {
		return iAccountRepository.findAll();
	}

	@Override 
	    public Mono<AccountDTO> createAccount(Mono<AccountDTO> account){	     
	        return account.map(Utils::dtoToEntity)
	                .flatMap(reactiveMongoTemplate::save)
	                .map(Utils::entityToDto);
	    }

	@Override
	public Mono<Account> updateAccount(String id, Account account) {
		
		return iAccountRepository.findById(id)
				.flatMap(bean -> {	            	
					bean.setAmountAvaible(account.getAmountAvaible());
					bean.setAvailableBalance(account.getAvailableBalance());
					bean.setCondition(account.getCondition());
					bean.setDateEnd(account.getDateEnd());
					bean.setDateStart(account.getDateStart());
					bean.setIdCustomer(account.getIdCustomer());
					bean.setNumberAccount(account.getNumberAccount());
					bean.setTypeAccount(account.getTypeAccount());
					return iAccountRepository.save(bean);
				});
	}

	@Override
	public Mono<Account> deleteAccount(String id) {
		return iAccountRepository.findById(id)
				.flatMap(existsAccount -> iAccountRepository.delete(existsAccount).then(Mono.just(existsAccount)));
	}

	@Override
	public Mono<Account> getAccountById(String id) {
		return iAccountRepository.findById(id);
	}
 

 


}
