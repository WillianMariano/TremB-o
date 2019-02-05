package managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ClienteDAO;
import modelo.Cliente;

@ManagedBean
@ViewScoped
public class cadastrobean {
	private Cliente cliente=new Cliente();
	private ClienteDAO clidao=new ClienteDAO();
	private String email;
	
	public Cliente getCadastro() {
		return cliente;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String salvar() {
		Cliente cli=clidao.verificaemail(cliente.getEmail());
		if(cli==null)
		{
			clidao.create(this.cliente);
			return "index";
		}
		else {
			FacesContext.getCurrentInstance().addMessage("y:erro", new FacesMessage("Ja existe cadastro com esse email!"));
			return " ";
		}
	}
}
