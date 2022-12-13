package web.sistemagerenciamentohotel.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import web.sistemagerenciamentohotel.model.CheckIn;
import web.sistemagerenciamentohotel.model.Hospede;
import web.sistemagerenciamentohotel.model.Quarto;
import web.sistemagerenciamentohotel.model.Status;
import web.sistemagerenciamentohotel.model.StatusQuarto;
import web.sistemagerenciamentohotel.model.filter.ContaFilter;
import web.sistemagerenciamentohotel.model.filter.HospedeFilter;
import web.sistemagerenciamentohotel.model.filter.QuartoFilter;
import web.sistemagerenciamentohotel.pagination.PageWrapper;
import web.sistemagerenciamentohotel.repository.ConsumoRepository;
import web.sistemagerenciamentohotel.repository.ContaRepository;
import web.sistemagerenciamentohotel.repository.HospedeRepository;
import web.sistemagerenciamentohotel.repository.QuartoRepository;
import web.sistemagerenciamentohotel.service.CheckInService;
import web.sistemagerenciamentohotel.service.QuartoService;

@Controller
@RequestMapping("/checkins")
public class CheckInController {
	
	@Autowired
	private QuartoRepository quartoRepository;

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private ConsumoRepository consumoRepository;

	@Autowired
	private CheckInService checkinService;
	
	@Autowired
	private QuartoService quartoService;
	
	@GetMapping("/abrircadastrar")
	public String abrirCadastrar(CheckIn checkin, HttpSession sessao) {
		sessao.setAttribute("checkin", checkin);
		return "checkin/cadastrar";
	}
	@GetMapping("/escolherdataCheckOut")
	public String dataCheckOut(LocalDate dataCheckOut,HttpSession sessao) {
		CheckIn checkin = (CheckIn) sessao.getAttribute("checkin");
		if (checkin == null) {
			checkin = new CheckIn();
		}
		
		checkin.setCheckOut(dataCheckOut);
		
		sessao.setAttribute("checkin", checkin);

		return "checkin/cadastrar";
	}
	
	@GetMapping("/abrirescolherhospede")
	public String abrirEscolherHospede(Model model) {
		List<Hospede> hospedes = hospedeRepository.findByStatus(Status.ATIVO);
		model.addAttribute("hospedes",hospedes);
		
		return "checkin/pesquisarhospede";
	}

	@GetMapping("/pesquisarhospede")
	public String pesquisar(HospedeFilter filtro, Model model,
			          @PageableDefault(size = 8) 
                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
                      Pageable pageable, HttpServletRequest request) {
		Page<Hospede> pagina = hospedeRepository.pesquisar(filtro, pageable);
		PageWrapper<Hospede> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "checkin/mostrarhospedes";
	}
	
	@PostMapping("/escolherhospede")
	public String escolherHospede(Hospede hospede, HttpSession sessao) {
		CheckIn checkin = (CheckIn) sessao.getAttribute("checkin");
		if (checkin == null) {
			checkin = new CheckIn();
		}

		checkin.setHospede(hospede);
		sessao.setAttribute("checkin", checkin);

		return "checkin/cadastrar";
	}
	
//	@GetMapping("/abrirescolherconta")
//	public String abrirEscolherConta(Model model) {
//		List<Conta> contas = contaRepository.findByStatus(Status.ATIVO);
//		model.addAttribute("contas",contas);
//		
//		List<Consumo> consumos = consumoRepository.findByStatus(Status.ATIVO);
//		model.addAttribute("consumos",consumos);
//		
//		return "checkin/pesquisarconta";
//	}
	
//	@GetMapping("/pesquisarconta")
//	public String pesquisar(ContaFilter filtro, Model model,
//			          @PageableDefault(size = 8) 
//                      @SortDefault(sort = "codigo", direction = Sort.Direction.ASC)
//                      Pageable pageable, HttpServletRequest request) {
//		Page<Conta> pagina = contaRepository.pesquisar(filtro, pageable);
//		PageWrapper<Conta> paginaWrapper = new PageWrapper<>(pagina, request);
//		model.addAttribute("pagina", paginaWrapper);
//		return "checkin/mostrarcontas";
//	}
	
//	@PostMapping("/escolherconta")
//	public String escolherConta(Conta conta, HttpSession sessao) {
//		CheckIn checkin = (CheckIn) sessao.getAttribute("checkin");
//		if (checkin == null) {
//			checkin = new CheckIn();
//		}
//
//		checkin.setConta(conta);
//		sessao.setAttribute("checkin", checkin);
//
//		return "checkin/cadastrar";
//	}
	
	@GetMapping("abrirescolherquarto")
	public String abrirEscolherLote(Model model) {
		List<Quarto> quartos = quartoRepository.findByStatusQuarto(StatusQuarto.DISPONIVEL);
		model.addAttribute("quartos", quartos);

		return "checkin/pesquisarquarto";
	}

	@GetMapping("/pesquisarquarto")
	public String pesquisar(QuartoFilter filtro, Model model,
			@PageableDefault(size = 8) @SortDefault(sort = "codigo", direction = Sort.Direction.ASC) Pageable pageable,
			HttpServletRequest request) {
		Page<Quarto> pagina = quartoRepository.pesquisar(filtro, pageable, true);

		PageWrapper<Quarto> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);

		return "checkin/mostrarquartos";
	}

	@PostMapping("/escolherquarto")
	public String escolherQuarto(Quarto quarto, HttpSession sessao) {
		CheckIn checkin = (CheckIn) sessao.getAttribute("checkin");
		if (checkin == null) {
			checkin = new CheckIn();
		}

		checkin.setQuarto(quarto);
		sessao.setAttribute("checkin", checkin);

		return "checkin/cadastrar";
	}
	

	@GetMapping("/cadastrar")
	public String cadastrarCheckIn(HttpSession sessao) {
		CheckIn checkin = (CheckIn) sessao.getAttribute("checkin");
		if (checkin == null) {
			checkin = new CheckIn();
		}
		
		checkin.setCheckIn(LocalDate.now());
		
		checkinService.salvar(checkin);
		checkin.getQuarto().setStatusQuarto(StatusQuarto.OCUPADO);
		quartoService.alterar(checkin.getQuarto());
		
		checkin.setQuarto(null);
		checkin.setHospede(null);
		sessao.setAttribute("checkin", null);
		
		return "redirect:/checkins/cadastrosucesso";
	}

	@GetMapping("/cadastrosucesso")
	public String mostrarCadastroSucesso(Model model) {
		model.addAttribute("mensagem", "Cadastro de checkin efetuado com sucesso.");
		return "mostrarmensagem";
	}
}
