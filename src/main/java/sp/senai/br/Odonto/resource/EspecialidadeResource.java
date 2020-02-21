package sp.senai.br.Odonto.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sp.senai.br.Odonto.model.Especialidade;
import sp.senai.br.Odonto.repository.EspecialidadeRepository;

@RestController
@RequestMapping("/odonto")
@CrossOrigin
public class EspecialidadeResource {

	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	
	@GetMapping("/especialidades")
	public List<Especialidade> getEspecialidade(){
		return especialidadeRepository.findAll();
	}
	
}
