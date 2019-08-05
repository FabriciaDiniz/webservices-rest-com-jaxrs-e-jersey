package br.com.alura.loja.resources;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {

	@Path("{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String busca(@PathParam("id") long id) {
	    Carrinho carrinho = new CarrinhoDAO().busca(id);
	    return carrinho.toJson();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adiciona(String conteudo) {
		Carrinho carrinho = new Gson().fromJson(conteudo, Carrinho.class);
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build(); //retorna 201
		
	}
	
	@Path("{id}/produtos/{idProduto}")
	@DELETE
	public Response remove(@PathParam("id") long id, @PathParam("idProduto") long idProduto) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(idProduto);
		return Response.ok().build();
	}
	
	// teste PUT:
	/* curl -v -X PUT -H "Content-Type: application/xml" -d 
	 * "{"id": "3467","quantidade": "1"}" http://localhost:8080/carrinhos/1/produtos/3467/quantidade*/
	
	@Path("{id}/produtos/{produtoId}/quantidade")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId, String conteudo) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		Produto produto = new Gson().fromJson(conteudo, Produto.class);
		carrinho.trocaQuantidade(produto);
        return Response.ok().build();
    }
}
