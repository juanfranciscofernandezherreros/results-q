package com.fernandez.basketball.resultsq.repository;

import com.fernandez.basketball.resultsq.dto.Match;
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
public class CustomMatchRepositoryImpl {
    private final MongoTemplate mongoTemplate;
    public CustomMatchRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Match> findAllByDynamicCriteria(Map<String, String> dynamicCriteria) {
        Query query = buildQuery(dynamicCriteria);
        return mongoTemplate.find(query, Match.class);
    }

    private Query buildQuery(Map<String, String> dynamicCriteria) {
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

    private boolean isFechaInicioField(String fieldName) {
        return fieldName.contains("fechaInicio");
    }

    private boolean isFechaFinField(String fieldName) {
        return fieldName.contains("fechaFin");
    }

    private boolean isFechaSpecific(String fieldName) {
        return fieldName.contains("fechaEspecifica");
    }

    private Criteria buildDateCriteria(String fieldName, String value, String operator) {
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

    private Date parseDateString(String value) throws ParseException {
        SimpleDateFormat sdfInput = new SimpleDateFormat("dd.MM.yyyy");
        return sdfInput.parse(value);
    }
}
