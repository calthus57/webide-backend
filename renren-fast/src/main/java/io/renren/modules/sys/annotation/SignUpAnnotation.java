package io.renren.modules.sys.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = SignUpAnnotationValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SignUpAnnotation {
    String message()default "注册信息不合法！";
    Class<?>[] groups()default {};
    Class<? extends Payload>[] payload()default {};
}
