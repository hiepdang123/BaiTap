package entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.Data;

@Data
@Entity(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "name")
    private String name;
 
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @OrderBy("title")
    private Set<Post> posts;
}
