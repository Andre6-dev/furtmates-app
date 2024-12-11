package io.devandre.furtmates.pets.entity.jdbc;

import com.google.common.base.CaseFormat;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class QueryBuilder {

    private StringBuilder select = new StringBuilder();
    private StringBuilder from = new StringBuilder();
    private StringBuilder where = new StringBuilder();
    private StringBuilder orderBy = new StringBuilder();
    private boolean whereAdded = false;

    public QueryBuilder select(String selectClause) {
        select.append(selectClause);
        return this;
    }

    public QueryBuilder from(String fromClause) {
        from.append(fromClause);
        return this;
    }

    public QueryBuilder where(PetFilter filter, MapSqlParameterSource params) {
        if (filter != null) {
            if (filter.getName() != null) {
                addWhereCondition("LOWER(p.name) LIKE LOWER(:name)");
                params.addValue("name", "%" + filter.getName() + "%");
            }

            if (filter.getSpeciesId() != null) {
                addWhereCondition("p.species_id = :speciesId");
                params.addValue("speciesId", filter.getSpeciesId());
            }

            if (filter.getBreedId() != null) {
                addWhereCondition("p.breed_id = :breedId");
                params.addValue("breedId", filter.getBreedId());
            }

            if (filter.getSize() != null) {
                addWhereCondition("p.size = :size");
                params.addValue("size", filter.getSize());
            }

            if (filter.getAdoptionStatus() != null) {
                addWhereCondition("p.adoption_status = :adoptionStatus");
                params.addValue("adoptionStatus", filter.getAdoptionStatus());
            }

            if (filter.getShelterId() != null) {
                addWhereCondition("p.shelter_id = :shelterId");
                params.addValue("shelterId", filter.getShelterId());
            }

            if (filter.getIsSpayed() != null) {
                addWhereCondition("p.is_spayed = :isSpayed");
                params.addValue("isSpayed", filter.getIsSpayed());
            }

            if (filter.getHealthStatus() != null) {
                addWhereCondition("p.health_status = :healthStatus");
                params.addValue("healthStatus", filter.getHealthStatus());
            }

            if (filter.getMinWeight() != null) {
                addWhereCondition("p.weight >= :minWeight");
                params.addValue("minWeight", filter.getMinWeight());
            }

            if (filter.getMaxWeight() != null) {
                addWhereCondition("p.weight <= :maxWeight");
                params.addValue("maxWeight", filter.getMaxWeight());
            }

            if (filter.getArrivalDateFrom() != null) {
                addWhereCondition("p.arrival_date >= :arrivalDateFrom");
                params.addValue("arrivalDateFrom", filter.getArrivalDateFrom());
            }

            if (filter.getArrivalDateTo() != null) {
                addWhereCondition("p.arrival_date <= :arrivalDateTo");
                params.addValue("arrivalDateTo", filter.getArrivalDateTo());
            }
        }
        return this;
    }

    public QueryBuilder sort(Sort sort) {
        if (sort != null && sort.isSorted()) {
            orderBy.append(" ORDER BY ");
            sort.forEach(order -> {
                String property = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, order.getProperty());
                orderBy.append("p.").append(property)
                        .append(" ").append(order.getDirection().name())
                        .append(",");
            });
            orderBy.setLength(orderBy.length() - 1); // Remove last comma
        }
        return this;
    }

    private void addWhereCondition(String condition) {
        if (!whereAdded) {
            where.append(" WHERE ");
            whereAdded = true;
        } else {
            where.append(" AND ");
        }
        where.append(condition);
    }

    public StringBuilder build() {
        return new StringBuilder()
                .append(select)
                .append(" ")
                .append(from)
                .append(where)
                .append(orderBy);
    }

    public String getFromClause() {
        return from.toString();
    }

    public String getWhereClause() {
        return where.toString();
    }
}
