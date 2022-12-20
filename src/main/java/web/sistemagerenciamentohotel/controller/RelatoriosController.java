package web.sistemagerenciamentohotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.sistemagerenciamentohotel.service.RelatorioService;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {

	private static final Logger logger = LoggerFactory.getLogger(RelatoriosController.class);
	
	@Autowired
	private RelatorioService relatorioService;
	
	
	@GetMapping("/relatorio")
	public ResponseEntity<byte[]> gerarRelatorioCompletoHotel() {
		logger.trace("Entrou em gerarRelatorioHotel");
		logger.debug("Gerando relatório do Hotel");
		
		byte[] relatorio = relatorioService.gerarRelatorioCompletoHotel();
		
		logger.debug("Relatório completo do hotel gerado");
		logger.debug("Retornando o relatório completo do hotel");
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RelatorioHotelCompleto.pdf")
				.body(relatorio);
	}
	
}