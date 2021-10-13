package com.insup.hello.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass()); //lombok 사용 @Slf4j

    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name); //운영이든 개발이든 다 남는다
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); //개발 로그
        log.info(" info log={}", name); //운영 로그
        log.warn(" warn log={}", name);
        log.error("error log={}", name);
        //로그를 사용하지 않아도 a+b 계산 로직이 먼저 실행됨, 이런 방식으로 사용하면 X
        log.debug("String concat log=" + name);
        return "ok";
    }
}
