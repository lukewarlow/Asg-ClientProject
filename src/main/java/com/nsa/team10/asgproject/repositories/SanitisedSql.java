package com.nsa.team10.asgproject.repositories;

import com.google.common.base.CaseFormat;
import org.jboss.logging.Logger;

import java.lang.reflect.Field;

public class SanitisedSql
{
    private Logger logger = Logger.getLogger(SanitisedSql.class);

    private String sqlTemplate;
    private String orderByTableName;
    private String orderBy;
    private boolean orderByAscending;
    private Class objectClass;
    private String fallbackOrderBy;

    public SanitisedSql(String sqlTemplate, String orderBy, boolean orderByAscending, String orderByTableName, Class objectClass, String fallbackOrderBy)
    {
        this.sqlTemplate = sqlTemplate;
        this.orderByTableName = orderByTableName;
        this.orderBy = orderBy;
        this.orderByAscending = orderByAscending;
        this.objectClass = objectClass;
        this.fallbackOrderBy = fallbackOrderBy;
    }

    public String toString()
    {
        try
        {
            return String.format(sqlTemplate, getOrderBySqlFragment(objectClass.getDeclaredField(orderBy)));
        }
        catch (NoSuchFieldException e)
        {
            logger.info("Tried to order by: " + orderBy + " for object of type: " + objectClass.getName());
            try
            {
                return String.format(sqlTemplate, getOrderBySqlFragment(objectClass.getDeclaredField(fallbackOrderBy)));
            }
            catch (NoSuchFieldException ex)
            {

                throw new UnsupportedOperationException("The fallback order by " + fallbackOrderBy + " is not valid");
            }
        }
    }

    private String getOrderBySqlFragment(Field field)
    {
        var snakeCaseFieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
        var direction = orderByAscending ? " ASC" : " DESC";
        if (orderByTableName.isEmpty())
            return snakeCaseFieldName + direction;
        else return orderByTableName + "." + snakeCaseFieldName + direction;
    }
}

