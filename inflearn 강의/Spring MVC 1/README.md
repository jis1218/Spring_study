##### 웹서버 - 정적파일 제공
##### 웹 애플리케이션 서버 - 프로그램 코드 실행(동적 컨텐츠 반환)
##### WAS에 오류가 생겼을 경우 웹서버가 오류 화면 제공 가능

##### 서블릿 컨테이너에서 서블릿 객체를 생성, 초기화, 호출, 종료하는 등 라이프사이클 관리를 해준다.서블릿 -> 싱글톤으로 관리
##### multi-thread 환경에서 올바른 singleton
##### https://medium.com/@joongwon/multi-thread-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C%EC%9D%98-%EC%98%AC%EB%B0%94%EB%A5%B8-singleton-578d9511fd42

##### 동시 요청을 위한 멀티 쓰레드 처리 지원한다.
##### 멀티 쓰레드 환경이므로 싱글톤 객체(서블릿, 스피링 빈)은 주의해서 사용해야 한다.(멤버 변수(공유변수) 조심)
##### 서블릿을 호출하는 것은 스레드
##### 여러 요청이 오면 필요한만큼(제한없이) 쓰레드를 생성할 수 있겠지만
##### 쓰레드 생성 비용은 비싸고 또한 쓰레드는 컨텍스트 스위칭 비용이 발생한다.
##### 그래서 쓰레드 풀을 만든다.
##### 그러면 쓰레드 생성, 종료하는 비용이 절약된다.

##### 쓰레드 풀 실무 팁
##### WAS의 주요 튜닝 포인트는 최대 쓰레드 수
##### 너무 낮게 설정하면 사용할 수 있는 CPU를 다 사용하지 못함(세팅 잘못함... 50%는 써야함?)
##### 너무 높게 설정하면 CPU, 메모리 리소스 임계점 초과로 서버 다운...
##### 그럼 적정 숫자는 어떻게 찾나? 앱의 복잡도, CPU, 메모리, IO 리소스 상황에 따라 모두 다름
##### 그래서 성능 테스트를 꼭 해봐야 함...
##### 툴 : 아파치 ab, 제이미터, nGrinder(네이버 오픈소스...)
##### 

##### WEB-INF 안에 있는 view는 직접 호출이 안됨, 컨트롤러(서블릿)를 통해서만 가능


##### Servlet으로 개발을 하게 되면 view 코드가 Servlet 클래스 안에 생기게 되어 지저분하게 된다.
##### 그래서 JSP로 뷰를 개발하게 되었는데 역시나 JSP 안에 비즈니스 로직이 생기게 되어 유지보수 하기가 어려워진다.
##### 따라서 나온게 MVC 패턴이다. Controller는 서블릿, View는 JSP, Model은 Controller에서 View로 데이터를 넘겨줄 때 사용하는 객체라고 생각하면 되겠다.
##### 하지만 MVC 패턴도 한계가 있으니 바로 서블릿에서 JSP에 foward 해주는 코드 등 중복되는 코드가 많다는 것이다.
```java
RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
dispatcher.forward(request, response);

String viewPath = "/WEB-INF/views/new-form.jsp";
```

##### 그리고 HttpServletRequest request, HttpServletResponse response는 테스트 하기도 쉽지 않다.  
##### 그래서 공통기능을 처리하는 서블릿을 Front Controller 패턴으로 도입하게 되었다.