package com.bunsen.nodeMcuDemo.dao;

import com.bunsen.nodeMcuDemo.HibernateUtil;
import com.bunsen.nodeMcuDemo.model.NodeMCU;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.logging.Logger;

public class NodeMCUDao {
    private static final Logger logger = Logger.getLogger(NodeMCUDao.class.getName());

    public void saveData(NodeMCU nodeMCU) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nodeMCU);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.severe("Error saving data: " + e.getMessage());
        }
    }
}
