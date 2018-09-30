package ru.yantsen.telegrambots.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Deliveries", schema = "Bot")
public class Delivery {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "delivery_date")
    private Date deliveryDate;

    public Integer getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public Order getOrder() {
        return order;
    }

    public Date getDate() {
        return deliveryDate;
    }



    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setDate(Date date) {
        this.deliveryDate = date;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "id=" + id +
                ", driverId=" + driver.getId() +
                ", orderId=" + order.getId() +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}


