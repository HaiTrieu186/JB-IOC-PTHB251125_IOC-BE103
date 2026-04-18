package re.edu.bt36.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import re.edu.bt36.dto.response.AppointmentResponse;
import re.edu.bt36.entity.Appointment;
import re.edu.bt36.entity.Doctor;
import re.edu.bt36.exception.ResourceNotFoundException;
import re.edu.bt36.mapper.AppointmentMapper;
import re.edu.bt36.repository.IAppointmentRepository;
import re.edu.bt36.repository.IDoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final IAppointmentRepository appointmentRepository;
    private final IDoctorRepository doctorRepository;


    public List<AppointmentResponse> findAllAppointmentByDoctor(
            Long doctorId
    ){
        Doctor d = doctorRepository.findById(doctorId).orElseThrow(
                () -> new ResourceNotFoundException("Lỗi: không tìm thấy bác sĩ với id: "+doctorId)
        );
        List<Appointment> list= d.getAppointments();

        return list.stream().map(AppointmentMapper::mapToAppointmentResponse).collect(Collectors.toList());
    }



}
