package com.dreamgames.backendengineeringcasestudy.controller;

import com.dreamgames.backendengineeringcasestudy.controller.request.CreateUserRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.EnterTournamentRequest;
import com.dreamgames.backendengineeringcasestudy.controller.request.UpdateLevelRequest;
import com.dreamgames.backendengineeringcasestudy.controller.response.CreateUserResponse;
import com.dreamgames.backendengineeringcasestudy.dao.entity.GroupLeaderboard;
import com.dreamgames.backendengineeringcasestudy.dao.entity.Users;
import com.dreamgames.backendengineeringcasestudy.service.TournamentService;
import com.dreamgames.backendengineeringcasestudy.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UsersController {
    private final UsersService usersService;


    public UsersController(UsersService usersService, TournamentService tournamentService) {
        this.usersService = usersService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Users>> getAll(){
        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(usersService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> create(@Valid @RequestBody CreateUserRequest request) throws Exception {
        return new ResponseEntity<>(usersService.createNewUser(request), HttpStatus.CREATED);
    }

    @PutMapping("/level-up/{id}")
    public ResponseEntity<Users> update(@Valid @RequestBody UpdateLevelRequest request) throws Exception{
        return new ResponseEntity<>(usersService.updateLevelProgress(request), HttpStatus.OK);
    }

}
