package cz.czechitas.java2webapps.lekce10.controller;

import cz.czechitas.java2webapps.lekce10.entity.Student;
import cz.czechitas.java2webapps.lekce10.entity.Trida;
import cz.czechitas.java2webapps.lekce10.service.TridaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
public class TridaController {

    private final TridaService service;

    @Autowired
    public TridaController(TridaService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView seznamTrid(@PageableDefault(sort={"nazev"}) Pageable pageable) {
        return new ModelAndView("seznamtrid")
                .addObject("tridy", service.seznamTrid(pageable));
    }

    @GetMapping("/trida")
    public Object trida(short id) {
        Optional<Trida> trida = service.detailTridy(id);
        if (trida.isPresent()) {
            return new ModelAndView("detailtrida")
                    .addObject("trida", trida.get());
        }
        return null;
    }

    @GetMapping("/student")
    public Object student(int id) {
        Optional<Student> student = service.detailStudenta(id);
        if (student.isPresent()) {
            return new ModelAndView("detailstudent")
                    .addObject("student", student.get());
        }
        return null;
    }

}