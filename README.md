# jStrainer Demo

A simple demo of [jStrainer](https://github.com/jstrainer/jstrainer)

### Requirements

* Java 1.8 or later
* Maven 3

### How to run

```
git clone https://github.com/jstrainer/jstrainer-demo.git
cd jstrainer-demo
mvn spring-boot:run
```

### Testing in Postman

![image](https://user-images.githubusercontent.com/1093408/72948523-2c735000-3d64-11ea-82c8-dd67c3b06f27.png)


### Implementation

1. Define the Strainer bean in our Spring configuration:

```java
package com.github.jstrainer.jstrainerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.jstrainer.Strainer;

@Configuration
public class StrainerConfiguration {

    @Bean
    public Strainer strainer() {
        return new Strainer();
    }

}
```

2. Create a `RequestBodyAdvice` to invoke the filters before it is passed into a controller method:

```java
package com.github.jstrainer.jstrainerdemo.web;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import com.github.jstrainer.Filtered;
import com.github.jstrainer.Strainer;

@ControllerAdvice
public class FilteredRequestBodyAdvice implements RequestBodyAdvice {

    @Autowired
    private Strainer strainer;

    @Override
    public boolean supports(MethodParameter methodParameter, Type valueType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(Filtered.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type valueType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type valueType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        strainer.filter(body); // invoking the filters
        return body;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type valueType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

}
```

3. Annotate your controller method with `@Filtered`

```java
package com.github.jstrainer.jstrainerdemo.web.rest;

// ...

import com.github.jstrainer.Filtered;
import com.github.jstrainer.jstrainerdemo.model.Person;

@RestController
@RequestMapping("/people")
public class PersonController {

    @PostMapping
    public Person create(@RequestBody @Validated @Filtered Person person) {
        return person; // data already filtered
    }

}
```





