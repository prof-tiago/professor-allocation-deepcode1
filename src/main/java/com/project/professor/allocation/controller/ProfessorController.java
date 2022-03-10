package com.project.professor.allocation.controller;

import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.service.ProfessorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        super();
        this.professorService = professorService;
    }

    @ApiOperation(value = "Find all professors")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Professor>> findAll(@RequestParam(name = "name", required = false) String name) {
        List<Professor> professors = professorService.findAll(name);
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @ApiOperation(value = "Find a professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @GetMapping(path = "/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Professor> findById(@PathVariable(name = "professor_id") Long id) {
        Professor professor = professorService.findById(id);
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(professor, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Find professors by department")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping(path = "/department/{department_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Professor>> findByDepartment(@PathVariable(name = "department_id") Long id) {
        List<Professor> professors = professorService.findByDepartment(id);
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @ApiOperation(value = "Save a professor")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Professor> save(@RequestBody Professor professor) {
        try {
            professor = professorService.save(professor);
            return new ResponseEntity<>(professor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Update a professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
    @PutMapping(path = "/{professor_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Professor> update(@PathVariable(name = "professor_id") Long id,
                                            @RequestBody Professor professor) {
        professor.setId(id);
        try {
            professor = professorService.update(professor);
            if (professor == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(professor, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Delete a professor")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping(path = "/{professor_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteById(@PathVariable(name = "professor_id") Long id) {
        professorService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Delete all professors")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteAll() {
        professorService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
