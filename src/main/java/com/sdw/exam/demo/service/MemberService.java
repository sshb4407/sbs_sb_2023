package com.sdw.exam.demo.service;

import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import com.sdw.exam.demo.repository.MemberRepository;
import com.sdw.exam.demo.vo.Member;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	public int join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member oldMember = getMemberByLoginId(loginId);
		
		// 로그인아이디 중복체크
		if ( oldMember != null ) {
			return -1;
		}
		
		// 이름 + 이메일 중복체크
		oldMember = getMemberByNameAndEmail(name, email);
		
		if ( oldMember != null ) {
			return -2;
		}
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		return memberRepository.getLastInsertId();
	}
	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}
	private Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}