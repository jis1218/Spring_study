# Eureka
##### 서비스를 등록하고 서비스를 찾아주는 전화번호부 같은 서비스

# API Gateway의 역할
##### 인증 및 권한 부여
##### 서비스 검색 통합
##### 응답 캐싱
##### 정책, 회로 차단기 및 QoS 다시 시도
##### 속도 제한
##### 부하 분산
##### 로깅, 추적, 상관 관계
##### 헤더, 쿼리 문자열 및 청구 변환
##### IP 허용 목록에 추가

##### Zuul -> blocking API
##### Spring Gateway -> 비동기
##### 그래서 tomcat이 아니라 netty가 실행된다. netty는 비동기 기반인가보다...

##### Spring cloud gateway를 사용할 때 로드밸런서를 사용하면 기본적으로 Round-robin 방식으로 동작을 한다.

##### 아무리 로그인을 해도 UsernamePasswordAuthenticationFilter를 상속한 나의 CustomFilter가 동작하지 않은 이유...

##### UsernamePasswordAuthenticationFilter는 /login 에 접근하였을 때만 trigger 되기 때문에
##### 생성자에서 this.setFilterProcessesUrl을 추가해줘야 한다...
```java
public AuthenticationFilter() {
        this.setFilterProcessesUrl("/users/login");
    }
```


##### 왜 아래와 같이 설정하면 

```java
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailService);
    }
```

```java
        return getAuthenticationManager()
        .authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword(), new ArrayList<>())
            );
```
##### 위의 코드의 getAuthenticationManger()는 null이 되는가??
##### https://stackoverflow.com/questions/51986766/spring-security-getauthenticationmanager-returns-null-within-custom-filter

##### 스프링 시큐리티 관련 내용인데...
