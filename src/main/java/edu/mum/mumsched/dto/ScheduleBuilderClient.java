package edu.mum.mumsched.dto;

import edu.mum.mumsched.domain.ScheduleJson;
import feign.Headers;
import feign.RequestLine;

import java.util.List;

public interface ScheduleBuilderClient {
//    @RequestLine("GET /{isbn}")
//    BookResource findByIsbn(@Param("isbn") String isbn);
//
    @RequestLine("GET")
    List<SectionDTO> findAll();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void create(ScheduleJson scheduleJson);
}
