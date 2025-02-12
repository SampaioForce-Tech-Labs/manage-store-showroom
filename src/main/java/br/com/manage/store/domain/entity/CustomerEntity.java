package br.com.manage.store.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_customer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "date_birth")
    private Date dateBirth;

    @Column(name = "marital_status ")
    private String maritalStatus;

    @Column(name = "enterprise")
    private String enterprise;

    @Column(name = "business_phone")
    private String businessPhone;

    @Column(name = "length_service")
    private String lengthService;

    @Column(name = "business_zip_code")
    private String businessZipCode;

    @Column(name = "business_address")
    private String businessAddress;

    @Column(name = "business_city")
    private String businessCity;

    @Column(name = "business_state")
    private String businessState;

    @Column(name = "business_position")
    private String businessPosition;

    @Column(name = "bank")
    private String bank;

    @Column(name = "agency")
    private String agency;

    @Column(name = "father")
    private String father;

    @Column(name = "mother")
    private String mother;

    @Embedded
    private AddressData addressData;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "customerEntity", cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<SalesEntity> salesEntities = new ArrayList<>();

    @OneToMany(mappedBy = "customerEntity", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ReferencePersonEntity> referenceEntityList = new ArrayList<>();
}
