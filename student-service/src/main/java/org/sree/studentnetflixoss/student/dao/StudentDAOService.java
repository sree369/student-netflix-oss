package org.sree.studentnetflixoss.student.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.sree.studentnetflixoss.student.model.Parent;
import org.sree.studentnetflixoss.student.model.Student;
import org.sree.studentnetflixoss.student.repository.ParentRepository;
import org.sree.studentnetflixoss.student.repository.StudentRepository;

@Repository
@Transactional
public class StudentDAOService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ParentRepository repo;
	@Autowired
	private StudentRepository studentRepo;
	
	public List<Parent> getAllUsers(){
		
		List<Parent> users = repo.findAll();
		Iterator<Parent> itr = users.iterator();
		while(itr.hasNext()) {
			Parent parent = itr.next();
			long parentId = parent.getParentId();
			// did not return all students with the parentid using Example
//			Student studEg = new Student();
//			studEg.setParentId(parentId);
//			Example<Student> example = Example.of(studEg);
//			List<Student> students =	studentRepo.findAll(example);
//			parent.setStudents(students);
			List<Student> temp = new ArrayList();
			List<Student> studs = studentRepo.findAll();
			Iterator itrStud = studs.iterator();
			while(itrStud.hasNext()) {
				Student student = (Student)itrStud.next();
				if(student.getParentId() == parentId) {
					temp.add(student);
				}
			}
			parent.setStudents(temp);
		}
		
		return users;
		
	}
	
	public Parent getUser(long userId){
		
		Parent parent = repo.findById(userId).get();
		long parentId = parent.getParentId();
		
		// did not return all students with the parentid using Example
//		Student studEg = new Student();
//		studEg.setParentId(parentId);
//		Example<Student> example = Example.of(studEg);
//		List<Student> students =	studentRepo.findAll(example);
//		parent.setStudents(students);
		List<Student> temp = new ArrayList();
		List<Student> studs = studentRepo.findAll();
		Iterator itr = studs.iterator();
		while(itr.hasNext()) {
			Student student = (Student)itr.next();
			if(student.getParentId() == parentId) {
				temp.add(student);
			}
		}
		parent.setStudents(temp);
				
		return parent;
	}
}

	