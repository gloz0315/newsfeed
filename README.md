# 하찮은 토론장 프로젝트

## 프로젝트 개요

유저들끼리 주제에 대한 찬반 의견을 통해 토론하는 사이트

## 기능 정의

- 모든 기능들의 필수 항목은 `null`이 될 수 없으며, 만약 `null`값이 들어오게 된다면 `MethodArgumentNotValidException` 예외를 발생시킨다.
  
- 게시글을 작성하고 조회하며 수정, 삭제를 할 수 있다.
     - 수정과 삭제 하기 전에 작성자인지 확인하며 만약 틀리다면 `CustomException` 예외를 발생시킨다.
     - 조회하는 유저가 게시글에 투표를 했는지 확인할 수 있다.
       
- 명예의 전당을 통해 실시간으로 TOP3 투표 수의 게시글을 확인할 수 있다.
  
- 게시글에 댓글을 작성하고, 수정하며 삭제할 수 있다.
   - 투표를 하지 않은 게시글에는 댓글을 작성할 수 없다.
   - 대댓글 기능을 적용했다.
   - 수정과 삭제 하기 전에 작성자인지 확인하며 만약 틀리다면 `CustomException` 예외를 발생시킨다.
   - 댓글에서 댓글 작성자의 투표 상태를 확인할 수 있다.
     
- 북마크를 등록할 수 있으며 해제가 가능하다.

- 회원가입과 탈퇴, 로그인 및 로그아웃 기능을 적용했다.
  - 로그인을 하였을 때, 비밀번호나 이메일이 틀리다면 `CustomException` 예외를 발생시킨다.

- 유저는 자신의 정보를 조회할 수 있다.

- 관리자 권한을 가진 유저는 특정 이메일을 블랙리스트로 등록, 해제할 수 있다.
   - 블랙리스트에 등록된 이메일은 회원가입 및 로그인을 하였을 때, `CustomException` 예외를 발생시킨다.
 
## 기술 스택

- JDK 17
- SpringBoot 3.2.2
- Spring MVC
- Swagger 2.3.0
- Lombok
- Jpa
- MySQL 8.3.x
- AWS
- Docker
- DockerHub
- EC2
- RDS
- Github actions
- JWT
- Slack

## ERD

![ERD사진](https://github.com/nbcamp-spring-ptjcodingcompany/nbcamp-spring-newsfeed/assets/80665963/cd1f6a02-8427-45cd-b585-20acc6169a1b)

## 아키텍쳐

![아키텍쳐 그림](https://github.com/nbcamp-spring-ptjcodingcompany/nbcamp-spring-newsfeed/assets/80665963/d3c99ace-48b9-4379-b164-a9f692b2b8fc)

## API 명세서

[API 명세서](https://teamsparta.notion.site/aeb121823d8c4ec89f9aaa15f5e46783)

## Member

- 팀장 (박태준)
   - https://github.com/ruh0n
     
- 팀원1 (이병수)
   - https://github.com/gloz0315
     
- 팀원2 (권용수)
   - https://github.com/Modafi
     
- 팀원3 (이준우)
   - https://github.com/devJWL

