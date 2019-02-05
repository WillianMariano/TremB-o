package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import modelo.ItemCardapio;

public class ItemCardapioDAO {
	public void create(ItemCardapio itemcardapio) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(itemcardapio);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(ItemCardapio itemcardapio) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(itemcardapio);
		em.getTransaction().commit();
		em.close();
	}
	
	public ItemCardapio readId(long id) {
		EntityManager em=JPAUtil.getEntityManager();
		ItemCardapio consultaItemCardapio=em.find(ItemCardapio.class, id);
		em.close();
		return consultaItemCardapio;
	}
	
	public List<ItemCardapio> readAll(){
		EntityManager em = JPAUtil.getEntityManager();
		Query query = em.createQuery("from ItemCardapio");
		List<ItemCardapio> itens = query.getResultList();
		return itens;
	}
	
	public void delete(ItemCardapio itemcardapio) {
		EntityManager em=JPAUtil.getEntityManager();
		em.getTransaction().begin();
		Object c=em.merge(itemcardapio);
		em.remove(c);
		em.getTransaction().commit();
		em.close();
	}
	
	public ItemCardapio buscaitemcardapiodescricao(String descricao) {
		EntityManager em=JPAUtil.getEntityManager();
	    String sql = "SELECT ItemCardapio FROM ItemCardapio ItemCardapio where ItemCardapio.descricao=:descricao";
	    return em.createQuery(sql, ItemCardapio.class).setParameter("descricao", descricao).getSingleResult();
	}
}
