// 스프링 사전용 페이지
/*
// 롬복 어노테이션

@NoArgsConstructor
기본생성자를 자동으로 추가한다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
기본생성자의 접근 권한을 protected로 제한한다.
생성자로 protected Posts() {}와 같은 효과
Entity Class를 프로젝트 코드상에서 기본생성자로 생성하는 것은 금지하고, JPA에서 Entity 클래스를 생성하는것은 허용하기 위해 추가한다.

@AllArgsConstructor
모든 필드 값을 파라미터로 받는 생성자를 추가한다.

@RequiredArgsConstructor
final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 추가한다.
final: 값이 할당되면 더 이상 변경할 수 없다.

@Getter
Class 내 모든 필드의 Getter method를 자동 생성한다.

@Setter
Class 내 모든 필드의 Setter method를 자동 생성한다.
Controller에서 @RequestBody로 외부에서 데이터를 받는 경우엔 기본생성자 + set method를 통해서만 값이 할당된다.
그래서 이때만 setter를 허용한다.
Entity Class에는 Setter를 설정하면 안된다.
차라리 DTO 클래스를 생성해서 DTO 타입으로 받도록 하자

@ToString
Class 내 모든 필드의 toString method를 자동 생성한다.
@ToString(exclude = "password")
특정 필드를 toString() 결과에서 제외한다.
클래스명(필드1이름=필드1값, 필드2이름=필드2값, …) 식으로 출력된다.

@EqualsAndHashCode
equals와 hashCode method를 오버라이딩 해주는 Annotation이다.
@EqualsAndHashCode(callSuper = true)
callSuper 속성을 통해 equals와 hashCode 메소드 자동 생성 시 부모 클래스의 필드까지 감안할지 안 할지에 대해서 설정할 수 있다.
즉, callSuper = true로 설정하면 부모 클래스 필드 값들도 동일한지 체크하며,
callSuper = false로 설정(기본값)하면 자신 클래스의 필드 값들만 고려한다.

@Builder
어느 필드에 어떤 값을 채워야 할지 명확하게 정하여 생성 시점에 값을 채워준다.

Constructor와 Builder의 차이
생성 시점에 값을 채워주는 역할은 똑같다.
하지만 Builder를 사용하면 어느 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있다.
해당 Class의 Builder 패턴 Class를 생성 후 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함된다.

@Data
@Getter @Setter @EqualsAndHashCode @AllArgsConstructor을 포함한 Lombok에서 제공하는 필드와 관련된 모든 코드를 생성한다.
실제로 사용하지 않는것이 좋다.
전체적인 모든 기능 허용으로 위험 존재

@ComponentScan
@Component와 @Service, @Repository, @Controller, @Configuration이 붙은 클래스 Bean들을 찾아서 Context에 bean등록

@Component
개발자가 직접 작성한 Class를 Bean으로 등록

@Bean
개발자가 직접 제어가 불가능한 외부 라이브러리등을 Bean으로 만들려할 때 사용

@Autowired
속성(field), setter method, constructor(생성자)에서 사용하며 Type에 따라 알아서 Bean을 주입
스프링이 자동적으로 값을 할당한다.
Controller 클래스에서 DAO나 Service에 관한 객체들을 주입 시킬 때 많이 사용

@Controller
Spring의 Controller를 의미한다. Spring MVC에서 Controller클래스에 쓰인다.

@RestController
Spring에서 Controller 중 View로 응답하지 않는, Controller를 의미한다.
method의 반환 결과를 JSON 형태로 반환한다.
이 Annotation이 적혀있는 Controller의 method는 HttpResponse로 바로 응답이 가능하다.
@ResponseBody 역할을 자동적으로 해주는 Annotation이다.
@Controller + @ResponseBody를 사용하면 @ResponseBody를 모든 메소드에서 적용한다.

@Controller 와 @RestController 의 차이
@Controller
API와 view를 동시에 사용하는 경우에 사용한다.
대신 API 서비스로 사용하는 경우는 @ResponseBody를 사용하여 객체를 반환한다.
view(화면) return이 주목적이다.

@RestController
view가 필요없는 API만 지원하는 서비스에서 사용한다.
Spring 4.0.1부터 제공
@RequestMapping 메서드가 기본적으로 @ResponseBody 의미를 가정한다.
data(json, xml 등) return이 주목적이다.

즉, @RestController = @Controller + @ResponseBody 이다.

@Service
Service Class에서 쓰인다.
비즈니스 로직을 수행하는 Class라는 것을 나타내는 용도이다.

@Repository
DAO class에서 쓰인다.
DataBase에 접근하는 method를 가지고 있는 Class에서 쓰인다.

@EnableAutoConfiguration
Spring Application Context를 만들 때 자동으로 설정하는 기능을 켠다.
classpath의 내용에 기반해서 자동으로 생성해준다.
만약 tomcat-embed-core.jar가 존재하면 톰캣 서버가 setting된다.

@Configuration
@Configuration을 클래스에 적용하고 @Bean을 해당 Class의 method에 적용하면 @Autowired로 Bean을 부를 수 있다.

@Required
setter method에 적용해주면 Bean 생성시 필수 프로퍼티 임을 알린다.
Required Annotation을 사용하여 optional 하지 않은, 꼭 필요한 속성들을 정의한다.
영향을 받는 bean property를 구성할 시에는 XML 설정 파일에 반드시 property를 채워야 한다.
엄격한 체크, 그렇지 않으면 BeanInitializationException 예외를 발생

@Log4j2 log.info() 사용 가능하게 해줌.

@SpringBootApplication
@Configuration, @EnableAutoConfiguration, @ComponentScan 3가지를 하나의 애노테이션으로 합친 것이다.

@RequestMapping
요청 URL을 어떤 method가 처리할지 mapping해주는 Annotation이다.
Controller나 Controller의 method에 적용한다.
요청을 받는 형식인 GET, POST, PATCH, PUT, DELETE 를 정의하기도 한다.
요청 받는 형식을 정의하지 않는다면, 자동적으로 GET으로 설정된다.

@CrossOrigin
CORS 보안상의 문제로 브라우저에서 리소스를 현재 origin에서 다른 곳으로의 AJAX요청을 방지하는 것이다.
@RequestMapping이 있는 곳에 사용하면 해당 요청은 타 도메인에서 온 ajax요청을 처리해준다.

@GetMapping
@RequestMapping(Method=RequestMethod.GET)과 같다.
@PostMapping, @PutMapping, @PatchMapping, @DeleteMapping 등 도 있다.

@RequestBody
요청이 온 데이터(JSON이나 XML형식)를 바로 Class나 model로 매핑하기 위한 Annotation이다.
POST나 PUT, PATCH로 요청을 받을때에, 요청에서 넘어온 body 값들을 자바 타입으로 파싱해준다.
HTTP POST 요청에 대해 request body에 있는 request message에서 값을 얻어와 매핑한다.
RequestData를 바로 Model이나 클래스로 매핑한다.
이를테면 JSON 이나 XML같은 데이터를 적절한 messageConverter로 읽을 때 사용하거나 POJO 형태의 데이터 전체로 받는 경우에 사용한다.

@RequestParam
@PathVariable과 비슷하다.
request의 parameter에서 가져오는 것이다. method의 파라미터에 사용된다.
?moviename=thepurge 와 같은 쿼리 파라미터를 파싱해준다.
HTTP GET 요청에 대해 매칭되는 request parameter 값이 자동으로 들어간다.
url 뒤에 붙는 parameter 값을 가져올 때 사용한다.

@ResponseBody
HttpMessageConverter를 이용하여 JSON 혹은 xml 로 요청에 응답할수 있게 해주는 Annotation이다.
view가 아닌 JSON 형식의 값을 응답할 때 사용하는 Annotation으로 문자열을 리턴하면
그 값을 http response header가 아닌 response body에 들어간다.
이미 RestController Annotation이 붙어 있다면, 쓸 필요가 없다.
허나 그렇지 않은 단순 컨트롤러라면, HttpResponse로 응답 할 수 있게 해준다.
만약 객체를 return하는 경우 JACKSON 라이브러리에 의해 문자열로 변환되어 전송된다.
context에 설정된 viewResolver를 무시한다고 보면된다.

@PathVariable
method parameter 앞에 사용하면서 해당 URL에서 {특정값}을 변수로 받아 올 수 있다.
@RequestMapping(value = "/some/path/{id}", method = RequestMethod.GET)
public ResponseEntity<?> someMethod(@PathVariable int id) {}
HTTP 요청에 대해 매핑되는 request parameter 값이 자동으로 Binding 된다.
uri에서 각 구분자에 들어오는 값을 처리해야 할 때 사용한다.
REST API에서 값을 호출할 때 주로 많이 사용한다.





*/