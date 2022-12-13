package web.sistemagerenciamentohotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.sistemagerenciamentohotel.model.Consumo;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.ConsumoFilter;
import web.sistemagerenciamentohotel.pagination.PageWrapper;
import web.sistemagerenciamentohotel.repository.ConsumoRepository;
import web.sistemagerenciamentohotel.service.ConsumoService;

@Controller
@RequestMapping("/consumos")
public class ConsumoController {

	@Autowired
	private ConsumoRepository consumoRepository;
	
	@Autowired
	private ConsumoService consumoService;

	
	@GetMapping("/abrirpesquisar")
	public String abrirPesquisar() {
		return "consumo/pesquisar";
	}
	
	@GetMapping("/pesquisar")
	public String pesquisar(ConsumoFilter filtro, Model model,
			          @PageableDefault(size = 5) 
                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
                      Pageable pageable, HttpServletRequest request) {
		Page<Consumo> pagina = consumoRepository.pesquisar(filtro, pageable);
		PageWrapper<Consumo> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "consumo/mostrarconsumos";
	}
	
	@GetMapping("/cadastrar")
	public String abrirCadastrar(Consumo consumo) {
		return "consumo/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(Consumo consumo) {
		consumoService.salvar(consumo);
		return "redirect:/consumos/cadastrosucesso";
	}
	
	@GetMapping("/cadastrosucesso")
	public String mostrarCadastroSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Cadastro do consumo efetuado com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(Consumo consumo) {
		return "consumo/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(Consumo consumo) {
		consumoService.alterar(consumo);
		return "redirect:/consumos/alterarsucesso";
	}
	
	@GetMapping("/alterarsucesso")
	public String mostrarAlterarSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Alteração do consumo efetuada com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/confirmarremocao")
	public String confirmarRemocao(Consumo consumo) {
		return "consumo/confirmarremocao";
	}
	
	@PostMapping("/remover")
	public String remover(Consumo consumo) {
		consumo.setStatus(Status.INATIVO);
		consumoService.alterar(consumo);
		return "redirect:/consumos/remocaosucesso";
	}
	
	@GetMapping("/remocaosucesso")
	public String mostrarRemocaoSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Remoção do Consumo efetuada com sucesso.");
		return "mostrarmensagem";
	}

}
