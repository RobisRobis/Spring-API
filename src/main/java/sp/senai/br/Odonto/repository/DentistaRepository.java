package sp.senai.br.Odonto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sp.senai.br.Odonto.model.Dentista;

public interface DentistaRepository extends JpaRepository<Dentista, Long>{

		List<Dentista> findByCro(String cro);
		
		@Query(value = "SELECT d FROM Dentista d WHERE d.nome LIKE %:nome%")
		List<Dentista> findByLikeNome(@Param("nome")String nome);
		
		/*
		 * SQL
		 * SELECT * FROM tbl_dentista WHERE nome LIKE '%PEDRO%' 
		 * 
		 * 
		 * JPQL/HQL
		 * SELECT dentista FROM Dentista dentista WHERE dentista.nome LIKE '%PEDRO%'
		 * 
		 */
	
}
