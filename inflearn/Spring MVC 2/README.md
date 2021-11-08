##### 세션 탈취시 대응방법(IP 보안)
https://webhack.dynu.net/?idx=20161111.003&print=friendly

##### setAttribute를 할 때 name이 항상 같은데 사용자를 어떻게 구분?? -> Cookie에 담겨있는 JSESSIONID로 구분한다!!
```java
session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
```
##### https://okky.kr/article/918315
##### https://okky.kr/article/498323
##### https://jjunii486.tistory.com/245

##### 각 요청마다 Session 객체가 다름, 각 클라이언트마다 하나씩 생성됨
##### 세션은 클라이언트의 상태를 서버가 가지고 있는 것이다. 클라이언트가 서버에 자신의 상태를 넘겨준다.(로그인 정보, 다른 값 등) 서버에서 그 값을 세션에 저장하고 (어떤 기준에 대한 상태를 가지고 있겠다고 하고) 그에 대한 세션 id를 클라이언트에 주게 되면 클라이언트는 그 id(JSESSIONID)값을 이용해 서버에 있는 session의 정보를 이용할 수 있다. 즉 다른 사람이 sessionId를 탈취하면 그 sessionId에 해당하는 session을 이용할 수 있다는 것이다.
##### 클라이언트에서 서버에 세션을 요청하게 되면 서버는 세션을 만들고 그에 대한 고유한 id(JSESSIONID)를 만들어 클라이언트에 준다.

##### session id를 이용해 그 session을 가지고 올 수 있는데 보안상의 이유로 deprecated 된 HttpSessionContext를 보니 얼핏 알 것 같다.
```java
@Deprecated
public interface HttpSessionContext {

    @Deprecated
    public HttpSession getSession(String sessionId);

    @Deprecated
    public Enumeration<String> getIds();
}
```

##### 현재는 어떤 것을 사용하고 있는가? 잘 모르겠다

##### 클라이언트는 다른 요청 시마다 세션 id를 이용해 자신의 상태를 서버에 알려준다.
##### HttpSession은 메모리에 저장되지만 서버의 정책에 따라 session을 db에 저장할 수도 있다.


##### 다중 서버 환경에서 세션을 관리하는 방법
##### https://velog.io/@hyeminn/%EB%8B%A4%EC%A4%91-%EC%84%9C%EB%B2%84-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-%EC%84%B8%EC%85%98%EC%9D%84-%EA%B4%80%EB%A6%AC%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95

##### 필터 -> 서블릿이 제공하는 기능
##### 인터셉터 -> 스프링이 제공하는 기능