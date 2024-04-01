package kr.or.ddit.payment;

import org.springframework.context.annotation.ComponentScan;
import static org.springframework.context.annotation.ComponentScan.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;


@Configuration
@ComponentScan(value = "kr.or.ddit.payment", useDefaultFilters = true, excludeFilters = {
	@Filter(classes = Controller.class)
	, @Filter(classes = MvcView.class)
})
public class PaymentParentJavaConfiguration {

}