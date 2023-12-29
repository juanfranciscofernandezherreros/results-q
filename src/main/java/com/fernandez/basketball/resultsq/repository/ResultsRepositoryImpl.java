package com.fernandez.basketball.resultsq.repository;

import com.fernandez.basketball.resultsq.dao.ResultsDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ResultsRepositoryImpl {
    public final MongoTemplate mongoTemplate;
    public ResultsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Page<ResultsDAO> findAllByDynamicCriteriaWithPagination(Map<String, String> dynamicCriteria, int page, int size) {
        Query query = buildQuery(dynamicCriteria);
        long count = mongoTemplate.count(query, ResultsDAO.class);
        query.with(PageRequest.of(page, size));
        List<ResultsDAO> resultList = mongoTemplate.find(query, ResultsDAO.class);
        return new PageImpl<>(resultList, PageRequest.of(page, size), count);
    }

    public Query buildQuery(Map<String, String> dynamicCriteria) {
        Query query = new Query();
        List<Criteria> dateCriterias = new ArrayList<>();
        for (Map.Entry<String, String> entry : dynamicCriteria.entrySet()) {
            String fieldName = entry.getKey();
            String value = entry.getValue();
            Criteria criteria;
            if (isFechaInicioField(fieldName)) {
                dateCriterias.add(buildDateCriteria("matchData.transformedDate", value, "gte"));
            } else if (isFechaFinField(fieldName)) {
                dateCriterias.add(buildDateCriteria("matchData.transformedDate", value, "lte"));
            } else if (isFechaSpecific(fieldName)) {
                dateCriterias.add(buildDateCriteria("matchData.transformedDate", value, "eqt"));
            } else {
                criteria = Criteria.where(fieldName).is(value);
                query.addCriteria(criteria);
            }
        }
        if (!dateCriterias.isEmpty()) {
            Criteria dateCriteria = new Criteria().andOperator(dateCriterias.toArray(new Criteria[0]));
            query.addCriteria(dateCriteria);
        }
        return query;
    }

    public boolean isFechaInicioField(String fieldName) {
        return fieldName.contains("fechaInicio");
    }

    public boolean isFechaFinField(String fieldName) {
        return fieldName.contains("fechaFin");
    }

    public boolean isFechaSpecific(String fieldName) {
        return fieldName.contains("fechaEspecifica");
    }

    public Criteria buildDateCriteria(String fieldName, String value, String operator) {
        try {
            Date parsedDate = parseDateString(value);

            if ("gte".equals(operator)) {
                return Criteria.where(fieldName).gte(parsedDate);
            } else if ("lte".equals(operator)) {
                // Ajusta la fecha para incluir el final del día
                Date endOfDay = new Date(parsedDate.getTime() + 86399999);
                return Criteria.where(fieldName).lte(endOfDay);
            } else {
                // Default to equality check
                return Criteria.where(fieldName).is(parsedDate);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Error al parsear la fecha: " + e.getMessage());
        }
    }

    public Date parseDateString(String value) throws ParseException {
        SimpleDateFormat sdfInput = new SimpleDateFormat("dd.MM.yyyy");
        return sdfInput.parse(value);
    }
}
