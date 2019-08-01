import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.Servidor;
import br.com.alura.loja.modelo.Carrinho;

public class ClienteTest {

	private HttpServer server;
	
	@Before
	public void startaServidor() {
		this.server = Servidor.inicializaServidor();
	}
	
	@After
	public void mataServidor() {
		server.stop();
	}
	
    @Test
    public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
    	
    	Client client = ClientBuilder.newClient();
    	
    	// pega a URI base do servidor para fazer as requisições
    	WebTarget target = client.target("http://localhost:8000");
    	
    	// faz uma requisição get para o path, parseando para String
    	String conteudo = target.path("/carrinhos").request().get(String.class);
    	
    	// checa se o conteudo retornado é o que se estava esperando e a conexão com o servidor está ok
    	Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
    	Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
    
    }
}
