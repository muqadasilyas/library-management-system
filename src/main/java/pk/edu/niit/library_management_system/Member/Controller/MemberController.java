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
import pk.edu.niit.library_management_system.Member.Mapper.MemberMapper;
import pk.edu.niit.library_management_system.Member.Services.MemberServices;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberServices memberServices;
    @Autowired
    private MemberMapper memberMapper;
    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers()
    {
            List<Member> members=memberServices.getAllMembers();
            if(members.isEmpty())
            {
                throw new MemberNotFoundException("GET/member: Members not found");
            }
            List<MemberResponseDTO> responseDTOS=members.stream().map(member -> {
                MemberResponseDTO responseDTO=memberMapper.toResponseDTO(member);
                return responseDTO;
            }).toList();
            log.info("GET/member: {} Members found: ",responseDTOS.size());
            return ResponseEntity.ok(responseDTOS);
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> createMember(@Valid @RequestBody MemberRequestDTO requestDTO)
    {
            Member member=memberMapper.toEntity(requestDTO);
            Member created=memberServices.createMember(member);
            MemberResponseDTO responseDTO=memberMapper.toResponseDTO(created);
            log.info("POST/member : Member created for this id: {}",responseDTO.getMemberId());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

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
            Member member=memberMapper.toEntity(memberRequestDTO);
            Member updated=memberServices.updateMember(id,member);
            MemberResponseDTO responseDTO=memberMapper.toResponseDTO(updated);

            log.info("PUT/member/id/{}: Member updated for this id: {}",id);
            return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(@PathVariable long id)
    {

            Member member=memberServices.getMemberById(id);
            MemberResponseDTO responseDTO=memberMapper.toResponseDTO(member);
            log.info("GET/member/id/{}: Member found for this id :{}",id);
            return ResponseEntity.ok(responseDTO);

    }
}
