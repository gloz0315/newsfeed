package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.dto.BlackListRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.repository.BlackListRepository;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListServiceImpl implements BlackListService {

  private final BlackListRepository blackListRepository;

  @Override
  public ResponseEntity<CommonResponseDto<String>> register(BlackListRequestDto dto) {
    blackListRepository.register(dto.getEmail());

    return CommonResponseDto.ok("블랙리스트에 추가하였습니다", dto.getEmail());
  }
}
