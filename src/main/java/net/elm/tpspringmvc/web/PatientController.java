package net.elm.tpspringmvc.web;

import net.elm.tpspringmvc.entities.Patient;
import net.elm.tpspringmvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String delete(@RequestParam(name = "id")Long idPatient,String keyword,int page){
        patientRepository.deleteById(idPatient);
        return "redirect:/index?keyword="+keyword+"&page="+page;
    }

    @GetMapping("/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";
    }

    @PostMapping(path = "/savePatient")
    public  String savePatient(Model model,Patient patient){
        patientRepository.save(patient);
        return  "formPatients";
    }

}
