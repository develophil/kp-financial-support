## 카카오페이 서버개발 사전과제
> 1. 지자체 협약 지원 API 개발


### 개발환경
- Mac OS Mojave 10.14.6
- IntelliJ 2019.2
- Spring Boot 2.1.8
- Gradle 5.6.2
- Java 1.8
- JPA
- Lombok

#### 문제해결 방법
* [x] 지자체 코드 생성
    * 코드 구성 : prefix : “REG”, 자리수 : 우리나라 읍면동까지 다 합쳐도 지자체 개수는 4000개 미만이기 때문에 숫자 4자리로 구성.
    * builder : lombok에서 기본 제공되는 @Builder로는 코드값을 생성하기 어려우므로 builder customizing
* [x] csv 파일 DB 저장
    * 객체 매핑
        * 많이 사용되는 opencsv vs jackson-dataformat-csv 비교 
            * 성능 : jackson이 두 배 이상 빠름 (https://github.com/uniVocity/csv-parsers-comparison)
            * 관심도 : opencsv win
            * 사용성 : jackson win
            * 선택 : jackson
    * DB insert 성능
        * jpa batch 사용 : saveAll, batchSize : 50
- [x] ClassNotFoundException 발생 - 톰캣 실행 안됨.
    * 해결은 File > Invalidate Caches / Restart 를 통해서 캐쉬를 지우거나 캐쉬를 지우고 IntelliJ를 재시작 하면 문제는 해결 된다.
IntelliJ가 평소에는 잘 작동하기는 하지만 가끔가다 Gradle이 제대로 싱크가 되지 않아 종종 문제를 일으키곤 한다.
(https://jeonjin.tistory.com/707)
    * ide를 껐다 켜기 귀찮으니 build folder를 삭제하고 실행해보자.
* [x] gradle dependencies 삭제 된 상황
    * gradle window -> reimport all gradle (https://samirbehara.com/2018/01/29/resolving-gradle-issues-in-intellij-idea/)
* [x] jpa domain class convertor 기능 사용
    * 세팅 추가 - JPA @EnableSpringDataWebSupport
    * 도메인 클래스 컨버터, 페이징, 정렬 사용가능
* [x] embeddble sort
    * 객체변수.멤버변수로 접근하니까 됨. Sort.Order.desc("supportLimit.supportLimitExponent")
* [ ] csv 파싱 시 에러 발생
```
com.fasterxml.jackson.databind.exc.MismatchedInputException: Cannot construct instance of `com.kakaopay.hkp.lgs.api.financialsupport.domain.dto.FinancialSupportCsv` (although at least one Creator exists): no String-argument constructor/factory method to deserialize from String value ('1')
```
  

### 빌드 및 실행 방법
```
gradle bootRun
or
./gradlew bootRun
```

- [local Swagger-UI 바로가기](<http://localhost:8080/swagger-ui.html>)

