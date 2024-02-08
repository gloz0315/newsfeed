package com.ptjcoding.nbcampspringnewsfeed.domain.vote.controller;

import com.ptjcoding.nbcampspringnewsfeed.domain.common.dto.CommonResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteCreateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteResponseDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.dto.VoteUpdateRequestDto;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.model.Vote;
import com.ptjcoding.nbcampspringnewsfeed.domain.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/votes")
public class VoteController {

  private final VoteService voteService;


  @PostMapping
  public ResponseEntity<CommonResponseDto<VoteResponseDto>> createVote(
      @Validated @RequestBody VoteCreateRequestDto requestDto
  ) {
    Vote vote = voteService.createVote(requestDto);

    return CommonResponseDto.ok("투표 등록 성공", VoteResponseDto.of(vote));
  }

  @PutMapping("/{voteId}")
  public ResponseEntity<CommonResponseDto<VoteResponseDto>> updateVote(
      @PathVariable Long voteId, @Validated @RequestBody VoteUpdateRequestDto requestDto
  ) {
    Vote vote = voteService.updateVote(voteId, requestDto);

    return CommonResponseDto.ok("투표 수정 성공", VoteResponseDto.of(vote));
  }

  @DeleteMapping("/{voteId}")
  public ResponseEntity<CommonResponseDto<Void>> deleteVote(
      @PathVariable Long voteId
  ) {
    voteService.deleteVote(voteId);

    return CommonResponseDto.ok("투표 삭제 성공", null);
  }

}
