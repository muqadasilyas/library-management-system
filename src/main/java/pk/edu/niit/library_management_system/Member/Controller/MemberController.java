package pk.edu.niit.library_management_system.Member.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.niit.library_management_system.ExceptionHandler.MemberNotFoundException;
import pk.edu.niit.library_management_system.Member.DTO.MemberRequestDTO;
import pk.edu.niit.library_management_system.Member.DTO.MemberResponseDTO;
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
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers()
    {
            List<Member> members=memberServices.getAllMembers();
            if(members==null)
            {
                throw new MemberNotFoundException("GET/member: Members not found");
            }
            List<MemberResponseDTO> responseDTOS=members.stream().map(member -> {
                MemberResponseDTO responseDTO=new MemberResponseDTO();
                responseDTO.setMemberId(member.getMemberId());
                responseDTO.setMemberName(member.getMemberName());
                responseDTO.setEmail(member.getEmail());
                responseDTO.setMembershipDate(member.getMembershipDate());
                return responseDTO;
            }).toList();
            log.info("GET/member: {} Members found: ",responseDTOS.size());
            return ResponseEntity.ok(responseDTOS);
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@Valid @RequestBody MemberRequestDTO requestDTO)
    {
            Member member=new Member();
            member.setMemberName(requestDTO.getMemberName());
            member.setEmail(requestDTO.getEmail());
            member.setMembershipDate(requestDTO.getMembershipDate());
            Member created=memberServices.createMember(member);
            MemberResponseDTO responseDTO=new MemberResponseDTO();
            responseDTO.setMemberId(created.getMemberId());
            responseDTO.setMemberName(created.getMemberName());
            responseDTO.setMembershipDate(created.getMembershipDate());
            responseDTO.setEmail(created.getEmail());
            log.info("POST/member : Member created for this id: {}",responseDTO.getMemberId());
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
    public ResponseEntity<MemberResponseDTO> updateMember(@PathVariable long id,@Valid @RequestBody MemberRequestDTO memberRequestDTO)
    {
            Member member=new Member();
            member.setMemberName(memberRequestDTO.getMemberName());
            member.setMembershipDate(memberRequestDTO.getMembershipDate());
            member.setEmail(memberRequestDTO.getEmail());
            Member updated=memberServices.updateMember(id,member);
            MemberResponseDTO responseDTO=new MemberResponseDTO();
            responseDTO.setMemberId(updated.getMemberId());
            responseDTO.setMemberName(updated.getMemberName());
            responseDTO.setEmail(updated.getEmail());
            responseDTO.setMembershipDate(updated.getMembershipDate());

            log.info("PUT/member/id/{}: Member updated for this id: {}",id);
            return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable long id)
    {

            Member member=memberServices.getMemberById(id);
            MemberResponseDTO responseDTO=new MemberResponseDTO();
            responseDTO.setMemberId(member.getMemberId());
            responseDTO.setMemberName(member.getMemberName());
            responseDTO.setEmail(member.getEmail());
            responseDTO.setMembershipDate(member.getMembershipDate());
            log.info("GET/member/id/{}: Member found for this id :{}",id);
            return ResponseEntity.ok(responseDTO);

    }
}
