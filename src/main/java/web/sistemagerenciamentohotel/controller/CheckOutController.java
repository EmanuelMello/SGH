package web.sistemagerenciamentohotel.controller;

import java.util.List;

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
import web.sistemagerenciamentohotel.model.CheckIn;
import web.sistemagerenciamentohotel.model.CheckOut;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.filter.CheckOutFilter;
import web.sistemagerenciamentohotel.pagination.PageWrapper;
import web.sistemagerenciamentohotel.repository.CheckInRepository;
import web.sistemagerenciamentohotel.repository.CheckOutRepository;
import web.sistemagerenciamentohotel.service.CheckOutService;

@Controller
@RequestMapping("/checkouts")
public class CheckOutController {
	private static final Logger logger = LoggerFactory.getLogger(CheckOutController.class);
	@Autowired
	private CheckOutRepository checkOutRepository;
	
	@Autowired
	private CheckOutService checkOutService;
	
	@Autowired
	private CheckInRepository checkInRepository;

	
	@GetMapping("/abrirpesquisar")
	public String abrirPesquisar(Model model) {
		List<CheckIn> checkins= checkInRepository.findAll();
		model.addAttribute("checkins", checkins);
		return "checkOut/pesquisar";
	}
	
	@GetMapping("/pesquisar")
	public String pesquisar(CheckOutFilter filtro, Model model,
			          @PageableDefault(size = 5) 
                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
                      Pageable pageable, HttpServletRequest request) {
		Page<CheckOut> pagina = checkOutRepository.pesquisar(filtro, pageable);
		PageWrapper<CheckOut> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "checkOut/mostrarcheckOuts";
	}
	
	@GetMapping("/abrircadastrar")
	public String abrirCadastrar(Model model, CheckOut checkOut) {
		List<CheckIn> checkins= checkInRepository.findAll();
		model.addAttribute("checkins", checkins);
		
		return "checkOut/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@Valid CheckOut checkOut, BindingResult resultado) {
		if(resultado.hasErrors()) {
			logger.info("O CheckOut recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "checkOut/cadastrar";
		}else {
			checkOutService.salvar(checkOut);
			return "redirect:/checkOuts/cadastrosucesso";
		}
	}
	
	@GetMapping("/cadastrosucesso")
	public String mostrarCadastroSucesso(CheckOut checkOut,  Model model) {
		NotificacaoAlertify notificao = new NotificacaoAlertify("Cadastro de checkOut efetuado com sucesso.",TipoNotificacaoAlertify.SUCESSO);
		model.addAttribute("notificacao",notificao);
		return "checkOut/cadastrar";
	}
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(CheckOut checkOut,String queryString, Model model) {
		model.addAttribute("queryString",queryString);
		return "checkOut/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(@Valid CheckOut checkOut, BindingResult resultado,String queryString, Model model,
			RedirectAttributes atributos) {
		if(resultado.hasErrors()) {
			logger.info("O CheckOut recebido para alterar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			model.addAttribute("queryString",queryString);
			return "checkOut/cadastrar";
		}else {
			checkOutService.alterar(checkOut);
			atributos.addFlashAttribute("queryString",queryString);
			return "redirect:/checkOuts/alterarsucesso";
		}
	}
	
	@GetMapping("/alterarsucesso")
	public String mostrarAlterarSucesso(Model model) {
		NotificacaoAlertify notificao = new NotificacaoAlertify("Alteração de checkOut efetuado com sucesso.",TipoNotificacaoAlertify.SUCESSO);
		model.addAttribute("notificacao",notificao);
		return "forward:/checkOuts/pesquisar?"+ model.getAttribute("queryString");
	}
	
	@PostMapping("/confirmarremocao")
	public String confirmarRemocao(CheckOut checkOut) {
		return "checkOut/confirmarremocao";
	}
	
	@PostMapping("/remover")
	public String remover(CheckOut checkOut) {
		checkOut.setStatus(Status.INATIVO);
		checkOutService.alterar(checkOut);
		return "redirect:/checkOuts/remocaosucesso";
	}
	
	@GetMapping("/remocaosucesso")
	public String mostrarRemocaoSucesso(Model model) {
		NotificacaoAlertify notificao = new NotificacaoAlertify("Remoção de hóspede efetuado com sucesso.",TipoNotificacaoAlertify.SUCESSO);
		model.addAttribute("notificacao",notificao);
		return "checkOut/pesquisar";
	}
}
