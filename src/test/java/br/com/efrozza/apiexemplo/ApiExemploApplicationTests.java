package br.com.efrozza.apiexemplo;

import br.com.efrozza.apiexemplo.domain.Trilha;
import br.com.efrozza.apiexemplo.repository.TrilhaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
class ApiExemploApplicationTests {

	@Autowired
	private TrilhaRepository repository;

	@Test
	void main() {
		ApiExemploApplication.main(new String[]{});
	}

	@Test
	public void testTrilha(){
		Trilha trilha3 = new Trilha();
		trilha3.setNome("Trilha 3");
		trilha3.setId(3);
		repository.save(trilha3);
	}

}
