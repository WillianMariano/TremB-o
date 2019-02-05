package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import dao.ItemCardapioDAO;
import dao.PedidoDAO;
import modelo.Cliente;
import modelo.ItemCardapio;
import modelo.Pedido;

@ManagedBean
@ViewScoped
public class pedidobean {
	private int cardapioId;
	private Pedido pedido=new Pedido();
	private loginbean cliente=new loginbean();
	List<ItemCardapio> listacardapiopedidos=new ArrayList<>();
	private Cliente cli=new Cliente();
	
	public Cliente getCli() {
		return cli;
	}
	public void setCli(Cliente cli) {
		this.cli = cli;
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
		
	public List<ItemCardapio> getListacardapiopedidos() {
		return listacardapiopedidos;
	}
	public void setListacardapiopedidos(List<ItemCardapio> listacardapiopedidos) {
		this.listacardapiopedidos = listacardapiopedidos;
	}
	
	public int getCardapioId() {
		return cardapioId;
	}
	public void setCardapioId(int cardapioId) {
		this.cardapioId = cardapioId;
	}
	
	public loginbean getCliente() {
		return cliente;
	}
	public void setCliente(loginbean cliente) {
		this.cliente = cliente;
	}
	
	public List<ItemCardapio> getItemscardapio(){
		return new ItemCardapioDAO().readAll();
	}
	
	public void adicionaraopedido(ItemCardapio items) {
		//item.setItemcardapio(items);
		//this.pedido.setItempedidos(item);
		this.listacardapiopedidos.add(items);
	}
	
	public List<ItemCardapio> carrinhopedido(){
		return this.listacardapiopedidos;
	}
	
	public float Valor() {
		float soma=0;
		float aux;
		for (ItemCardapio item : this.listacardapiopedidos) {
			aux=(float)item.getValor();
			soma=soma+aux;
		}
		return soma;
	}
	
	public String finalizarpedido(Cliente cli) {
		//cli=cliente.getCliente();
		//int id=cli.getId();
		//cli=new ClienteDAO().readId(id);
		this.pedido.setCliente(cli);
		this.pedido.setItemcardapio(this.listacardapiopedidos);
		new PedidoDAO().create(this.pedido);
		return "mensagem";
	}
}
