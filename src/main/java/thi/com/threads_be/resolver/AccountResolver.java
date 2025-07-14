package thi.com.threads_be.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class AccountResolver {
    @QueryMapping
    public String hello() {
        return "Hello World";
    }
}
