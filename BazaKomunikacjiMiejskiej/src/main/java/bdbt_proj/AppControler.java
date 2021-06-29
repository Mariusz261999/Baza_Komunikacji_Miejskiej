package bdbt_proj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppControler {
//-----------------------------------------------------------------PRZYSTANKI--------------------------------------------------------------------------
	@Autowired
	private PrzystankiDAO dao;
	
	@RequestMapping("/")
	public String viewHomePage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getName();
		return "start_"+role;
	}
	@RequestMapping("/adminView")
	public String viewHomePageAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getName();
		return "start_"+role;
	}
	
	@RequestMapping("/userView")
	public String viewHomePageUser() {
		return "start_user";
	}
	
	@RequestMapping("/przystanki")
	public String showPrzystankiUser(Model model) {
		
		List<Przystanek> listPrzystanek = dao.list();
		model.addAttribute("listPrzystanek", listPrzystanek);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getName();
		return "przystankiView_"+role;
	}
	@RequestMapping("/przystankiAdmin")
	public String showPrzystankiAdmin(Model model) {
		
		List<Przystanek> listPrzystanek = dao.list();
		model.addAttribute("listPrzystanek", listPrzystanek);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getName();
		return "przystankiView_"+role;
	}
	
	@RequestMapping("/new")
	public String showNewForm(Model model) {
		
		Przystanek przystanek = new Przystanek();
		model.addAttribute("przystanek", przystanek);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getName();
		return "newPrzystanek_form_"+role;
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(@ModelAttribute("przystanek") Przystanek przystanek) {
		dao.save(przystanek);
		return "redirect:/przystankiAdmin";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditForm(@PathVariable(name="id") int id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getName();
	ModelAndView mav = new ModelAndView("editPrzystanek_form_"+role);
	Przystanek przystanek = dao.get(id);
	mav.addObject("przystanek", przystanek);
	return mav;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(@ModelAttribute("przystanek") Przystanek przystanek) {
		dao.update(przystanek);
		return "redirect:/przystankiAdmin";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable(name="id") int id) {
		dao.delete(id);
		return "redirect:/przystankiAdmin";
	}
//---------------------------------------------------------------------ULGI---------------------------------------------------------------------	
	@Autowired
	private UlgiDAO dao2;
		
		@RequestMapping("/ulgi")
		public String showUlgi(Model model) {
			List<Ulga> listUlga = dao2.list();
			model.addAttribute("listUlga", listUlga);
			return "ulgiView_user";
		}
		
		@RequestMapping("/ulgiAdmin")
		public String showUlgiAdmin(Model model) {
			List<Ulga> listUlga = dao2.list();
			model.addAttribute("listUlga", listUlga);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String role = authentication.getName();
			return "ulgiView_"+role;
		}
		
		@RequestMapping("/newUlga")
		public String showNewUlgaForm(Model model) {
			
			Ulga ulga = new Ulga();
			model.addAttribute("ulga", ulga);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String role = authentication.getName();
			return "newUlga_form_"+role;
		}
		@RequestMapping(value="/saveUlga", method=RequestMethod.POST)
		public String save(@ModelAttribute("ulga") Ulga ulga) {
			dao2.save(ulga);
			return "redirect:/ulgiAdmin";
		}
		@RequestMapping("/editUlga/{id}")
		public ModelAndView showEditUlgaForm(@PathVariable(name="id") int id) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String role = authentication.getName();
		ModelAndView mav = new ModelAndView("editUlga_form_"+role);
		Ulga ulga = dao2.get(id);
		mav.addObject("ulga", ulga);
		return mav;
		}
		
		@RequestMapping(value="/updateUlga", method = RequestMethod.POST)
		public String update(@ModelAttribute("ulga") Ulga ulga) {
			dao2.update(ulga);
			return "redirect:/ulgiAdmin";
		}
		
		@RequestMapping(value="/deleteUlga/{id}")
		public String delete1(@PathVariable(name="id") int id) {
			dao2.delete(id);
			return "redirect:/ulgiAdmin";
		}
//------------------------------------------------------------------KLIENCI-----------------------------------------------------------
		@Autowired
		private KlienciDAO dao3;
		
			@RequestMapping("/klienciAdmin")
			public String showkliecniView_admin(Model model) {
				List<Klient> listKlient = dao3.list();
				model.addAttribute("listKlient", listKlient);
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		        String role = authentication.getName();
				return "klienciView_"+role;
			}
			@RequestMapping("/newKlient")
			public String showNewKientForm(Model model) {
				
				Klient klient = new Klient();
				model.addAttribute("klient", klient);
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		        String role = authentication.getName();
				return "newKlient_form_"+role;
			}
			
			@RequestMapping(value="/saveKlient", method=RequestMethod.POST)
			public String save(@ModelAttribute("klient") Klient klient) {
				dao3.save(klient);
				return "redirect:/klienciAdmin";
			}
			@RequestMapping("/editKlient/{id}")
			public ModelAndView showEditKlientForm(@PathVariable(name="id") int id) {
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		        String role = authentication.getName();
			ModelAndView mav = new ModelAndView("editKlient_form_"+role);
			Klient klient = dao3.get(id);
			mav.addObject("klient", klient);
			return mav;
			}
			
			@RequestMapping(value="/updateKlient", method = RequestMethod.POST)
			public String update(@ModelAttribute("klient") Klient klient) {
				dao3.update(klient);
				return "redirect:/klienciAdmin";
			}
			
			@RequestMapping(value="/deleteKlient/{id}")
			public String delete2(@PathVariable(name="id") int id) {
				dao3.delete(id);
				return "redirect:/klienciAdmin";
			}
}
