package springbook.tr.auth.signup.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springbook.tr.auth.signup.model.SignUpRequestDto;
import springbook.tr.auth.signup.model.SignUpResponseDto;
import springbook.tr.exception.CustomException;
import springbook.tr.exception.ErrorCode;
import springbook.tr.user.model.entity.User;
import springbook.tr.user.model.repository.UserRepository;

@Service
public class SignUpService {

	private final UserRepository userRepository;

	public SignUpService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// 회원 가입
	@Transactional
	public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {

		validSignUpRequest(signUpRequestDto);

		User user = signUpRequestDto.toEntity();
		user.passwordEncoder(signUpRequestDto.getPassword());
		userRepository.save(user);

		return new SignUpResponseDto(user.getId());
	}

	// 유효 검증 메서드
	private void validSignUpRequest(SignUpRequestDto signUpRequestDto) {


		if (!signUpRequestDto.getPassword().equals(signUpRequestDto.getConfirmPassword())) {
			throw new CustomException(ErrorCode.PASSWORD_MISMATCH);
		}
		if (userRepository.findByUsername(signUpRequestDto.getUsername()).isPresent()) {
			throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
		}
	}
}
