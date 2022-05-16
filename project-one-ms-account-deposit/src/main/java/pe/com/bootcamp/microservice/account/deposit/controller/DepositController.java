package pe.com.bootcamp.microservice.account.deposit.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.com.bootcamp.microservice.deposit.deposit.dto.depositDTO;
import pe.com.bootcamp.microservice.deposit.deposit.entity.deposit;
import pe.com.bootcamp.microservice.deposit.deposit.service.depositService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/deposit")
public class depositController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private final depositService depositService;
 

	@GetMapping(path = "/list")
	public Mono<ResponseEntity<Flux<deposit>>> getAlldeposit() {
		Flux<deposit> list=this.depositService.getAlldeposit();
		return Mono.just(
				ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(list));
	}	   
 
	   

	@PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<depositDTO> create(@RequestBody Mono<depositDTO> depositDTO) {
        return this.depositService.createdeposit(depositDTO);
    }
    

		 

	@PutMapping("update/{id}")
	private ResponseEntity<Mono<depositDTO>> updatedepositById(@PathVariable String id, @RequestBody Mono<depositDTO> depositdto){
 
		deposit depositRequest = modelMapper.map(depositdto, deposit.class);
		Mono<deposit> deposit =  depositService.updatedeposit(id, depositRequest);
		
		Mono<depositDTO> depositResponse = Mono.just(modelMapper.map(deposit, depositDTO.class));
		return ResponseEntity.ok().body(depositResponse); 
	}

	@DeleteMapping("delete/{id}")
	private Mono<ResponseEntity<Void>> deletedepositById(@PathVariable String id){
		return this.depositService.deletedeposit(id)
				.map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/details/{id}")
	private ResponseEntity<Mono<depositDTO>> getdepositById(@PathVariable String id){
 
		Mono<deposit> deposit= depositService.getdepositById(id);

		Mono<depositDTO> depositResponse = Mono.just(modelMapper.map(deposit, depositDTO.class));
		return ResponseEntity.ok().body(depositResponse);
		 


	}
}