package web.sistemagerenciamentohotel.controller;

import java.util.List;

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
import web.sistemagerenciamentohotel.model.Conta;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.ContaFilter;
import web.sistemagerenciamentohotel.pagination.PageWrapper;
import web.sistemagerenciamentohotel.repository.ConsumoRepository;
import web.sistemagerenciamentohotel.repository.ContaRepository;
import web.sistemagerenciamentohotel.service.ContaService;

@Controller
@RequestMapping("/contas")
public class ContaController {

	@Autowired
	private ConsumoRepository consumoRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ContaService contaService;
	
	@GetMapping("/abrirpesquisar")
	public String abrirPesquisar(Model model) {
		List<Consumo> consumos= consumoRepository.findAll();
		model.addAttribute("consumos", consumos);
		return "conta/pesquisar";
	}
	
	@GetMapping("/pesquisar")
	public String pesquisar(ContaFilter filtro, Model model,
			          @PageableDefault(size = 5) 
                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
                      Pageable pageable, HttpServletRequest request) {
		Page<Conta> pagina = contaRepository.pesquisar(filtro, pageable);
		PageWrapper<Conta> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "conta/mostrarcontas";
	}
	
	@GetMapping("/cadastrar")
	public String abrirCadastrar(Conta conta, Model model) {
		List<Consumo> consumos = consumoRepository.findAll();
		model.addAttribute("consumos", consumos);
		return "conta/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(Conta conta) {
		contaService.salvar(conta);
		return "redirect:/contas/cadastrosucesso";
	}
	
	@GetMapping("/cadastrosucesso")
	public String mostrarCadastroSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Cadastro de conta efetuado com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(Conta conta, Model model) {
		List<Consumo> consumos = consumoRepository.findAll();
		model.addAttribute("consumos", consumos);
		return "conta/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(Conta conta) {
		contaService.alterar(conta);
		return "redirect:/contas/alterarsucesso";
	}
	
	@GetMapping("/alterarsucesso")
	public String mostrarAlterarSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Alteração de conta efetuada com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/confirmarremocao")
	public String confirmarRemocao(Conta conta) {
		return "conta/confirmarremocao";
	}
	
	@PostMapping("/remover")
	public String remover(Conta conta) {
		conta.setStatus(Status.INATIVO);
		contaService.alterar(conta);
		return "redirect:/contas/remocaosucesso";
	}
	
	@GetMapping("/remocaosucesso")
	public String mostrarRemocaoSucesso(Model model) {
		model.addAttribute("mensagem", 
				"Remoção de conta efetuada com sucesso.");
		return "mostrarmensagem";
	}

}
