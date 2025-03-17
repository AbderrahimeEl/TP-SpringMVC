package net.elm.tpspringmvc.web;

import net.elm.tpspringmvc.entities.Patient;
import net.elm.tpspringmvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model,@RequestParam(name = "page",defaultValue = "0") int page,
                                    @RequestParam(name = "size",defaultValue = "3") int size,
                                    @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Patient> pagePatient = patientRepository.findByFirstnameContains(keyword,PageRequest.of(page,size));
        model.addAttribute("patientList",pagePatient.getContent());
        model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patient";
    }
    @GetMapping("/deletePatient")
    public String delete(@RequestParam(name = "id")Long idPatient){
        patientRepository.deleteById(idPatient);
        return "redirect:/index";
    }
}
