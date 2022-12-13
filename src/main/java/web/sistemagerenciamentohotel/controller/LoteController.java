package web.sistemagerenciamentohotel.controller;
//package web.controlevacinacao.controller;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.data.web.SortDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import web.controlevacinacao.model.Lote;
//import web.controlevacinacao.model.Status;
//import web.controlevacinacao.model.Quarto;
//import web.controlevacinacao.model.filter.LoteFilter;
//import web.controlevacinacao.pagination.PageWrapper;
//import web.controlevacinacao.repository.LoteRepository;
//import web.controlevacinacao.repository.QuartoRepository;
//import web.controlevacinacao.service.LoteService;
//
//@Controller
//@RequestMapping("/lotes")
//public class LoteController {
//
//	@Autowired
//	private QuartoRepository vacinaRepository;
//	
//	@Autowired
//	private LoteRepository loteRepository;
//	
//	@Autowired
//	private LoteService loteService;
//	
//	@GetMapping("/abrirpesquisar")
//	public String abrirPesquisar(Model model) {
//		List<Quarto> vacinas = vacinaRepository.findAll();
//		model.addAttribute("vacinas", vacinas);
//		return "lote/pesquisar";
//	}
//	
//	@GetMapping("/pesquisar")
//	public String pesquisar(LoteFilter filtro, Model model,
//			          @PageableDefault(size = 5) 
//                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
//                      Pageable pageable, HttpServletRequest request) {
//		Page<Lote> pagina = loteRepository.pesquisar(filtro, pageable);
//		PageWrapper<Lote> paginaWrapper = new PageWrapper<>(pagina, request);
//		model.addAttribute("pagina", paginaWrapper);
//		return "lote/mostrarlotes";
//	}
//	
//	@GetMapping("/cadastrar")
//	public String abrirCadastrar(Lote lote, Model model) {
//		List<Quarto> vacinas = vacinaRepository.findAll();
//		model.addAttribute("vacinas", vacinas);
//		return "lote/cadastrar";
//	}
//	
//	@PostMapping("/cadastrar")
//	public String cadastrar(Lote lote) {
//		loteService.salvar(lote);
//		return "redirect:/lotes/cadastrosucesso";
//	}
//	
//	@GetMapping("/cadastrosucesso")
//	public String mostrarCadastroSucesso(Model model) {
//		model.addAttribute("mensagem", 
//				"Cadastro de lote efetuado com sucesso.");
//		return "mostrarmensagem";
//	}
//	
//	@PostMapping("/abriralterar")
//	public String abrirAlterar(Lote lote, Model model) {
//		List<Quarto> vacinas = vacinaRepository.findAll();
//		model.addAttribute("vacinas", vacinas);
//		return "lote/alterar";
//	}
//	
//	@PostMapping("/alterar")
//	public String alterar(Lote lote) {
//		loteService.alterar(lote);
//		return "redirect:/lotes/alterarsucesso";
//	}
//	
//	@GetMapping("/alterarsucesso")
//	public String mostrarAlterarSucesso(Model model) {
//		model.addAttribute("mensagem", 
//				"Alteração de lote efetuada com sucesso.");
//		return "mostrarmensagem";
//	}
//	
//	@PostMapping("/confirmarremocao")
//	public String confirmarRemocao(Lote lote) {
//		return "lote/confirmarremocao";
//	}
//	
//	@PostMapping("/remover")
//	public String remover(Lote lote) {
//		lote.setStatus(Status.INATIVO);
//		loteService.alterar(lote);
//		return "redirect:/lotes/remocaosucesso";
//	}
//	
//	@GetMapping("/remocaosucesso")
//	public String mostrarRemocaoSucesso(Model model) {
//		model.addAttribute("mensagem", 
//				"Remoção de lote efetuada com sucesso.");
//		return "mostrarmensagem";
//	}
//
//}
