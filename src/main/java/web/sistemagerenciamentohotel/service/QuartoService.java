package web.sistemagerenciamentohotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.repository.QuartoRepository;

@Service
public class QuartoService {

	@Autowired
	private QuartoRepository quartoRepository;
	
	@Transactional
	public void salvar(Quarto quarto) {
		quartoRepository.save(quarto);
	}
	
	@Transactional
	public void alterar(Quarto quarto) {
		quartoRepository.save(quarto);
	}
	
	@Transactional
	public void remover(Long codigo) {
		quartoRepository.deleteById(codigo);
	}
	
	
}

