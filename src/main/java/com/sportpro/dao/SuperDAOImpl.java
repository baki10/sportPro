package com.sportpro.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ilmir
 * @param <T> Entity
 * @param <P>
 */

public class SuperDAOImpl<T,P> implements SuperDAO<T,P> {

    @PersistenceContext
    protected EntityManager em;
    protected final Class<T> entityClass;

    public SuperDAOImpl(Class clazz) {
        this.entityClass = clazz;
    }

    @Override
    public T create(T t) {
        em.persist(t);
        return t;
    }

    @Override
    public T get(P id) {
        return this.em.find(entityClass, id);
    }

    @Override
    public T update(T t) {
        T merge = this.em.merge(t);
        return merge;
    }

    @Override
    public void delete(T t) {
        T newT = this.em.merge(t);
        this.em.remove(newT);
    }

    @Override
    public List<T> getAll() {
        TypedQuery<T> namedQuery = em.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass);
        return namedQuery.getResultList();
    }

}
