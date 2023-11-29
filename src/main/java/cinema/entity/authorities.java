package cinema.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class authorities {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private user user;

    public authorities() {
    }

    public authorities(String authority, user user) {
        this.authority = authority;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }
}