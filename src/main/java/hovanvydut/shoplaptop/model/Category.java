package hovanvydut.shoplaptop.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hovanvydut
 * Created on 5/31/21
 */

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 128, unique = true, nullable = false)
    private String name;

    @Column(name = "slug", length = 64, unique = true, nullable = false)
    private String slug;

    @Column(name = "image", length = 128, nullable = false)
    private String image;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<Category> children = new HashSet<>();

    public Category(int id) {
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
        this.slug = name;
        this.image = "default.png";
    }

    public Category(String name, Category parent) {
        this(name);
        this.parent = parent;
    }

    @Transient
    public String getImagePath() {
        if (this.id == null) return "/img/image-thumbnail.png";

        return "/img/category/" + this.id + "/" + this.image;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", image='" + image + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
