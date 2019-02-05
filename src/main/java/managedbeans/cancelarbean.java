package managedbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;

import dao.PedidoDAO;
import modelo.Cliente;
import modelo.Pedido;

@ManagedBean
public class cancelarbean {
	
	private String nome;
	private Pedido pedido=new Pedido();

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Pedido> getpedidos(Cliente cliente){
		List<Pedido> pedidos=new PedidoDAO().buscapedidocliente(cliente);
		return pedidos;
	}
	
	public void cancelarpedido(Pedido pedido) {
		new PedidoDAO().delete(pedido);
	}
}
