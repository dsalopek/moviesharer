package com.movieaccess.demo.dao;

import com.movieaccess.demo.movie.Movie;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

@UseClasspathSqlLocator
public interface MovieDao {
    @SqlQuery
    Movie findById(@Bind("id") int id);
}
