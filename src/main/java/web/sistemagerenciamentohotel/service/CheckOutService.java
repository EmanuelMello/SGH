package web.sistemagerenciamentohotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.sistemagerenciamentohotel.model.CheckOut;
import web.sistemagerenciamentohotel.repository.CheckOutRepository;

@Service
public class CheckOutService {
	@Autowired
	private CheckOutRepository checkOutRepository;
	
	@Transactional
	public void salvar(CheckOut checkOut) {
		checkOutRepository.save(checkOut);
	}
	
	@Transactional
	public void alterar(CheckOut checkOut) {
		checkOutRepository.save(checkOut);
	}
	
	@Transactional
	public void remover(Long codigo) {
		checkOutRepository.deleteById(codigo);
	}
}
