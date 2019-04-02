package demo.springboot.redis.cache.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.springboot.redis.cache.model.Student;


public interface StudentRepository extends JpaRepository<Student, String> {
	Optional<Student> findById(String id);

}
