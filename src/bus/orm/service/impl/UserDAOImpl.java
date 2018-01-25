package bus.orm.service.impl;

import bus.orm.MySessionFactory;
import bus.orm.entity.Collection;
import bus.orm.entity.User;
import bus.orm.service.UserDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public User userLogin(User user) {
        Transaction tx = null;
        String hql = "";
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from User where username = ? and password = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, user.getUsername());
            query.setParameter(1, user.getPassword());
            List list = query.list();
            tx.commit();
            if (list.size() > 0) {
                User u = new User();
                u.setId(((User)list.get(0)).getId());
                u.setUsername(((User)list.get(0)).getUsername());
                u.setNickname(((User)list.get(0)).getNickname());
                return u;
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

    @Override
    public boolean userRegister(User user) {
        Transaction tx = null;
        String hql = "";
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from User where username = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, user.getUsername());
            List list = query.list();
            if (list.size() > 0) {
                tx.commit();
                return false;
            } else {
                session.save(user);
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
    public boolean starBusLine(Collection collection) {
        Transaction tx = null;
        String hql = "";
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from Collection where busId = ? and userId = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, collection.getBusId());
            query.setParameter(1, collection.getUserId());
            List list = query.list();
            if (list.size() > 0) {
                tx.commit();
                return false;
            } else {
                session.save(collection);
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
    public boolean unstarBusLine(Collection collection) {
        Transaction tx = null;
        String hql = "";
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from Collection where busId = ? and userId = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, collection.getBusId());
            query.setParameter(1, collection.getUserId());
            List list = query.list();
            if (list.size() > 0) {
                session.delete(list.get(0));
                tx.commit();
                return true;
            } else {
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
    public List<Collection> getStaredBusLines(int userId) {
        Transaction tx = null;
        String hql = "";
        List<Collection> collections = new ArrayList<>();
        try {
            Session session = MySessionFactory.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            hql = "from Collection where userId = ?";
            Query query = session.createQuery(hql);
            query.setParameter(0, userId);
            List list = query.list();
            if (list.size() > 0) {
                collections = list;
                tx.commit();
                return collections;
            } else {
                tx.commit();
                return collections;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return collections;
        } finally {
            if (tx != null) {
                tx = null;
            }
        }
    }
}
