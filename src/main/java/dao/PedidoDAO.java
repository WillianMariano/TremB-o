package dao;

import java.util.List;

import javax.persistence.EntityManager;

import modelo.Cliente;
import modelo.Pedido;

public class PedidoDAO {
	public void create(Pedido pedido) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(pedido);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(Pedido pedido) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(pedido);
		em.getTransaction().commit();
		em.close();
	}
	
	public Pedido readId(long id) {
		EntityManager em=JPAUtil.getEntityManager();
		Pedido consultaPedido=em.find(Pedido.class, id);
		em.close();
		return consultaPedido;
	}
	
	public void delete(Pedido pedido) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Object c=em.merge(pedido);
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Pedido> buscapedidocliente(Cliente cliente) {
		EntityManager em=JPAUtil.getEntityManager();
	    String sql = "SELECT Pedido FROM Pedido Pedido where Pedido.cliente=:cliente";
	    return em.createQuery(sql, Pedido.class).setParameter("cliente", cliente).getResultList();
	}
}
