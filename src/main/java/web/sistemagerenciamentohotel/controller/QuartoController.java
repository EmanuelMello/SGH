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

import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.QuartoFilter;
import web.sistemagerenciamentohotel.pagination.PageWrapper;
import web.sistemagerenciamentohotel.repository.QuartoRepository;
import web.sistemagerenciamentohotel.service.QuartoService;

@Controller
@RequestMapping("/quartos")
public class QuartoController {

	@Autowired
	private QuartoRepository quartoRepository;
	
	@Autowired
	private QuartoService quartoService;

	
	@GetMapping("/abrirpesquisar")
	public String abrirPesquisar() {
		return "quarto/pesquisar";
	}
	
	@GetMapping("/pesquisar")
	public String pesquisar(QuartoFilter filtro, Model model,
			          @PageableDefault(size = 5) 
                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
                      Pageable pageable, HttpServletRequest request) {
		Page<Quarto> pagina = quartoRepository.pesquisar(filtro, pageable,false);
		PageWrapper<Quarto> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "quarto/mostrarquartos";
	}
	
	@GetMapping("/cadastrar")
	public String abrirCadastrar(Quarto quarto) {
		return "quarto/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(Quarto quarto) {
		quartoService.salvar(quarto);
		return "redirect:/quartos/cadastrosucesso";
	}
	
	@GetMapping("/cadastrosucesso")
	public String mostrarCadastroSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Cadastro do quarto efetuado com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(Quarto quarto) {
		return "quarto/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(Quarto quarto) {
		quartoService.alterar(quarto);
		return "redirect:/quartos/alterarsucesso";
	}
	
	@GetMapping("/alterarsucesso")
	public String mostrarAlterarSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Alteração do quarto efetuada com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/confirmarremocao")
	public String confirmarRemocao(Quarto quarto) {
		return "quarto/confirmarremocao";
	}
	
	@PostMapping("/remover")
	public String remover(Quarto quarto) {
		quarto.setStatus(Status.INATIVO);
		quartoService.alterar(quarto);
		return "redirect:/quartos/remocaosucesso";
	}
	
	@GetMapping("/remocaosucesso")
	public String mostrarRemocaoSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Remoção do Quarto efetuada com sucesso.");
		return "mostrarmensagem";
	}
}
