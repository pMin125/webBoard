 광고, 게시글, 댓글, 공지사항 관리 기능을 제공하며, JWT 기반 인증, 실시간 알림, 검색 최적화 등을 포함한 게시판입니다. 
 성능 최적화를 위해 Docker, Redis, MongoDB, ElasticSearch, RabbitMQ 기술 스택을 활용하였습니다.
 
## 개발 환경
- Spring Boot 3.2.7 (Gradle)
- Java 11
- MySQL
- MongoDB
- RabbitMQ
- Docker
- Elasticsearch
- Redis
- JPA
- sWAGGER
- AWS(EC2)

## 기능
1. 회원가입 및 로그인
   * 로그인시 JWT토큰(Access Token, Refresh Token) 방식 사용
2. 게시글 및 댓글 CRUD
   * 게시글 및 댓글 기본적인 CRUD 구성
   * 비동기 데이터 처리:
     Redis에 캐싱된 데이터를 활용하거나 필요한 경우 데이터베이스에서 조회.
3. 광고
   * 광고 작성: 광고 데이터를 데이터베이스와 Redis에 저장.
   * 광고 조회: Redis에서 조회 후, 없으면 데이터베이스에서 가져옴.
   * 광고 클릭 처리: 클릭 데이터를 기록.
   * 조회 및 클릭 통계:
   * MongoDB를 사용하여 광고 조회/클릭 기록을 adId 기준으로 그룹화 및 통계 생성.
   * 통계를 데이터베이스에 저장.
4. 게시글 관리
   * 게시글 작성: 게시글 작성 시 데이터베이스와 Elasticsearch에 색인.
   * 게시글 수정 및 삭제: 작성자 권한 확인 후 데이터베이스와 Elasticsearch에 반영.
   * 게시글 조회:
   * Redis에서 인기 게시글 조회.
   * 데이터베이스에서 최신/이전 게시글 목록 가져오기.
   * 검색 기능: Elasticsearch에서 키워드 기반 검색.
5. 댓글 알림:
댓글 작성자, 게시글 작성자, 해당 게시글의 모든 댓글 작성자에게 RabbitMQ를 통해 알림 전송.

## ERD
<img width="291" alt="erd" src="https://github.com/user-attachments/assets/3a3cd0bb-0c43-4b06-93f2-a9bcd28aaac9" />

## rabbitmq 테스트
<img width="1259" alt="rabbitmq테스트" src="https://github.com/user-attachments/assets/58a4ab65-05d3-4d0e-8b37-39e31911a7b0" />

## 부하테스트
<img width="1235" alt="부하테스트" src="https://github.com/user-attachments/assets/fcb2f495-7496-40d3-903f-67b7202b75e2" />

