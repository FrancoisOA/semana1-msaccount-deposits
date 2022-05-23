package pe.com.bootcamp.microservice.deposit.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.com.bootcamp.microservice.deposit.dto.DepositDTO;

@Repository
public interface IDepositRepository extends ReactiveMongoRepository<DepositDTO, String>{
}
