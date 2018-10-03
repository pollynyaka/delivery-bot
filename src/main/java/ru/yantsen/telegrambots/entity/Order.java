package ru.yantsen.telegrambots.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(max = 20)
    @Column(name = "order_number")
    private String orderNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "client_name")
    private String clientName;

    @NotNull
    @Size(max = 1024)
    @Column(name = "client_address")
    private String address;

    @Column(name="order_status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public String getAddress() {
        return address;
    }

    public Status getStatus() { return status; }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStatus(Status status) { this.status = status; }

    public Collection<OrderItem> getItems() {
        return items;
    }

    public void setItems(Collection<OrderItem> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", clientName='" + clientName + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status + '\'' +
                ", itemsCount=" + items.size() +
                '}';
    }
}
