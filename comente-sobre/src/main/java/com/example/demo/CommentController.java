package com.example.demo;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comente-sobre")
    public String pesquisar(Model model) {
        model.addAttribute("topico", "");
        return "index";
    }

    @PostMapping("/comente-sobre")
    public String redirecionar(@RequestParam("topico") String topico) {
        String topicoCodificado = URLEncoder.encode(topico, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "redirect:/comente-sobre/" + topicoCodificado;
    }

    private String removeAccents(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    @GetMapping("/comente-sobre/{topico}")
    public String exibirComentarios(@PathVariable("topico") String topico, Model model) {
        List<Comentario> listaComentarios = commentRepository.findByTopico(topico);
        model.addAttribute("topico", topico);
        model.addAttribute("comentarios", listaComentarios);
        model.addAttribute("novoComentario", new Comentario());
        return "comentarios";
    }

    @PostMapping("/comente-sobre/{topico}")
    public String adicionarComentario(@PathVariable("topico") String topico,
                                      @ModelAttribute("novoComentario") Comentario novoComentario) {
        String topicoCodificado = URLEncoder.encode(topico, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");
        novoComentario.setTopico(topico);
        commentRepository.save(novoComentario);
        return "redirect:/comente-sobre/" + topicoCodificado;
    }
}

