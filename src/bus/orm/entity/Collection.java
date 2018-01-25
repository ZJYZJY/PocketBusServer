package bus.orm.entity;

import javax.persistence.*;

@Entity
@Table(name = "collection", schema = "pocket_bus", catalog = "")
public class Collection {
    private int uid;
    private String busId;
    private int userId;

    @Id
    @Column(name = "uid")
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "bus_id")
    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Collection that = (Collection) o;

        if (uid != that.uid) return false;
        if (userId != that.userId) return false;
        if (busId != null ? !busId.equals(that.busId) : that.busId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid;
        result = 31 * result + (busId != null ? busId.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}
