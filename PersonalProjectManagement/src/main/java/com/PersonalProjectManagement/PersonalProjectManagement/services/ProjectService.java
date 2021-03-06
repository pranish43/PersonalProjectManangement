package com.PersonalProjectManagement.PersonalProjectManagement.services;

import com.PersonalProjectManagement.PersonalProjectManagement.domain.Backlog;
import com.PersonalProjectManagement.PersonalProjectManagement.domain.Project;
import com.PersonalProjectManagement.PersonalProjectManagement.exceptions.ProjectIdException;
import com.PersonalProjectManagement.PersonalProjectManagement.repositories.BacklogRepository;
import com.PersonalProjectManagement.PersonalProjectManagement.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;



    public Project saveOrUpdateProject(Project project){

    try{
        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        if(project.getId()==null){
            Backlog backlog = new Backlog();
            project.setBlacklog(backlog);
            backlog.setProject(project);
            backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        }

        if(project.getId()!= null){
            project.setBlacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
        }
        return projectRepository.save(project);

    }catch (Exception e){
        throw new ProjectIdException("Project Id :"+ project.getProjectIdentifier().toUpperCase()+" already exists" );
    }
    }

    public Project findProjectByIdentifier(String projectId){
        Project project =projectRepository.findByProjectIdentifier(projectId.toUpperCase());
    if(project == null){
        throw new ProjectIdException("Project Id  does not exists" );

    }
        return  project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Cannot Project with ID" + projectId+ "project does not exists ");
        }
        projectRepository.delete(project);
    }



}
