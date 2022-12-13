package web.sistemagerenciamentohotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.sistemagerenciamentohotel.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNomeUsuarioIgnoreCase(String nomeUsuario);
	
}
