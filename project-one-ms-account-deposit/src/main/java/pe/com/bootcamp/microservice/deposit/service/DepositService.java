package pe.com.bootcamp.microservice.deposit.service;

import pe.com.bootcamp.microservice.deposit.dto.DepositDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepositService {
    public void createDep(DepositDTO d);    
    public Mono<DepositDTO> findByDepId(String id); 
    public Flux<DepositDTO> findAllDep(); 
    public Mono<DepositDTO> updateDep(String id, DepositDTO d); 
    public Mono<Void> deleteDep(String id);   
}
