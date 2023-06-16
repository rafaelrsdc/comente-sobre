package com.example.demo;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ComenteSobreEndToEndTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Configurar o WebDriver adequado
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\gmass\\Downloads\\chromedriver_win32\\chromedriver.exe");

        // Inicializar o driver
        driver = new ChromeDriver();
    }

    @Test
    public void testComentario() {
        // Navegar para a página inicial
        driver.get("http://localhost:8080/comente-sobre");

        // Preencher o campo de pesquisa
        driver.findElement(By.id("topico")).sendKeys("Metodos ageis");

        // Enviar o formulário
        driver.findElement(By.cssSelector(".busca")).click();

        // Verificar se a página de comentários é exibida corretamente
        String titulo = driver.findElement(By.tagName("h1")).getText();
        Assert.assertEquals("TÓPICO: Metodos ageis", titulo);

        // Adicionar um novo comentário
        driver.findElement(By.id("email")).sendKeys("test@example.com");
        driver.findElement(By.id("comentario")).sendKeys("Este é um comentário de teste");
        driver.findElement(By.cssSelector(".botaoComent")).click();

        // Verificar se o comentário foi adicionado com sucesso
        String comentario = driver.findElement(By.cssSelector(".antigoComentario")).getText();
        Assert.assertTrue(comentario.contains("test@example.com"));
        Assert.assertTrue(comentario.contains("Este é um comentário de teste"));
    }

    @AfterEach
    public void tearDown() {
        // Fechar o navegador após cada teste
        driver.quit();
    }
}
