package com.example.demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @Mock
    private Model model; // Mockando a dependência Model

    @InjectMocks
    private CommentController commentController; // O controller sendo testado

    private Map<String, List<Comentario>> comentarios; // Um mapa para armazenar comentários por tópico

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializando os mocks
        commentController = new CommentController(); // Criando uma instância do controller
        comentarios = commentController.getComentarios(); // Obtendo o mapa de comentários usando o método getComentarios()
    }

    @Test
    void testPesquisar() {
        String expectedView = "index";
        String actualView = commentController.pesquisar(model); // Invocando o método pesquisar() do controller
        assertEquals(expectedView, actualView);
        verify(model).addAttribute("topico", ""); // Verificando se o atributo "topico" foi adicionado ao model
    }

    @Test
    void testRedirecionar() {
        String topico = "Programação em Java";
        String expectedView = "redirect:/comente-sobre/Programacao em Java";
        String actualView = commentController.redirecionar(topico); // Invocando o método redirecionar() do controller
        assertEquals(expectedView, actualView);
    }

    @Test
    void testExibirComentarios() {
        String topico = "JavaProgramming";
        List<Comentario> listaComentarios = new ArrayList<>();
        comentarios.put(topico, listaComentarios); // Adicionando uma lista de comentários ao mapa comentarios
        String expectedView = "comentarios";
        String actualView = commentController.exibirComentarios(topico, model); // Invocando o método exibirComentarios() do controller
        assertEquals(expectedView, actualView);
        verify(model).addAttribute("topico", topico); // Verificando se o atributo "topico" foi adicionado ao model
        verify(model).addAttribute("comentarios", listaComentarios); // Verificando se o atributo "comentarios" foi adicionado ao model
        verify(model).addAttribute(eq("novoComentario"), any(Comentario.class)); // Verificando se o atributo "novoComentario" foi adicionado ao model com qualquer instância da classe Comentario
    }

    @Test
    void testAdicionarComentario() {
        String topico = "JavaProgramming";
        Comentario novoComentario = new Comentario();
        List<Comentario> listaComentarios = new ArrayList<>();
        comentarios.put(topico, listaComentarios); // Adicionando uma lista de comentários ao mapa comentarios
        String expectedView = "redirect:/comente-sobre/JavaProgramming";
        String actualView = commentController.adicionarComentario(topico, novoComentario); // Invocando o método adicionarComentario() do controller
        assertEquals(expectedView, actualView);
        assertEquals(1, listaComentarios.size()); // Verificando se o tamanho de listaComentarios é 1
        assertEquals(novoComentario, listaComentarios.get(0)); // Verificando se o primeiro elemento de listaComentarios é igual a novoComentario
    }
}
