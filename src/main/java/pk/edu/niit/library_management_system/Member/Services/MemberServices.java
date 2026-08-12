package pk.edu.niit.library_management_system.Member.Services;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pk.edu.niit.library_management_system.BorrowRecord.Entity.BorrowRecord;
import pk.edu.niit.library_management_system.ExceptionHandler.MemberNotFoundException;
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
        if(!memberRepository.existsById(id))
        {
            throw new MemberNotFoundException("DELETE/member/id/"+id+": Member not found for id: "+id);
        }
        memberRepository.deleteById(id);
    }

    public Member updateMember(long id, Member member)
    {
        Member existing=memberRepository.findById(id).orElseThrow(()->
                new MemberNotFoundException("PUT/member/id/"+id+": Member not found for id: "+id));

            existing.setMemberName(member.getMemberName());
            existing.setEmail(member.getEmail());
            existing.setMembershipDate(member.getMembershipDate());
            return memberRepository.save(existing);

    }

    public Member getMemberById(long id) {
        Member existing = memberRepository.findById(id).orElseThrow(() ->
                new MemberNotFoundException("GET/member/id/" + id + ": Member not found for id : " + id));
        return existing;
    }
}
