package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // Se a 8080 estiver sendo usada usa outra porta
@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Para de rodar assim que termina
public class UsuarioRepositoryTest {
	
	//Injetando a Repository
    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll //Notação para sequencia o id 
    void start () {

    // Metodo de start, inserindo informações dentro do nosso banco de dados virtual H2

    usuarioRepository.deleteAll();
    usuarioRepository.save(new Usuario(0L, "João da Silva", "https://i.imgur.com/h4t8loa.jpg", "joao@email.com.br", "13465278"));

    usuarioRepository.save(new Usuario(0L, "Manuela da Silva", "https://i.imgur.com/NtyGneo.jpg", "manuela@email.com.br", "13465278"));

    usuarioRepository.save(new Usuario(0L, "Adriana da Silva", "https://i.imgur.com/5M2p5Wb.jpg", "adriana@email.com.br", "13465278"));

    usuarioRepository.save(new Usuario(0L, "Paulo Antunes", "https://i.imgur.com/FETvs20.jpg", "paulo@email.com.br", "13465278"));
}

    @Test
    @DisplayName("Retornar 1 usuário")
    public void deveRetornarUmUsuario() {

        Optional <Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
        assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));   //Pode ser nulo ou ter um valor

    }
    
    @Test
    @DisplayName("Retornar 3 usuários")
    public void deveRetornarTresUsuarios() {
    	List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
    	assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));

    }

}
