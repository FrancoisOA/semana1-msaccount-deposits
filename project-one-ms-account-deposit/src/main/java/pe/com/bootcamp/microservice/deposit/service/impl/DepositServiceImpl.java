package pe.com.bootcamp.microservice.deposit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import pe.com.bootcamp.microservice.deposit.dto.DepositDTO;
import pe.com.bootcamp.microservice.deposit.repository.IDepositRepository;
import pe.com.bootcamp.microservice.deposit.service.DepositService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DepositServiceImpl implements DepositService {
	@Autowired
	private IDepositRepository iDepositRepo;

	@Override
    public void createDep(DepositDTO deposit) {
    	iDepositRepo.save(deposit).subscribe();
    }

	@Override
    public Mono<DepositDTO> findByDepId(String id) {
        return iDepositRepo.findById(id);
    }

	@Override
    public Flux<DepositDTO> findAllDep() {
        return iDepositRepo.findAll();
    }

	@Override
    public Mono<DepositDTO> updateDep(String id, DepositDTO deposit) {
        return iDepositRepo.findById(id)
                .switchIfEmpty(Mono.error(new Exception("TASK_NOT_FOUND")))
                .map(fetchedDeposit  -> {
                	deposit.setId(id);
		                if (deposit.getIdSourceAccount() != null) {fetchedDeposit.setIdSourceAccount(deposit.getIdSourceAccount());}
		                if (deposit.getIdDestinationAccount() != null) {fetchedDeposit.setIdDestinationAccount(deposit.getIdDestinationAccount());}
		                if (deposit.getNameDestinationBank() != null) {fetchedDeposit.setNameDestinationBank(deposit.getNameDestinationBank());}
		                if (deposit.getDateDeposit() != null) {fetchedDeposit.setDateDeposit(deposit.getDateDeposit());}
		                if (deposit.getTimeDeposit() != null) {fetchedDeposit.setTimeDeposit(deposit.getTimeDeposit());}
		                if (deposit.getChannelDeposit() != null) {fetchedDeposit.setChannelDeposit(deposit.getChannelDeposit());}
		                if (deposit.getConceptDeposit() != null) {fetchedDeposit.setConceptDeposit(deposit.getConceptDeposit());}
		                if (deposit.getAmountDeposit() != null) {fetchedDeposit.setAmountDeposit(deposit.getAmountDeposit());}
                    if (deposit.getStatusDeposit() != null) {fetchedDeposit.setStatusDeposit(deposit.getStatusDeposit());}
                    if (deposit.getCodEmployee() != null) {fetchedDeposit.setCodEmployee(deposit.getCodEmployee());}
                    if (deposit.getComissionDeposit() != 0.0) {fetchedDeposit.setComissionDeposit(deposit.getComissionDeposit());}
                    return fetchedDeposit;
                })
                .flatMap(iDepositRepo::save);
    }

	@Override
    public Mono<Void> deleteDep(String id) {
        return iDepositRepo.deleteById(id);
    }
}
