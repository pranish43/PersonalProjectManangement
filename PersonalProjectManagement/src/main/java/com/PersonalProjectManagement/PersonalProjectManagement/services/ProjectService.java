package com.PersonalProjectManagement.PersonalProjectManagement.services;

import com.PersonalProjectManagement.PersonalProjectManagement.domain.Project;
import com.PersonalProjectManagement.PersonalProjectManagement.exceptions.ProjectIdException;
import com.PersonalProjectManagement.PersonalProjectManagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;



    public Project saveOrUpdateProject(Project project){

    try{
        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        return projectRepository.save(project);

    }catch (Exception e){
        throw new ProjectIdException("Project Id :"+ project.getProjectIdentifier().toUpperCase()+" already exists" );
    }
    }
}
