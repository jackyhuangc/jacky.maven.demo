package com.jacky.utils.aop;

import java.lang.annotation.*;

//@Inherited annotation类型是被标注过的class的子类所继承。类并不从它所实现的接口继承annotation，方法并不从它所重载的方法继承annotation
@Target({ ElementType.PARAMETER, ElementType.METHOD }) // FIELD:用于描述域(字段)，METHOD:用于描述方法，PARAMETER:用于描述参数
@Retention(RetentionPolicy.RUNTIME) // 使用这个meta-Annotation可以对Annotation的“生命周期”限制，在运行时有效（即运行时保留）
@Documented // 可以被例如javadoc此类的工具文档化
public @interface Log {

	String module() default "";

	String methods() default "";
}
