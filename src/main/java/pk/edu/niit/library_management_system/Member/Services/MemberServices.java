package pk.edu.niit.library_management_system.Member.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.Member.Entity.Member;
import pk.edu.niit.library_management_system.Member.Repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class MemberServices {
    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers()
    {
        return memberRepository.findAll();
    }

    public Member createMember(Member member)
    {
        return memberRepository.save(member);
    }

    public void deleteMember(long id)
    {
        memberRepository.deleteById(id);
    }

    public Member updateMember(long id, Member member)
    {
        Optional<Member> existing=memberRepository.findById(id);
        if(existing.isPresent())
        {
            Member updated=existing.get();
            updated.setMemberName(member.getMemberName());
            updated.setEmail(member.getEmail());
            updated.setMembershipDate(member.getMembershipDate());
            return memberRepository.save(updated);
        }
        return null;
    }

    public Member getMemberById(long id)
    {
        Optional<Member> existing=memberRepository.findById(id);
        if(existing.isPresent())
        {
            Member member=existing.get();
            return member;
        }
        return null;
    }
}
