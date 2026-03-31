package re.edu.coursemanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import re.edu.coursemanagement.model.Instructor;
import re.edu.coursemanagement.repository.IRepository;

import java.util.List;

@Service
public class InstructorServiceImpl implements IInstructorService{
    private final IRepository<Instructor> instructorRepository;

    @Autowired
    public InstructorServiceImpl(IRepository<Instructor> instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructor() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(int id) {
        return instructorRepository.findById(id);
    }

    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.create(instructor);
    }

    @Override
    public Instructor updateInstructor(Instructor instructor, int id) {
        Instructor i = instructorRepository.findById(id);

        if(i==null){
            return null;
        }

        return instructorRepository.update(id , instructor);
    }

    @Override
    public Instructor deleteInstructorById(int id) {
        Instructor i = instructorRepository.findById(id);

        if(i==null){
            return null;
        }

        return instructorRepository.deleteById(id);
    }


}
