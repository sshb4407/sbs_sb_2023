package com.sdw.exam.demo.service;

import org.springframework.stereotype.Service;

import com.sdw.exam.demo.repository.MemberRepository;
import com.sdw.exam.demo.util.Ut;
import com.sdw.exam.demo.vo.Member;
import com.sdw.exam.demo.vo.ResultData;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public ResultData<Integer> join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member oldMember = getMemberByLoginId(loginId);
		
		// 로그인아이디 중복체크
		if ( oldMember != null ) {
			return ResultData.from("F-7", Ut.f("해당 로그인아이디(%s)는 이미 사용중입니다.", loginId));
		}
		
		// 이름 + 이메일 중복체크
		oldMember = getMemberByNameAndEmail(name, email);
		
		if ( oldMember != null ) {
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일(%s)은 이미 사용중입니다.", name, email));
		}
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1", "회원가입이 완료되었습니다. 환영합니다^^", id);
	}
	
	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

}