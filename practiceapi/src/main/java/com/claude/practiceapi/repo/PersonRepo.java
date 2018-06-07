package com.claude.practiceapi.repo;

import com.claude.practiceapi.domain.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person,Long> {
}
