package siteIcarros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;


public class listaExercicios {
	
	WebDriver driver;
	String    url;
	int       qtd;
	
	@Before
	public void iniciar() {
		url = "https://www.icarros.com.br/";
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\aline\\eclipse-workspace\\Exercicios\\drivers\\chrome\\83\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
	}
	
	@After
	public void finalizar() {
		driver.close();
	}
	
	public static boolean numero(String elemento) {
		return elemento.matches("[0-9]+");
	}
	
	//Exercicio 2 -1
	@Test
	public void consultarCarrosUsados() {
		String   retorno;
		String[] elementos;
		
		driver.get(url);	
		driver.findElement(By.id("anunciosNovos")).click();
		driver.findElement(By.xpath("//button[contains(@type, 'button') and contains(@title, 'Marca')]")).click();
		driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[2]/div[1]/div/div/div/div/div/div/input")).sendKeys("Chevrolet", Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[2]/div[2]/div/div/div/div/div/div/input")).sendKeys("Astra Sedan", Keys.ENTER);
		driver.findElement(By.xpath("//button[contains(@type, \"button\") and contains(@title, \"Ano min.\")]")).click();
		driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[3]/div[1]/div/div/div/div/div/div/input")).sendKeys("De 2010", Keys.ENTER);
		driver.findElement(By.xpath("//button[contains(@type, \"button\") and contains(@title, \"Ano máx.\")]")).click();
		driver.findElement(By.xpath("//*[@id=\"buscaForm\"]/div[3]/div[2]/div/div/div/div/div/div/input")).sendKeys("Até 2018", Keys.ENTER);
		driver.findElement(By.xpath("//div[@class='form__row form__row--new']//div[@class=\"col-xs-4 p-top-xs\"]//button")).click();
		
		retorno = driver.findElement(By.className("titulo")).getText();
		elementos = retorno.split("\\s|(|)");
		
		for(String elemento : elementos){
            boolean teste = numero(elemento);
            //System.out.println(elemento + " é um número? R:" + teste);
            if(numero(elemento)) {
            	qtd = Integer.parseInt(elemento);
            	System.out.println("A lista esta retornando "+qtd+" carros");
            	break;
            }
        }		
	}
	
	//Exercicio 2 - 2 
	@Test
	public void validarAno() {
		consultarCarrosUsados();
		System.out.println("\n\nPrimeiro carro\n");
		System.out.println(driver.findElement(By.xpath("//li[@id=\"ac24260037\"]//a[@class=\"clearfix\"]//h2[@class=\"esquerda titulo_anuncio\"]")).getText());
		System.out.println(driver.findElement(By.xpath("//li[@id=\"ac24260037\"]//a[@class=\"clearfix\"]//h3[@class=\"direita preco_anuncio\"]")).getText());
		System.out.println("\n\nSegundo carro\n");
		System.out.println(driver.findElement(By.xpath("//li[@id=\"ac29842743\"]//a[@class=\"clearfix\"]//h2[@class=\"esquerda titulo_anuncio\"]")).getText());
		System.out.println(driver.findElement(By.xpath("//li[@id=\"ac29842743\"]//a[@class=\"clearfix\"]//h3[@class=\"direita preco_anuncio\"]")).getText());
	}
	
	//Exercicio 2 - 3
	@Test
	public void criarArquivo() throws IOException {
		
		consultarCarrosUsados();
		
		FileWriter  arq = new FileWriter("d://arquivo.txt");
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.printf("+--Lista de pesquisa--+%n");
		gravarArq.printf("%n%nPrimeiro Carro%n%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac24260037\"]//a[@class=\"clearfix\"]//h2[@class=\"esquerda titulo_anuncio\"]")).getText());
		gravarArq.printf("%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac24260037\"]//a[@class=\"clearfix\"]//h3[@class=\"direita preco_anuncio\"]")).getText());
		gravarArq.printf("%n%nSegundo Carro%n%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac29842743\"]//a[@class=\"clearfix\"]//h2[@class=\"esquerda titulo_anuncio\"]")).getText());
		gravarArq.printf("%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac29842743\"]//a[@class=\"clearfix\"]//h3[@class=\"direita preco_anuncio\"]")).getText());
		gravarArq.printf("%n%nTerceiro Carro%n%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac30123890\"]//a[@class=\"clearfix\"]//h2[@class=\"esquerda titulo_anuncio\"]")).getText());
		gravarArq.printf("%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac30123890\"]//a[@class=\"clearfix\"]//h3[@class=\"direita preco_anuncio\"]")).getText());
		gravarArq.printf("%n%nQuarto Carro%n%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac29756288\"]//a[@class=\"clearfix\"]//h2[@class=\"esquerda titulo_anuncio\"]")).getText());
		gravarArq.printf("%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac29756288\"]//a[@class=\"clearfix\"]//h3[@class=\"direita preco_anuncio\"]")).getText());
		gravarArq.printf("%n%nQuinto Carro%n%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac30010170\"]//a[@class=\"clearfix\"]//h2[@class=\"esquerda titulo_anuncio\"]")).getText());
		gravarArq.printf("%n");
		gravarArq.printf(driver.findElement(By.xpath("//li[@id=\"ac30010170\"]//a[@class=\"clearfix\"]//h3[@class=\"direita preco_anuncio\"]")).getText());
		gravarArq.printf("%n%n");
		gravarArq.printf("+-------------+%n");
		arq.close();
	}
	
	//Exercicio 2 - 4
	@Test
	public void lerArquivo() throws IOException {
		FileReader     arq    = new FileReader("d://arquivo.txt");
		BufferedReader lerArq = new BufferedReader(arq);
		String         linha  = lerArq.readLine();
		
		while(linha!=null) {
			System.out.printf("%s\n", linha);
			linha = lerArq.readLine();
		}
		arq.close();
	}
		
		
}
		

