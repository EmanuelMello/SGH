package web.sistemagerenciamentohotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sistemagerenciamentohotel.model.Conta;
import web.sistemagerenciamentohotel.repository.ContaRepository;

@Service
public class ContaService {

	@Autowired
	private ContaRepository contaRepository;
	
	@Transactional
	public void salvar(Conta conta) {
		contaRepository.save(conta);
	}
	
	@Transactional
	public void alterar(Conta conta) {
		contaRepository.save(conta);
	}
	
	@Transactional
	public void remover(Long codigo) {
		contaRepository.deleteById(codigo);
	}
	
}
