package web.sistemagerenciamentohotel.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.sistemagerenciamentohotel.model.CheckIn;
import web.sistemagerenciamentohotel.model.CheckOut;
import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.StatusPago;
import web.sistemagerenciamentohotel.model.filter.CheckInFilter;
import web.sistemagerenciamentohotel.pagination.PageWrapper;
import web.sistemagerenciamentohotel.repository.CheckInRepository;
import web.sistemagerenciamentohotel.repository.CheckOutRepository;
import web.sistemagerenciamentohotel.repository.QuartoRepository;
import web.sistemagerenciamentohotel.service.CheckInService;
import web.sistemagerenciamentohotel.service.CheckOutService;
import web.sistemagerenciamentohotel.service.QuartoService;
import web.sistemagerenciamentohotel.model.StatusQuarto;

@Controller
@RequestMapping("/checkouts")
public class CheckOutController {
	private static final Logger logger = LoggerFactory.getLogger(CheckOutController.class);
	@Autowired
	private CheckOutRepository checkOutRepository;
	
	@Autowired
	private QuartoRepository quartoRepository;
	
	@Autowired
	private CheckOutService checkOutService;
	
	@Autowired
	private CheckInRepository checkInRepository;
	
	@Autowired
	private QuartoService quartoService;
	
	@Autowired
	private CheckInService checkinService;

	
	@GetMapping("/abrircadastrar")
	public String abrirCadastrar(CheckOut checkout, HttpSession sessao) {
		sessao.setAttribute("checkout", checkout);
		return "checkout/cadastrar";
	}
	
	@GetMapping("/cadastroValorTotal")
	public String dataCheckOut(BigDecimal valorTotal,HttpSession sessao) {
		CheckOut checkout = (CheckOut) sessao.getAttribute("checkout");
		if (checkout == null) {
			checkout = new CheckOut();
		}
		
		checkout.setValorTotal(valorTotal);
		
		sessao.setAttribute("checkout", checkout);

		return "checkout/cadastrar";
	}

	@GetMapping("/abrirescolhercheckin")
	public String abrirEscolherCheckIn(Model model) {
		List<CheckIn> checkins = checkInRepository.findByStatus(Status.ATIVO);
		model.addAttribute("checkins",checkins);
		
		List<Quarto> quartos = quartoRepository.findAll();
		model.addAttribute("quartos", quartos);
		
		return "checkout/pesquisarcheckin";
	}

	@GetMapping("/pesquisarcheckin")
	public String pesquisar(CheckInFilter filtro, Model model,
			          @PageableDefault(size = 8) 
                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
                      Pageable pageable, HttpServletRequest request) {
		Page<CheckIn> pagina = checkInRepository.pesquisar(filtro, pageable);
		PageWrapper<CheckIn> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "checkout/mostrarcheckins";
	}
	
	@PostMapping("/escolhercheckin")
	public String escolherCheckIn(CheckIn checkin, HttpSession sessao) {
		CheckOut checkout = (CheckOut) sessao.getAttribute("checkout");
		if (checkout == null) {
			checkout = new CheckOut();
		}

		checkout.setCheckIn(checkin);
		checkout.getCheckIn().setHospede(checkin.getHospede());
		checkout.getCheckIn().setQuarto(checkin.getQuarto());
		sessao.setAttribute("checkout", checkout);

		return "checkout/cadastrar";
	}

	@GetMapping("/cadastrar")
	public String cadastrarCheckOut(HttpSession sessao) {
		CheckOut checkout = (CheckOut) sessao.getAttribute("checkout");
		if (checkout == null) {
			checkout = new CheckOut();
		}
		checkout.getCheckIn().setStatus(Status.INATIVO);
		checkout.getCheckIn().setStatusPago(StatusPago.PAGO);
		checkinService.alterar(checkout.getCheckIn());
		checkout.getCheckIn().getQuarto().setStatusQuarto(StatusQuarto.DISPONIVEL);
		quartoService.alterar(checkout.getCheckIn().getQuarto());
		checkOutService.salvar(checkout);
		checkout.setValorTotal(null);
		checkout.setCheckIn(null);
		sessao.setAttribute("checkout", null);
		
		return "redirect:/checkouts/cadastrosucesso";
	}

	@GetMapping("/cadastrosucesso")
	public String mostrarCadastroSucesso(Model model) {
		model.addAttribute("mensagem", "Cadastro de checkout efetuado com sucesso.");
		return "mostrarmensagem";
	}
}
