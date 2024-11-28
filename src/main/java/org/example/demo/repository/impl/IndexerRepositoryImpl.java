package org.example.demo.repository.impl;

import org.example.demo.entity.Indexer;
import org.example.demo.entity.Player;
import org.example.demo.repository.IndexerRepository;
import org.example.demo.util.HIbernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class IndexerRepositoryImpl implements IndexerRepository {
    @Override
    public List<Indexer> findAll() {
        try (Session session = HIbernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from Indexer ", Indexer.class).list();
        }
    }

    @Override
    public void save(Indexer indexer) {
        Transaction transaction = null;
        try(Session session = HIbernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(indexer);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Indexer findById(int id) {
        try (Session session = HIbernateUtil.getSessionFactory().openSession()){
            return session.get(Indexer.class, id);
        }
    }

    @Override
    public void update(Indexer indexer) {
        Transaction transaction = null;
        try (Session session = HIbernateUtil.getSessionFactory().openSession()){
            transaction = session.getTransaction();
            session.update(indexer);
            transaction.commit();
        }catch (HibernateException e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session = HIbernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Player player = session.get(Player.class, id);
            if (player != null) {
                session.delete(player);
                transaction.commit();
            }
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
