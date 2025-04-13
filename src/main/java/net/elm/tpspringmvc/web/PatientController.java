package net.elm.tpspringmvc.web;

import jakarta.validation.Valid;
import net.elm.tpspringmvc.entities.Patient;
import net.elm.tpspringmvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/user/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "3") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Patient> pagePatient = patientRepository.findByFirstnameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patientList", pagePatient.getContent());
        model.addAttribute("pages", new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patient";
    }

    @GetMapping("/admin/deletePatient")
    public String delete(@RequestParam(name = "id") Long idPatient, String keyword, int page) {
        patientRepository.deleteById(idPatient);
        return "redirect:/user/index?keyword=" + keyword + "&page=" + page;
    }

    @GetMapping("/admin/formPatients")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }

    @GetMapping("/admin/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page) {
        Patient p = patientRepository.findById(id).orElse(null);
        if (p == null) throw new RuntimeException("patient does not exist ");
        model.addAttribute("patient", p);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "editPatient";
    }

    @PostMapping(path = "/admin/savePatient")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword) {
        if (bindingResult.hasErrors())
            return "formPatients";
        patientRepository.save(patient);
        return "redirect:/user/index?ke yword=" + keyword + "&page=" + page;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }

}
