package web.sistemagerenciamentohotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sistemagerenciamentohotel.model.CheckIn;
import web.sistemagerenciamentohotel.repository.CheckInRepository;

@Service
public class CheckInService {
	
	@Autowired
	private CheckInRepository checkInRepository;
	
	@Transactional
	public void salvar(CheckIn checkIn) {
		checkInRepository.save(checkIn);
	}
	
//	@Transactional
//	public void alterar(Hospedagem hospedagem) {
//		hospedagemRepository.save(hospedagem);
//	}
//	
//	@Transactional
//	public void remover(Long codigo) {
//		hospedagemRepository.deleteById(codigo);
//	}
}
