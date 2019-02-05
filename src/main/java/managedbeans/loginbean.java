package managedbeans;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import dao.ClienteDAO;
import modelo.Cliente;

@ManagedBean
@SessionScoped
public class loginbean {
	private Cliente cliente=new Cliente();
	private ClienteDAO clidao=new ClienteDAO();
	private String email;
	private String senha;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setsenha(String senha) {
		this.senha = senha;
	}
	
	/*public void Sessao() {
		this.cliente=clidao.validalogin(cliente.getEmail(),cliente.getSenha());
	}*/

	public String validalogin() {
		this.cliente=clidao.validalogin(cliente.getEmail(),cliente.getSenha());
		if(cliente==null)
		{
			FacesContext.getCurrentInstance().addMessage("x:erro", new FacesMessage("Email ou senha errado!"));
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginbean", new loginbean());
			return "index";
		}
		else {
			return "principal";
		}
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loginbean", new loginbean());
		return "index";
	}
}
