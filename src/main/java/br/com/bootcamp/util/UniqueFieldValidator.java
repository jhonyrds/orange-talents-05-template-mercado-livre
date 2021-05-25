package br.com.bootcamp.util;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueFieldValidator implements ConstraintValidator<UniqueField, Object> {

    @PersistenceContext
    private EntityManager entityManager;
    private String fieldName;
    private Class<?> entityName;

    @Override
    public void initialize(UniqueField value) {
        fieldName = value.fieldName();
        entityName = value.entityName();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Query query = entityManager.createQuery("SELECT 1 FROM " + entityName.getName() + " WHERE " + fieldName + "=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Foi encontrado mais de um atributo " + fieldName + " = " + value);

        return list.isEmpty();
    }
}
