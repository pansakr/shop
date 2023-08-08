package com.core.shop.member.service;

import com.core.shop.cart.entity.CartEntity;
import com.core.shop.cart.repository.CartRepository;
import com.core.shop.member.dto.MemberRequest;
import com.core.shop.member.dto.MemberResponse;
import com.core.shop.member.entity.MemberEntity;
import com.core.shop.member.repository.MemberRepository;
import com.core.shop.security.domain.DefaultUserDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService{

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper mapper;

    @Override
    public void signup(MemberRequest request) {

        Optional<MemberEntity> maybeMember = memberRepository.findByEmail(request.getEmail());
        if(maybeMember.isPresent()){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }

        MemberEntity member = mapper.map(request, MemberEntity.class);
        memberRole(member, passwordEncoder);
        MemberEntity savedMember = memberRepository.save(member);

        CartEntity cart = new CartEntity();
        cart.setMember(savedMember);
        cartRepository.save(cart);
    }

    @Override
    public MemberResponse memberModifyForm(Long memberId) {

        MemberEntity member = memberRepository.findById(memberId)
                                              .orElseThrow(RuntimeException::new);

        return mapper.map(member, MemberResponse.class);
    }

    @Override
    public MemberResponse memberModify(MemberRequest request) {

        MemberEntity member = memberRepository.findByEmail(request.getEmail())
                                              .orElseThrow(RuntimeException::new);

        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setAddress1(request.getAddress1());
        member.setAddress2(request.getAddress2());

        return mapper.map(member, MemberResponse.class);
    }

    @Override
    public void memberDelete(MemberRequest request) {

        MemberEntity member = memberRepository.findByEmail(request.getEmail())
                                              .orElseThrow(RuntimeException::new);

        cartRepository.deleteByMember(member);
        memberRepository.deleteByEmail(member.getEmail());


    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MemberEntity member = memberRepository.findByEmail(email)
                                              .orElseThrow(() -> new UsernameNotFoundException(email));

        return new DefaultUserDetails(member.getEmail(), member.getPassword(), member.getRole().name());
    }
}
