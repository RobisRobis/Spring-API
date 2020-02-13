package sp.senai.br.Odonto.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sp.senai.br.Odonto.model.Dentista;
import sp.senai.br.Odonto.repository.DentistaRepository;

@RestController
@RequestMapping("/odonto")
@CrossOrigin
public class DentistaResource {

	@Autowired
	private DentistaRepository dentistaRepository;
	
	//Lista todos os dentistas
	
	@GetMapping("/dentistas")
	public List<Dentista> getDentistas() {
		return dentistaRepository.findAll();
	}
	
	@GetMapping("/pacientes")
	public String teste() {
		return "Olá Mundo";
	}
	
	@GetMapping("/dentistas/{codigo}")
	public ResponseEntity<?> getDentista(@PathVariable Long codigo) {
		Optional dentistaConsultado = dentistaRepository.findById(codigo);
		return 
				dentistaConsultado.isPresent() ? 
						ResponseEntity.ok(dentistaConsultado.get()) : 
						ResponseEntity.notFound().build();
	}
	
	@PostMapping("/dentistas")
	@ResponseStatus(HttpStatus.CREATED)
	public Dentista gravar (@Valid @RequestBody Dentista dentista) {
		Dentista dentistaNovo = dentistaRepository.save(dentista);
		return dentistaNovo;
	}
	
	@DeleteMapping("/dentistas/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long codigo) {
		dentistaRepository.deleteById(codigo);
	}
	
	@PutMapping("/dentistas/{codigo}")
	public ResponseEntity<Dentista> atualizar(@PathVariable Long codigo,@Valid @RequestBody Dentista dentista){
			
		Dentista dentistaBanco = dentistaRepository.findById(codigo).get();
		
		BeanUtils.copyProperties(dentista, dentistaBanco, "codigo");
		
		dentistaRepository.save(dentista);
		
		return ResponseEntity.ok(dentista);
		}
	
}
