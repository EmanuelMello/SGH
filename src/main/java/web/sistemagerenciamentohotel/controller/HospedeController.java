package web.sistemagerenciamentohotel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import web.sistemagerenciamentohotel.ajax.NotificacaoAlertify;
import web.sistemagerenciamentohotel.ajax.TipoNotificacaoAlertify;
import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.HospedeFilter;
import web.sistemagerenciamentohotel.pagination.PageWrapper;
import web.sistemagerenciamentohotel.repository.HospedeRepository;
import web.sistemagerenciamentohotel.service.HospedeService;

@Controller
@RequestMapping("/hospedes")
public class HospedeController {
	private static final Logger logger = LoggerFactory.getLogger(HospedeController.class);
	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Autowired
	private HospedeService hospedeService;

	
	@GetMapping("/abrirpesquisar")
	public String abrirPesquisar() {
		return "hospede/pesquisar";
	}
	
	@GetMapping("/pesquisar")
	public String pesquisar(HospedeFilter filtro, Model model,
			          @PageableDefault(size = 5) 
                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
                      Pageable pageable, HttpServletRequest request) {
		Page<Hospede> pagina = hospedeRepository.pesquisar(filtro, pageable);
		PageWrapper<Hospede> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "hospede/mostrarhospedes";
	}
	
	@GetMapping("/cadastrar")
	public String abrirCadastrar(Hospede hospede) {
		return "hospede/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Hospede hospede, BindingResult resultado) {
		if(resultado.hasErrors()) {
			logger.info("O Hospede recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "hospede/cadastrar";
		}else {
			hospedeService.salvar(hospede);
			return "redirect:/hospedes/cadastrosucesso";
		}
	}
	
	@GetMapping("/cadastrosucesso")
	public String mostrarCadastroSucesso(Hospede hospede,  Model model) {
		NotificacaoAlertify notificao = new NotificacaoAlertify("Cadastro de hospede efetuado com sucesso.",TipoNotificacaoAlertify.SUCESSO);
		model.addAttribute("notificacao",notificao);
		return "hospede/cadastrar";
	}
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(Hospede hospede,String queryString, Model model) {
		model.addAttribute("queryString",queryString);
		return "hospede/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(@Valid Hospede hospede, BindingResult resultado,String queryString, Model model,
			RedirectAttributes atributos) {
		if(resultado.hasErrors()) {
			logger.info("O Hospede recebido para alterar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			model.addAttribute("queryString",queryString);
			return "hospede/cadastrar";
		}else {
			hospedeService.alterar(hospede);
			atributos.addFlashAttribute("queryString",queryString);
			return "redirect:/hospedes/alterarsucesso";
		}
	}
	
	@GetMapping("/alterarsucesso")
	public String mostrarAlterarSucesso(Model model) {
		NotificacaoAlertify notificao = new NotificacaoAlertify("Alteração de hospede efetuado com sucesso.",TipoNotificacaoAlertify.SUCESSO);
		model.addAttribute("notificacao",notificao);
		return "forward:/hospedes/pesquisar?"+ model.getAttribute("queryString");
	}
	
	@PostMapping("/confirmarremocao")
	public String confirmarRemocao(Hospede hospede) {
		return "hospede/confirmarremocao";
	}
	
	@PostMapping("/remover")
	public String remover(Hospede hospede) {
		hospede.setStatus(Status.INATIVO);
		hospedeService.alterar(hospede);
		return "redirect:/hospedes/remocaosucesso";
	}
	
	@GetMapping("/remocaosucesso")
	public String mostrarRemocaoSucesso(Model model) {
		NotificacaoAlertify notificao = new NotificacaoAlertify("Remoção de hóspede efetuado com sucesso.",TipoNotificacaoAlertify.SUCESSO);
		model.addAttribute("notificacao",notificao);
		return "hospede/pesquisar";
	}
}
