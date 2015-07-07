package com.tsystems.javaschool.ecare.entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Kolia on 01.07.2015.
 */
@Entity
@Table(name = "contracts", schema = "", catalog = "ecare")
@NamedQueries(
        {
                @NamedQuery (name = "Contract.getAllContracts", query = "SELECT c FROM Contract c"),
                @NamedQuery (name = "Contract.findContractByNumber", query = "SELECT c FROM Contract c WHERE c.phoneNumber = :number"),
                @NamedQuery (name = "Contract.getAllContractsForClient", query = "SELECT c FROM Contract c WHERE c.user.id = :id"),
                @NamedQuery (name = "Contract.deleteAllContracts", query="DELETE FROM Contract"),
                @NamedQuery (name = "Contract.deleteAllContractsForClient", query = "DELETE FROM Contract WHERE user.id = ?1"),
                @NamedQuery (name = "Contract.size", query="SELECT count(c) FROM Contract c")
        })
public class Contract
{
    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contractId;

    @ManyToOne
    @Column(name = "user_id")
    private User user;

    @ManyToOne
    @Column(name = "tariff_id")
    private Tariff tariff;

    @Column(name = "phone_number")
    private int phoneNumber;

    @ManyToMany
    @JoinTable(name="contract_locking",
            joinColumns=@JoinColumn(name="contract_id"),
            inverseJoinColumns=@JoinColumn(name="locker_id"))
    private Collection<User> lockedByUsers;

    @ManyToMany
    @JoinTable(name="selected_options",
            joinColumns=@JoinColumn(name="contract_id"),
            inverseJoinColumns=@JoinColumn(name="option_id"))
    private Collection<Option> selectedOptions;

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
    }


    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }


    public Tariff getTariff()
    {
        return tariff;
    }

    public void setTariff(Tariff tariff)
    {
        this.tariff = tariff;
    }

    public int getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public Collection<User> getLockedByUsers()
    {
        return lockedByUsers;
    }

    public void setLockedByUsers(Collection<User> lockedByUsers)
    {
        this.lockedByUsers = lockedByUsers;
    }

    public Collection<Option> getSelectedOptions()
    {
        return selectedOptions;
    }

    public void setSelectedOptions(Collection<Option> selectedOptions)
    {
        this.selectedOptions = selectedOptions;
    }

    public Contract(){}

    public Contract(int phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract that = (Contract) o;

        if (contractId != that.contractId) return false;
        if (tariff != that.tariff) return false;
        if (user != that.user) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return contractId;
    }


}
