package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyDao extends CrudRepository<Faculty, Long>{
    @Query("select f from Faculty f where f.facultyId= :id")
    public Faculty findFacultyById(@Param("id") Long facultyId);

    @Query("select f from Faculty f where f.facultyName= :name")
    public Faculty findFacultyByFacultyName(@Param("name") String facultyName);

    @Query("select f from Faculty f where f.email= :email")
    public Faculty findFacultyByEmail(@Param("email") String email);

    @Query("delete from Faculty f where f.facultyId= :id")
    public void removeById(@Param("id") Long facultyId);

}
