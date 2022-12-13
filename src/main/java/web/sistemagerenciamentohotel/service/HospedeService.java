package web.sistemagerenciamentohotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.repository.HospedeRepository;

@Service
public class HospedeService {
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Transactional
	public void salvar(Hospede hospede) {
		hospedeRepository.save(hospede);
	}
	
	@Transactional
	public void alterar(Hospede hospede) {
		hospedeRepository.save(hospede);
	}
	
	@Transactional
	public void remover(Long codigo) {
		hospedeRepository.deleteById(codigo);
	}
}
