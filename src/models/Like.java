package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name="likes")

@NamedQueries({
    @NamedQuery(
            name = "getLikeData",
            query = "SELECT l FROM Like AS l WHERE l.likedEmployee = :likedEmployee AND l.likeReport = :likeReport ORDER BY l.id DESC"
             )
})

@Entity
public class Like {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne//いいねした人のデータ
    @JoinColumn(name = "likedEmployee_id", nullable= false)
    private Employee likedEmployee;

    @ManyToOne//いいねした対象のデータ
    @JoinColumn(name = "likeReport_id", nullable = false)
    private Report likeReport;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getLikedEmployee() {
        return likedEmployee;
    }

    public void setLikedEmployee(Employee likedEmployee) {
        this.likedEmployee = likedEmployee;
    }

    public Report getLikeReport() {
        return likeReport;
    }

    public void setLikeReport(Report likeReport) {
        this.likeReport = likeReport;
    }


}





