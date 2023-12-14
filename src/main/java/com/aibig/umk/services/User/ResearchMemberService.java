package com.aibig.umk.services.User;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aibig.umk.model.User.ResearchMember;
import com.aibig.umk.repository.User.ResearchMemberRepository;

@Service
public class ResearchMemberService {

    private final ResearchMemberRepository researchMemberRepository;

    public ResearchMemberService(ResearchMemberRepository researchMemberRepository) {
        this.researchMemberRepository = researchMemberRepository;
    }

    public void saveResearchMember(ResearchMember researchMember) {
        researchMemberRepository.save(researchMember);
    }

    public ResearchMember getResearchMemberById(int researchMemberId) {
        return researchMemberRepository.findById(researchMemberId).orElse(null);
    }

    public void deleteResearchMemberById(int researchMemberId) {
        researchMemberRepository.deleteById(researchMemberId);
    }

    public ResearchMember getResearchMemberByResearchMemberName(String researchMemberName) {
        return researchMemberRepository.findByResearchMemberName(researchMemberName);
    }

    public void updateResearchMember(ResearchMember researchMember) {
        ResearchMember existingResearchMember = new ResearchMember(researchMember);
        researchMemberRepository.save(existingResearchMember);
    }

    public List<ResearchMember> getAllResearchMembers() {
        return researchMemberRepository.findAll();
    }

}
