package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // for changing userName and password for a user
    // here in the path we send the name of the user whose credentials we have to update
    // and in the request body we send the updated values of the user
    // it has to be authenticated
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(user.getUserName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
// for only updating the password of a user
// here in the request body we send the updated password but the userName will be same
//    @PutMapping
//    public ResponseEntity<?> updateUser(@RequestBody User user) {
//        User userInDb = userService.findByUserName(user.getUserName());
//        if(userInDb != null){
//            userInDb.setUserName(user.getUserName());
//            userInDb.setPassword(user.getPassword());
//            userService.saveEntry(userInDb);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }