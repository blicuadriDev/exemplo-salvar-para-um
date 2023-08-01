package com.godknows.exemplosalvarparaum.services;

import com.godknows.exemplosalvarparaum.dtos.PersonDTO;
import com.godknows.exemplosalvarparaum.dtos.PersonDepartmentDTO;
import com.godknows.exemplosalvarparaum.entities.Department;
import com.godknows.exemplosalvarparaum.entities.Person;
import com.godknows.exemplosalvarparaum.repositories.DepartmentRepository;
import com.godknows.exemplosalvarparaum.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private DepartmentRepository departmentRepository;


    /*PersonDepartmentDTO for
    JSON (obj aninhado):
            {
                "name": "Nova Pessoa",
                "salary": 8000.0,
                "department": {
                        "id": 1
                }
             }
     */
    public PersonDepartmentDTO insert(PersonDepartmentDTO dto) {

        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

        /*
            Aqui,abaixo, o objeto está sendo monitorado pela JPA pois acessamos o repository para pegar o Id.
            Portanto ao salvar o objeto aninhado irá salvar o Id e puxar tb o nome do departamento.

         */
        Department dept = departmentRepository.getReferenceById(dto.getDepartment().getId());


        /*
            Aqui, abaixo, o objeto está Transient, ou seja, não monitorado pela JPA.
            Portanto ao salvar o objeto aninhado irá salvar apenas o Id sem puxar o nome do departamento.
         */
        //Department dept = new Department();
        //dept.setId(dto.getDepartment().getId());

        entity.setDepartment(dept);

        entity = repository.save(entity);

        return new PersonDepartmentDTO(entity);
    }



    //Apenas resaltando que posso usar o mesmo nome do método, sobrecarga, pq estamos usando parâmetros diferentes.
    /*PersonDTO for
    JSON (obj NÃO-aninhado):
            {
            "name": "Nova Pessoa",
            "salary": 8000.0,
            "department": 1
            }
     */
    public PersonDTO insert(PersonDTO dto) {

        Person entity = new Person();
        entity.setName(dto.getName());
        entity.setSalary(dto.getSalary());

        //Department dept = departmentRepository.getReferenceById(dto.getDepartmentId());

        Department dept = new Department();
        dept.setId(dto.getDepartmentId());

        entity.setDepartment(dept);

        entity = repository.save(entity);

        return new PersonDTO(entity);
    }

}
