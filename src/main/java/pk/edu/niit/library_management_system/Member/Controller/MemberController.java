package pk.edu.niit.library_management_system.Member.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.Member.Entity.Member;
import pk.edu.niit.library_management_system.Member.Services.MemberServices;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberServices memberServices;

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers()
    {
        try{
            List<Member> members=memberServices.getAllMembers();
            log.info("GET/member: {} Members found: ",members.size());
            return ResponseEntity.ok(members);
        }
        catch (Exception e)
        {
            log.error("Error getting all members: ",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member)
    {
        try{
            Member created=memberServices.createMember(member);
            log.info("POST/member : Member created for this id: {}",created.getMemberId());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e)
        {
            log.error("Error creating member: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable long id)
    {
        try{
            Member existing=memberServices.getMemberById(id);
            if(existing==null)
            {
                log.error("Member not found for this id: {}",id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            memberServices.deleteMember(id);
            log.info("DELETE/member/id/{} : Member deleted for this id : {}",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (Exception e)
        {
            log.error("Error deleting member for this id : {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("member/id/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable long id, @RequestBody Member member)
    {
        try{
            Member updated=memberServices.updateMember(id,member);
            if(updated==null)
            {
                log.error("Member not found for this id: {}",id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("PUT/member/id/{}: Member updated for this id: {}",id);
            return ResponseEntity.ok(updated);
        }
        catch (Exception e)
        {
            log.error("Error updating the member: {}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id)
    {
        try{
            Member member=memberServices.getMemberById(id);
            if (member==null)
            {
                log.error("Member not found for this id: {}",id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            log.info("GET/member/id/{}: Member found for this id :{}",id);
            return ResponseEntity.ok(member);
        }
        catch (Exception e)
        {
            log.error("Error getting member for this id:{}",id);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
