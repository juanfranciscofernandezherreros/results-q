package com.fernandez.basketball.resultsq.mapper;

import com.fernandez.basketball.resultsq.dao.ResultsDAO;
import com.fernandez.basketball.resultsq.dto.ResultsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ResultsMapper {

    ResultsMapper INSTANCE = Mappers.getMapper(ResultsMapper.class);

    ResultsDTO mapToDTO(ResultsDAO resultsDAO);

    List<ResultsDTO> mapListToDTO(List<ResultsDAO> resultsDAOList);

    ResultsDAO mapToDAO(ResultsDTO fixturesDTO);

    List<ResultsDAO> mapListToDAO(List<ResultsDTO> fixturesDTOList);

    default String mapToString(Instant instant) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(instant.atZone(ZoneId.of("UTC")));
    }

    default Instant mapToInstant(String dateString) {
        // Parsea el String a Instant
        return Instant.parse(dateString);
    }

    default Page<ResultsDTO> mapToPageDTO(Page<ResultsDAO> resultsDAOPage) {
        List<ResultsDTO> fixturesDTOList = mapListToDTO(resultsDAOPage.getContent());
        return new PageImpl<>(fixturesDTOList, resultsDAOPage.getPageable(), resultsDAOPage.getTotalElements());
    }
}

