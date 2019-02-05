package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import modelo.Cliente;

public class ClienteDAO {
	public void create(Cliente cliente) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(cliente);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(Cliente cliente) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(cliente);
		em.getTransaction().commit();
		em.close();
	}
	
	public Cliente readId(int id) {
		EntityManager em=JPAUtil.getEntityManager();
		Cliente consultaCliente=em.find(Cliente.class, id);
		em.close();
		return consultaCliente;
	}
	
	public List<Cliente> readAll(){
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("from Cliente");
		List<Cliente> clientes = query.getResultList();
		return clientes;
	}
	
	public void delete(Cliente cliente) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Object c=em.merge(cliente);
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
	
	public Cliente buscaclientenome(String nome) {
		EntityManager em=JPAUtil.getEntityManager();
	    String sql = "SELECT Cliente FROM Cliente Cliente where Cliente.nome=:nome";
	    Cliente cli= em.createQuery(sql, Cliente.class).setParameter("nome", nome).getSingleResult();
	    return cli;
	}
	
	public Cliente buscaclienteid(int id) {
		EntityManager em=JPAUtil.getEntityManager();
	    String sql = "SELECT Cliente FROM Cliente Cliente where Cliente.id=:id";
	    Cliente cli=em.createQuery(sql, Cliente.class).setParameter("id", id).getSingleResult();
	    return cli;
	}
	
	public Cliente validalogin(String email,String senha) {
		EntityManager em=JPAUtil.getEntityManager();
		Cliente cl=null;
		String sql = "SELECT Cliente FROM Cliente Cliente where Cliente.email=:email AND Cliente.senha=:senha";
		try {
	    	cl= em.createQuery(sql, Cliente.class).setParameter("email", email).setParameter("senha", senha).getSingleResult();
	    	return cl;
	    } catch(NoResultException e) {
	    	System.out.println("Email ou Senha não conferem!");
	    }
	    return cl;
	}
	
	public Cliente verificaemail(String email) {
		EntityManager em=JPAUtil.getEntityManager();
		Cliente cl=null;
		String sql = "SELECT Cliente FROM Cliente Cliente where Cliente.email=:email";
		try {
	    	cl= em.createQuery(sql, Cliente.class).setParameter("email", email).getSingleResult();
	    	return cl;
	    } catch(NoResultException e) {
	    	System.out.println("Email não cadastrado ainda");
	    }
	    return cl;
	}
}
