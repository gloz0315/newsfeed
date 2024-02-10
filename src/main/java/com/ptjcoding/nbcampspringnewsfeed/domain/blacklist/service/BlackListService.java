package com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.service;

import com.ptjcoding.nbcampspringnewsfeed.domain.blacklist.dto.BlackListRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import org.springframework.http.ResponseEntity;

public interface BlackListService {

  ResponseEntity<CommonResponseDto<String>> register(BlackListRequestDto dto);
}
