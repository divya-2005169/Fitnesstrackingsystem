package com.fitness.service;

import com.fitness.entity.Member;
import com.fitness.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    
    @Autowired
    private MemberRepository memberRepository;
    
    public Member addMember(Member member) {
        return memberRepository.save(member);
    }
    
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public Optional<Member> getMemberByUserId(String userId) {
        return memberRepository.findByUserId(userId);
    }
    
    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }
    
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
    
    public boolean existsByUserId(String userId) {
        return memberRepository.existsByUserId(userId);
    }
    
    public Member updateWorkoutDetails(String userId, String workoutType, String workoutDuration) {
        Optional<Member> memberOpt = memberRepository.findByUserId(userId);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            member.setWorkoutType(workoutType);
            member.setWorkoutDuration(workoutDuration);
            return memberRepository.save(member);
        }
        return null;
    }
} 