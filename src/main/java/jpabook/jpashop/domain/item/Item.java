package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {
    @Id
    @Column(name = "item_id", nullable = false)
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany
    @JoinTable(name = "category_item")
    private List<Category> categories = new ArrayList<>();

    // == business logic == //

    /**
     * stock 증가
     * @param quantity
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockException("Not enough stock");
        }
        this.stockQuantity -= quantity;
    }
}
