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
	
	@Transactional
	public void alterar(CheckIn checkIn) {
		checkInRepository.save(checkIn);
	}
	
	@Transactional
	public void remover(Long codigo) {
		checkInRepository.deleteById(codigo);
	}
}
