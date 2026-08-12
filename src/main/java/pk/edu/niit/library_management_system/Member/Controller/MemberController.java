package pk.edu.niit.library_management_system.Member.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.ExceptionHandler.MemberNotFoundException;
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
            List<Member> members=memberServices.getAllMembers();
            if(members==null)
            {
                throw new MemberNotFoundException("GET/member: Members not found");
            }
            log.info("GET/member: {} Members found: ",members.size());
            return ResponseEntity.ok(members);
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member)
    {
            Member created=memberServices.createMember(member);
            log.info("POST/member : Member created for this id: {}",created.getMemberId());
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable long id)
    {

            memberServices.deleteMember(id);
            log.info("DELETE/member/id/{} : Member deleted for this id : {}",id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("id/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable long id, @RequestBody Member member)
    {
            Member updated=memberServices.updateMember(id,member);

            log.info("PUT/member/id/{}: Member updated for this id: {}",id);
            return ResponseEntity.ok(updated);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable long id)
    {

            Member member=memberServices.getMemberById(id);
            log.info("GET/member/id/{}: Member found for this id :{}",id);
            return ResponseEntity.ok(member);

    }
}
