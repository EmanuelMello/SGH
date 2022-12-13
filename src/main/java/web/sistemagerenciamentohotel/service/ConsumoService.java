package web.sistemagerenciamentohotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sistemagerenciamentohotel.model.Consumo;
import web.sistemagerenciamentohotel.repository.ConsumoRepository;

@Service
public class ConsumoService {

	@Autowired
	private ConsumoRepository consumoRepository;
	
	@Transactional
	public void salvar(Consumo consumo) {
		consumoRepository.save(consumo);
	}
	
	@Transactional
	public void alterar(Consumo consumo) {
		consumoRepository.save(consumo);
	}
	
	@Transactional
	public void remover(Long codigo) {
		consumoRepository.deleteById(codigo);
	}
	
	
}

