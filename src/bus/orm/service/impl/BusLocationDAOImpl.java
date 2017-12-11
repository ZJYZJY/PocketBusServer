package bus.orm.service.impl;

import bus.orm.MySessionFactory;
import bus.orm.entity.BusLocation;
import bus.orm.entity.User;
import bus.orm.service.BusLocationDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BusLocationDAOImpl implements BusLocationDAO {

    @Override
    public boolean uploadLocation(BusLocation busLocation) {
        Transaction tx = null;
        String hql = "";
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from BusLocation where busId = ? and userId = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, busLocation.getBusId());
            query.setParameter(1, busLocation.getUserId());
            List list = query.list();
            if (list.size() > 0) {
                BusLocation temp = (BusLocation) list.get(0);
                busLocation.setUid(temp.getUid());
                session.merge(busLocation);
                tx.commit();
                return true;
            } else {
                session.save(busLocation);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    @Override
    public boolean clearLocation(BusLocation busLocation) {
        Transaction tx = null;
        String hql = "";
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from BusLocation where busId = ? and userId = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, busLocation.getBusId());
            query.setParameter(1, busLocation.getUserId());
            List list = query.list();
            if (list.size() > 0) {
                BusLocation temp = (BusLocation) list.get(0);
                session.delete(temp);
                tx.commit();
                return true;
            } else {
                tx.commit();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }

    @Override
    public BusLocation downLocation(String busId) {
        Transaction tx = null;
        String hql = "";
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from BusLocation where busId = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, busId);
            List list = query.list();
            tx.commit();
            if (list.size() > 0) {
                return  (BusLocation) list.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }
}
