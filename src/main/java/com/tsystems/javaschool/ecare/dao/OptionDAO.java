package com.tsystems.javaschool.ecare.dao;


import com.tsystems.javaschool.ecare.entities.Option;
import com.tsystems.javaschool.ecare.util.EntityManagerFactoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class OptionDAO implements IAbstractDAO<Option>
{
    private static OptionDAO instance;
    private EntityManager em = EntityManagerFactoryUtil.getEm();

    private OptionDAO() {
    }

    public static OptionDAO getInstance()
    {
        if (instance == null) {
            instance = new OptionDAO();
        }
        return instance;
    }

    @Override
    public Option saveOrUpdate(Option op) {
        return em.merge(op);
    }

    @Override
    public Option load(long id) {
        return em.find(Option.class, id);
    }

    public Option findOptionByTitleAndTariffId(String title, long id) {
        Query query = em.createNamedQuery("Option.findOptionByTitleAndTariffId", Option.class);
        query.setParameter("title", title);
        query.setParameter("id", id);
        return (Option) query.getSingleResult();
    }

    @Override
    public void delete(Option op) {
        em.remove(op);
    }

    @Override
    public List<Option> getAll() {
        return em.createNamedQuery("Option.getAllOptions", Option.class).getResultList();
    }

    @Override
    public void deleteAll() {
        em.createNamedQuery("Option.deleteAllOptions").executeUpdate();
    }

    public List<Option> getAllOptionsForTariff(long id) {
        Query query = em.createNamedQuery("Option.getAllOptionsForTariff", Option.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public void deleteAllOptionsForTariff(long id) {
        Query query = em.createNamedQuery("Option.deleteAllOptionsForTariff");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Override
    public long getCount() {
        return ((Number)em.createNamedQuery("Option.size").getSingleResult()).longValue();
    }
}
