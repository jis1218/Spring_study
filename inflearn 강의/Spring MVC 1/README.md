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

##버젼 1

##### 1. FrontController에서 URL 매핑 정보를 조회한다.
```java
private Map<String, ControllerV1> controllerMap = new HashMap<>();

public FrontControllerServletV1() {
    controllerMap.put("/front-controller/v1/members/new-form", new
    MemberFormControllerV1());
    controllerMap.put("/front-controller/v1/members/save", new
    MemberSaveControllerV1());
    controllerMap.put("/front-controller/v1/members", new
    MemberListControllerV1());
}
```
##### 2. 매핑정보를 바탕으로 컨트롤러를 호출한다.
##### 3. 컨트롤러에서 바로 JSP를 forward한다.

## 버전 2

##### 버전 1에서는 컨트롤러에서 바로 JSP를 forward를 하였지만 이러면 컨트롤러마다 이 로직이 중복되게 되므로 이것을 FrontController에서 대신 처리해준다.

```java
public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new MyView("/WEB-INF/views/new-form.jsp");
        }
}
```

```java
public class FrontControllerServletV2 extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
    response)
    throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI);

        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
```

## 버전 3

##### 컨트롤러에서는 ModelView를 반환한다. ModelView는 데이터를 담는 Model과 View 이름을 반환하는 것이 합쳐진 것이다.
##### 그리고 프론트 컨트롤러의 viewResolver라는 메서드가 MyView를 반환한다.

## 버전 4

##### 컨트롤러에서는 view 이름만 반환하고 FrontController에서 model을 처리한다. 컨트롤러는 Model 인자를 받기는 한다.

## 버전 5
##### 다양한 컨트롤러를 처리하기 위해 컨트롤러 어댑터(HandlerAdapter)를 이용한다.

##### 먼저 컨트롤러(Handler) 매핑정보를 이용하여 컨트롤러를 가져오고 handlerAdapter 목록을 조회하여 handlerAdpater도 가져온다.

##### 프론트 컨트롤러에서는 이 핸들러 어댑터를 이용하여 핸드러의 메서드를 호출하고 ModelView를 어댑터로부터 받는다.

##### 즉 어댑터 패턴은 다양한 형식의 클래스의 결과값을 일관된 형식으로 맞춰주는 역할을 한다.

##### SpringBootServletInitializer를 상속받은 클래스가 생기는 이유?
```java
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletApplication.class);
	}

}
```
##### 이번 프로젝트는 war로 빌드할 것이기 때문에...
##### https://medium.com/@SlackBeck/spring-boot-%EC%9B%B9-%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98%EC%9D%84-war%EB%A1%9C-%EB%B0%B0%ED%8F%AC%ED%95%A0-%EB%95%8C-%EC%99%9C-springbootservletinitializer%EB%A5%BC-%EC%83%81%EC%86%8D%ED%95%B4%EC%95%BC-%ED%95%98%EB%8A%94%EA%B1%B8%EA%B9%8C-a07b6fdfbbde
##### https://serverwizard.tistory.com/165


##### Servlet의 HttpServletRequest의 getParameter()는 GET URL 쿼리 파라미터 형식도 지원하고 POST HTML Form 형식도 지원한다.