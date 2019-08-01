import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Test;

public class ClienteTest {

    @Test
    public void testaQueAConexaoComOServidorFunciona() {
    	
    	Client client = ClientBuilder.newClient();
    	// pega a URI base do servidor para fazer as requisições
    	WebTarget target = client.target("http://www.mocky.io");
    	// faz uma requisição get para o path, parseando para String
    	String conteudo = target.path("/v2/52aaf5deee7ba8c70329fb7d").request().get(String.class);
    	
    	// checa se o conteudo retornado é o que se estava esperando e a conexão com o servidor está ok
    	Assert.assertTrue(conteudo.contains("<rua>Rua Vergueiro 3185"));
    }
}
