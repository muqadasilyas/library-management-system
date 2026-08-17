package pk.edu.niit.library_management_system.Member.Mapper;

import org.springframework.stereotype.Component;
import pk.edu.niit.library_management_system.Member.DTO.MemberRequestDTO;
import pk.edu.niit.library_management_system.Member.DTO.MemberResponseDTO;
import pk.edu.niit.library_management_system.Member.Entity.Member;

@Component
public class MemberMapper {
    public Member toEntity(MemberRequestDTO requestDTO)
    {
        Member member=new Member();
        member.setMemberName(requestDTO.getMemberName());
        member.setEmail(requestDTO.getEmail());
        member.setMembershipDate(requestDTO.getMembershipDate());
        return member;

    }
    public MemberResponseDTO toResponseDTO(Member member)
    {
        MemberResponseDTO responseDTO=new MemberResponseDTO();
        responseDTO.setMemberId(member.getMemberId());
        responseDTO.setMemberName(member.getMemberName());
        responseDTO.setMembershipDate(member.getMembershipDate());
        responseDTO.setEmail(member.getEmail());
        return responseDTO;
    }
}
